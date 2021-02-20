package com.deliveroo.cron

import com.deliveroo.cron.CronParser
import spock.lang.Specification

class ResultPrinterTest extends Specification {

    def standardOut = System.out
    def outputStreamCaptor = new ByteArrayOutputStream()

    void setup() {
        System.setOut(new PrintStream(outputStreamCaptor))
    }

    void cleanup() {
        System.setOut(standardOut)
    }

    def "should throw illegal argument exception when #input"(String[] input) {
        when:
        CronParser.parse(input)

        then:
        def error = thrown(IllegalArgumentException)
        error.message == "Single string parameter should be provided."

        where:
        input << [null, ["one", "two"]]
    }

    def "should throw illegal argument exception when argument has wrong number of parameters"() {
        given:
        def input = new String[]{"*/15 0 1,15 * 1-5 /usr/bin/find something"};

        when:
        CronParser.parse(input)

        then:
        def error = thrown(IllegalArgumentException)
        error.message == "Input parameter doesn't have correct number of parameters."
    }

    def "should throw illegal argument exception when #expectedTimeUnit parameter negative"(String[] input, String expectedTimeUnit) {
        when:
        CronParser.parse(input)

        then:
        def error = thrown(IllegalArgumentException)
        error.message == "[$expectedTimeUnit] param in wrong format"

        where:
        input                        | expectedTimeUnit
        ["-1 * * * * /usr/bin/find"] | "MINUTE"
        ["* -1 * * * /usr/bin/find"] | "HOUR"
        ["* * -1 * * /usr/bin/find"] | "DAY_OF_MONTH"
        ["* * * -1 * /usr/bin/find"] | "MONTH"
        ["* * * * -1 /usr/bin/find"] | "DAY_OF_WEEK"
    }

    def "should throw illegal argument exception when #expectedTimeUnit parameter is wrong interval"(String[] input, String expectedTimeUnit) {
        when:
        CronParser.parse(input)

        then:
        def error = thrown(IllegalArgumentException)
        error.message == "[$expectedTimeUnit] param in wrong format"

        where:
        input                          | expectedTimeUnit
        ["*/60 * * * * /usr/bin/find"] | "MINUTE"
        ["* */24 * * * /usr/bin/find"] | "HOUR"
        ["* * */32 * * /usr/bin/find"] | "DAY_OF_MONTH"
        ["* * * */13 * /usr/bin/find"] | "MONTH"
        ["* * * * */8 /usr/bin/find"]  | "DAY_OF_WEEK"
    }

    def "should print command runs in intervals"(String[] input, String expectedOutput) {
        when:
        CronParser.parse(input);

        then:
        outputStreamCaptor.toString() == expectedOutput.stripIndent()

        where:
        input                          | expectedOutput
        ["*/15 1 1 1 1 /usr/bin/find"] | """\
                                            minute       0 15 30 45
                                            hour         1
                                            day of month 1
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 */6 1 1 1 /usr/bin/find"]  | """\
                                            minute       1
                                            hour         0 6 12 18
                                            day of month 1
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1 */10 1 1 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1
                                            day of month 10 20 30
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1 1 */5 1 /usr/bin/find"]  | """\
                                            minute       1
                                            hour         1
                                            day of month 1
                                            month        5 10
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1 1 1 */2 /usr/bin/find"]  | """\
                                            minute       1
                                            hour         1
                                            day of month 1
                                            month        1
                                            day of week  2 4 6
                                            command /usr/bin/find
                                            """
    }

    def "should print command run in scoped time unit"(String[] input, String expectedOutput) {
        when:
        CronParser.parse(input);

        then:
        outputStreamCaptor.toString() == expectedOutput.stripIndent()

        where:
        input                         | expectedOutput
        ["1-3 1 1 1 1 /usr/bin/find"] | """\
                                            minute       1 2 3
                                            hour         1
                                            day of month 1
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1-3 1 1 1 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1 2 3
                                            day of month 1
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1 1-3 1 1 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1
                                            day of month 1 2 3
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1 1 1-3 1 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1
                                            day of month 1
                                            month        1 2 3
                                            day of week  1
                                            command /usr/bin/find
                                            """

        ["1 1 1 1 1-3 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1
                                            day of month 1
                                            month        1
                                            day of week  1 2 3
                                            command /usr/bin/find
                                            """
    }

    void "should print command run once hour when range param is limited to 1 hour"() {
        given:
        def input = new String[]{"0 1-1 1 1 1 /usr/bin/find"};

        when:
        CronParser.parse(input);

        then:
        outputStreamCaptor.toString() == """\
                                            minute       0
                                            hour         1
                                            day of month 1
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """.stripIndent()
    }

    def "should print command run at multiple time slots"(String[] input, String expectedOutput) {
        when:
        CronParser.parse(input);

        then:
        outputStreamCaptor.toString() == expectedOutput.stripIndent()

        where:
        input                         | expectedOutput
        ["0,3 1 1 1 1 /usr/bin/find"] | """\
                                            minute       0 3
                                            hour         1
                                            day of month 1
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 0,3 1 1 1 /usr/bin/find"] | """\
                                            minute       1
                                            hour         0 3
                                            day of month 1
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1 1,3 1 1 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1
                                            day of month 1 3
                                            month        1
                                            day of week  1
                                            command /usr/bin/find
                                            """
        ["1 1 1 1,3 1 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1
                                            day of month 1
                                            month        1 3
                                            day of week  1
                                            command /usr/bin/find
                                            """

        ["1 1 1 1 1,3 /usr/bin/find"] | """\
                                            minute       1
                                            hour         1
                                            day of month 1
                                            month        1
                                            day of week  1 3
                                            command /usr/bin/find
                                            """
    }
}


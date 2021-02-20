package com.deliveroo.cron

import spock.lang.Specification

class CronParserTest extends Specification {

    def standardOut = System.out
    def outputStreamCaptor = new ByteArrayOutputStream()

    void setup() {
        System.setOut(new PrintStream(outputStreamCaptor))
    }

    void cleanup() {
        System.setOut(standardOut)
    }

    void "should print requested params"() {
        given:
        def command = "ps aux"
        def minute = List.of(10)
        def hour = List.of(0)
        def dayOfMonth = List.of(1)
        def month = List.of(1)
        def dayOfWeek = List.of(2)

        when:
        ResultPrinter.print(command, minute, hour, dayOfMonth, month, dayOfWeek);

        then:
        outputStreamCaptor.toString() == """\
                                            minute       10
                                            hour         0
                                            day of month 1
                                            month        1
                                            day of week  2
                                            command ps aux
                                            """.stripIndent()
    }
}


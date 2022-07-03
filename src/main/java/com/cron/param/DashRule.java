package com.cron.param;

import com.cron.util.Checks;
import com.cron.model.TimeUnit;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class DashRule implements Rule {

    private final static String TEST_PATTERN = "\\d+-\\d+";


    @Override
    public boolean evaluate(String input) {
        return Pattern.compile(TEST_PATTERN).asMatchPredicate().test(input);
    }

    @Override
    public List<Integer> getResult(String input, TimeUnit dateTimeUnit) {
        final var startAndEnd = input.split("-");
        final var start = Integer.parseInt(startAndEnd[0]);
        final var end = Integer.parseInt(startAndEnd[1]);
        Checks.checkThat(start <= end, "First parameter in range argument should be lesser than the second one");
        return IntStream.range(start, end + 1)
            .boxed()
            .collect(toList());
    }
}

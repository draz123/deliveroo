package com.cron.param;

import com.cron.model.TimeUnit;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class AsteriskRule implements Rule {

    private static final String TEST_PATTERN = "\\*/?\\d+(\\d+)?|\\*";

    @Override
    public boolean evaluate(String input) {

        return Pattern.compile(TEST_PATTERN).asMatchPredicate().test(input);
    }

    @Override
    public List<Integer> getResult(String input, TimeUnit dateTimeUnit) {
        final var interval = input.equals("*") ? 1 : Integer.parseInt(input.replace("*/", ""));
        return IntStream.range(dateTimeUnit.beginning, dateTimeUnit.end + 1)
                .filter(i -> i % interval == 0)
                .boxed()
                .collect(toList());
    }
}

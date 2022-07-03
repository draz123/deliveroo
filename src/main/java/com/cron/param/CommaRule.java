package com.cron.param;

import com.cron.model.TimeUnit;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommaRule implements Rule {

    private final static String TEST_PATTERN = "\\d+,\\d+(,\\d+)?";

    @Override
    public boolean evaluate(String input) {
        return Pattern.compile(TEST_PATTERN).asMatchPredicate().test(input);
    }

    @Override
    public List<Integer> getResult(String input, TimeUnit ignored) {
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}

package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommaRule implements Rule {

    @Override
    public boolean evaluate(String input) {
        return Pattern.compile("\\d+,\\d+(,\\d+)?").asMatchPredicate().test(input);
    }

    @Override
    public List<Integer> getResult(String input, TimeUnit ignored) {
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}

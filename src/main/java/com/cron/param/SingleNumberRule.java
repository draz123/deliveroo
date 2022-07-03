package com.cron.param;

import com.cron.model.TimeUnit;

import java.util.List;
import java.util.regex.Pattern;

public class SingleNumberRule implements Rule {

    private final static String regex = "\\d+";

    @Override
    public boolean evaluate(String input) {
        return Pattern.compile(regex).asMatchPredicate().test(input);
    }

    @Override
    public List<Integer> getResult(String input, TimeUnit ignored) {
        return List.of(Integer.parseInt(input));
    }
}

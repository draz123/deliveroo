package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;

import java.util.List;
import java.util.regex.Pattern;

public class SingleNumberRule implements Rule {

    @Override
    public boolean evaluate(String input) {
        return Pattern.compile("\\d+").asMatchPredicate().test(input);
    }

    @Override
    public List<Integer> getResult(String input, TimeUnit ignored) {
        return List.of(Integer.parseInt(input));
    }
}

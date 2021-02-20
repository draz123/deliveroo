package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;

import java.util.List;

public interface Rule {
    boolean evaluate(String input);
    List<Integer> getResult(String input, TimeUnit dateTimeUnit);
}

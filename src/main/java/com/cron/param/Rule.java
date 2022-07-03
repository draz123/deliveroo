package com.cron.param;

import com.cron.model.TimeUnit;

import java.util.List;

public interface Rule {

    boolean evaluate(String input);

    List<Integer> getResult(String input, TimeUnit dateTimeUnit);
}

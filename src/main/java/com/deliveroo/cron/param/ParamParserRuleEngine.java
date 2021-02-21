package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;

import java.util.List;

public class ParamParserRuleEngine {

    private static final List<Rule> RULES = List.of(new AsteriskRule(), new DashRule(), new CommaRule(), new SingleNumberRule());

    public List<Integer> process(String input, TimeUnit dateTimeUnit) {
        final var rule = RULES
            .stream()
            .filter(r -> r.evaluate(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Input param does not matches any Rule"));
        return rule.getResult(input, dateTimeUnit);
    }
}

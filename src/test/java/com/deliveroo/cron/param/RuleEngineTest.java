package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;
import com.deliveroo.cron.param.RuleEngine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RuleEngineTest {

    private final RuleEngine ruleEngine = new RuleEngine();

    @Test
    void should_proces_comma_input() {
        // given
        var input = "3,4";
        var timeUnit = TimeUnit.MINUTE;

        // when
        var result = ruleEngine.process(input, timeUnit);

        //then
        assertEquals(List.of(3, 4), result);
    }

    @Test
    void should_throw_exception_when_not_supported_input() {
        // given
        var input = "3/4";
        var timeUnit = TimeUnit.MINUTE;

        // then
        assertThrows(IllegalArgumentException.class, () -> ruleEngine.process(input, timeUnit));
    }
}
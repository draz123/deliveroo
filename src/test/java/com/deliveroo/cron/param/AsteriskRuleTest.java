package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;
import com.deliveroo.cron.param.AsteriskRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AsteriskRuleTest {

    private final AsteriskRule rule = new AsteriskRule();

    @Test
    void should_evaluate_dash_numbers() {
        // given
        var input = "*/4";

        // when
        var result = rule.evaluate(input);

        // then
        assertTrue(result);
    }

    @Test
    void should_parse_dash_numbers() {
        // given
        var input = "*/10";

        // when
        var result = rule.getResult(input, TimeUnit.DAY_OF_MONTH);

        // then
        Assertions.assertEquals(List.of(10, 20, 30), result);
    }
}
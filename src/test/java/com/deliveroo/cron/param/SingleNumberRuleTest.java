package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;
import com.deliveroo.cron.param.SingleNumberRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SingleNumberRuleTest {

    SingleNumberRule rule = new SingleNumberRule();

    @Test
    void should_evaluate_single_number() {
        // given
        var input = "2";

        // when
        var result = rule.evaluate(input);

        // then
        assertTrue(result);
    }

    @Test
    void should_parse_single_number() {
        // given
        var input = "2";

        // when
        var result = rule.getResult(input, TimeUnit.DAY_OF_MONTH);

        // then
        Assertions.assertEquals(List.of(2), result);
    }
}
package com.cron.param;

import com.cron.model.TimeUnit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(List.of(2), result);
    }
}
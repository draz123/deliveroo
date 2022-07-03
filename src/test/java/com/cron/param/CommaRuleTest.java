package com.cron.param;

import com.cron.model.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommaRuleTest {

    private final CommaRule rule = new CommaRule();

    @Test
    void should_evaluate_dash_numbers() {
        // given
        var input = "2,4";

        // when
        var result = rule.evaluate(input);

        // then
        assertTrue(result);
    }

    @Test
    void should_parse_dash_numbers() {
        // given
        var input = "2,4";

        // when
        var result = rule.getResult(input, TimeUnit.DAY_OF_MONTH);

        // then
        assertEquals(List.of(2, 4), result);
    }
}
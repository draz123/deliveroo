package com.cron.param;

import com.cron.model.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DashRuleTest {

    private final DashRule rule = new DashRule();

    @Test
    void should_evaluate_dash_numbers() {
        // given
        var input = "2-4";

        // when
        var result = rule.evaluate(input);

        // then
        assertTrue(result);
    }

    @Test
    void should_parse_dash_numbers() {
        // given
        var input = "2-4";

        // when
        var result = rule.getResult(input, TimeUnit.DAY_OF_MONTH);

        // then
        assertEquals(List.of(2, 3, 4), result);
    }

    @Test
    void should_throw_exception_when_first_number_from_scope_greater_than_latter() {
        // given
        var input = "4-2";

        // then
        final var exception = assertThrows(IllegalArgumentException.class, () -> rule.getResult(input, TimeUnit.DAY_OF_MONTH));
        assertEquals("First parameter in range argument should be lesser than the second one", exception.getMessage());
    }
}
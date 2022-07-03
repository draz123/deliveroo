package com.cron.param;

import com.cron.model.TimeUnit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParamParserRuleEngineTest {

    private final ParamParserRuleEngine paramParserRuleEngine = new ParamParserRuleEngine();

    @Test
    void should_process_comma_input() {
        // given
        var input = "3,4";
        var timeUnit = TimeUnit.MINUTE;

        // when
        var result = paramParserRuleEngine.process(input, timeUnit);

        //then
        assertEquals(List.of(3, 4), result);
    }

    @Test
    void should_throw_exception_when_not_supported_input() {
        // given
        var input = "3/4";
        var timeUnit = TimeUnit.MINUTE;

        // then
        assertThrows(IllegalArgumentException.class, () -> paramParserRuleEngine.process(input, timeUnit));
    }
}
package com.deliveroo.cron;

import com.deliveroo.cron.model.TimeUnit;
import com.deliveroo.cron.param.ParamParserRuleEngine;

import java.util.Arrays;
import java.util.List;

import static com.deliveroo.cron.model.TimeUnit.*;
import static com.deliveroo.cron.util.Checks.checkThat;

public class CronParser {

    private static final int COMMAND_PARAM_INDEX = 5;

    private final ParamParserRuleEngine paramParserRuleEngine = new ParamParserRuleEngine();
    private final ResultPrinter resultPrinter = new ResultPrinter();

    public void parse(String[] args) {
        final var parameters = validateInputArgument(args);
        resultPrinter.print(commandParam(parameters),
            timeUnitParam(parameters, MINUTE),
            timeUnitParam(parameters, HOUR),
            timeUnitParam(parameters, DAY_OF_MONTH),
            timeUnitParam(parameters, MONTH),
            timeUnitParam(parameters, DAY_OF_WEEK));
    }

    private static String[] validateInputArgument(String[] args) {
        checkThat(args != null && args.length == 1, "Single string parameter should be provided.");
        final var input = args[0];
        final var parameters = input.split("\\s+");
        checkThat(parameters.length == 6, "Input parameter doesn't have correct number of parameters.");
        Arrays.stream(TimeUnit.values())
            .forEach(timeUnit -> checkThat(timeUnit.pattern.test(parameters[timeUnit.inputIndex]), String.format("[%s] param in wrong format", timeUnit.name())));
        return parameters;
    }

    private String commandParam(String[] parameters) {
        return parameters[COMMAND_PARAM_INDEX];
    }

    private List<Integer> timeUnitParam(String[] parameters, TimeUnit timeUnit) {
        final var parameter = parameters[timeUnit.inputIndex];
        return paramParserRuleEngine.process(parameter, timeUnit);
    }
}
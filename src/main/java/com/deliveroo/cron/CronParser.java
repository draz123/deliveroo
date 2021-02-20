package com.deliveroo.cron;

import com.deliveroo.cron.model.TimeUnit;
import com.deliveroo.cron.param.RuleEngine;

import java.util.List;

import static com.deliveroo.cron.util.Checks.checkThat;
import static com.deliveroo.cron.model.TimeUnit.*;

public class CronParser {

    private static final int COMMAND_PARAM_INDEX = 5;
    private static final RuleEngine RULE_ENGINE = new RuleEngine();
    private static final ResultPrinter resultPrinter = new ResultPrinter();

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
        return parameters;
    }

    private static String commandParam(String[] parameters) {
        final var command = parameters[COMMAND_PARAM_INDEX];
        checkThat(!command.isBlank() && !command.isEmpty(), "Command parameter is incorrect");
        return command;
    }

    private static List<Integer> timeUnitParam(String[] parameters, TimeUnit timeUnit) {
        final var parameter = parameters[timeUnit.inputIndex];
        checkThat(timeUnit.pattern.test(parameter), String.format("[%s] param in wrong format", timeUnit.name()));
        return RULE_ENGINE.process(parameter, timeUnit);
    }
}
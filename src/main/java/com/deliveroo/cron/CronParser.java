package com.deliveroo.cron;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.deliveroo.cron.Checks.checkThat;
import static com.deliveroo.cron.TimeUnit.*;
import static java.util.stream.Collectors.toList;

public class CronParser {

    private static final int COMMAND_PARAM_INDEX = 5;

    public static void main(String[] args) {
        parse(args);
    }

    public static void parse(String[] args) {
        checkThat(args != null && args.length == 1, "Single string parameter should be provided.");
        final var input = args[0];
        final var parameters = input.split("\\s+");
        checkThat(parameters.length == 6, "Input parameter doesn't have correct number of parameters.");
        final var minuteResults = getParam(parameters, MINUTE);
        final var hoursResults = getParam(parameters, HOUR);
        final var dayOfMonthResults = getParam(parameters, DAY_OF_MONTH);
        final var monthResults = getParam(parameters, MONTH);
        final var dayOfWeekResults = getParam(parameters, DAY_OF_WEEK);
        final var command = command(parameters);
        ResultPrinter.print(command, minuteResults, hoursResults, dayOfMonthResults, monthResults, dayOfWeekResults);
    }

    private static String command(String[] parameters) {
        final var command = parameters[COMMAND_PARAM_INDEX];
        checkThat(!command.isBlank() && !command.isEmpty(), "Command parameter is incorrect");
        return command;
    }

    private static List<Integer> getParam(String[] parameters, TimeUnit timeUnit) {
        final var parameter = parameters[timeUnit.inputIndex];
        checkThat(timeUnit.pattern.test(parameter), String.format("[%s] param in wrong format", timeUnit.name()));
        return parse(parameter, timeUnit);
    }
    private static List<Integer> parse(String input, TimeUnit timeUnit) {
        if (isDirectNumber(input)) {
            return List.of(Integer.parseInt(input));
        }
        if (input.contains("-")) {
            return parseRangeParam(input);
        }
        if (input.contains(",")) {
            return parseCommaParam(input);
        } else {
            return parseAstrixParam(input, timeUnit);
        }
    }

    private static boolean isDirectNumber(String input) {
        return !input.contains("-") && !input.contains("*") && !input.contains(",");
    }

    private static List<Integer> parseCommaParam(String input) {
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private static List<Integer> parseAstrixParam(String input, TimeUnit dateTimeUnit) {
        final var interval = input.equals("*") ? 1 : Integer.parseInt(input.replace("*/", ""));
        return IntStream.range(dateTimeUnit.beginning, dateTimeUnit.end + 1)
            .filter(i -> i % interval == 0)
            .boxed()
            .collect(toList());
    }

    private static List<Integer> parseRangeParam(String input) {
        final var startAndEnd = input.split("-");
        final var start = Integer.parseInt(startAndEnd[0]);
        final var end = Integer.parseInt(startAndEnd[1]);
        checkThat(start <= end, "First parameter in range argument should be lesser than the second one");
        return IntStream.range(start, end + 1)
            .boxed()
            .collect(toList());
    }
}
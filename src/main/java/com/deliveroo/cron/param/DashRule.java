package com.deliveroo.cron.param;

import com.deliveroo.cron.model.TimeUnit;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static com.deliveroo.cron.util.Checks.checkThat;
import static java.util.stream.Collectors.toList;

public class DashRule implements Rule {

    @Override
    public boolean evaluate(String input) {
        return Pattern.compile("-?\\d+(\\.\\d+)?-?\\d+(\\.\\d+)?").asMatchPredicate().test(input);
    }

    @Override
    public List<Integer> getResult(String input, TimeUnit dateTimeUnit) {
        final var startAndEnd = input.split("-");
        final var start = Integer.parseInt(startAndEnd[0]);
        final var end = Integer.parseInt(startAndEnd[1]);
        checkThat(start <= end, "First parameter in range argument should be lesser than the second one");
        return IntStream.range(start, end + 1)
            .boxed()
            .collect(toList());
    }
}

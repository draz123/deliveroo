package com.deliveroo.cron;

import java.util.Arrays;
import java.util.List;

public class ResultPrinter {

    public void print(String command,
                      List<Integer> minute,
                      List<Integer> hour,
                      List<Integer> dayOfMonth,
                      List<Integer> month,
                      List<Integer> dayOfWeek) {
        System.out.printf("minute        %s\n" +
                          "hour          %s\n" +
                          "day of month  %s\n" +
                          "month         %s\n" +
                          "day of week   %s\n" +
                          "command       %s%n",
            parse(minute),
            parse(hour),
            parse(dayOfMonth),
            parse(month),
            parse(dayOfWeek),
            command);
    }

    private static String parse(List<Integer> minute) {
        return Arrays.toString(minute.toArray())
            .replaceAll("^.|.$|,", "");
    }
}

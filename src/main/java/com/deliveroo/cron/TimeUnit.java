package com.deliveroo.cron;

import java.util.function.Predicate;
import java.util.regex.Pattern;

enum TimeUnit {
    MINUTE(0, 59, Pattern.compile("\\*|(\\*)?(/)?([0-5]?\\d)|(([0-5]?\\d)-([0-5]?\\d))|(([0-5]?\\d),)*([0-5]?\\d)").asMatchPredicate(), 0),
    HOUR(0, 23, Pattern.compile("\\*|(\\*/)?([0-9]|[1]?\\d|2[0-3])|([0-9]|[1]?\\d|2[0-3])-([0-9]|[1]?\\d|2[0-3])|(([0-9]|[1]?\\d|2[0-3]),)*([0-9]|[1]?\\d|2[0-3])").asMatchPredicate(), 1),
    DAY_OF_MONTH(1, 31, Pattern.compile("\\*|(\\*/)?(0?[1-9]|[12]\\d|3[01])|(0?[1-9]|[12]\\d|3[01])-(0?[1-9]|[12]\\d|3[01])|((0?[1-9]|[12]\\d|3[01]),)*(0?[1-9]|[12]\\d|3[01])").asMatchPredicate(), 2),
    MONTH(1, 12, Pattern.compile("\\*|(\\*/)?([1-9]|1[012])|([1-9]|1[012])-([1-9]|1[012])|(([1-9]|1[012]),)*([1-9]|1[012])").asMatchPredicate(), 3),
    DAY_OF_WEEK(1, 7, Pattern.compile("\\*|(\\*/)?([0-6])|([0-6]-[0-6])|([0-6],)*[0-6]").asMatchPredicate(), 4);

    public final int beginning;
    public final int end;
    public final Predicate<String> pattern;
    public final int inputIndex;

    TimeUnit(int beginning, int end, Predicate<String> pattern, int inputIndex) {
        this.beginning = beginning;
        this.end = end;
        this.pattern = pattern;
        this.inputIndex = inputIndex;
    }
}
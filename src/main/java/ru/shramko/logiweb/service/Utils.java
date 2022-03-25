package ru.shramko.logiweb.service;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static int daysLeft () {
        LocalDate today = LocalDate.now();
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        return (int)DAYS.between(today, endOfMonth);
    }
}

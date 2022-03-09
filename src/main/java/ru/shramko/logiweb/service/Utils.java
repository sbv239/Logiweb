package ru.shramko.logiweb.service;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Utils {
    public static int daysLeft () {
        LocalDate today = LocalDate.now();
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        int daysLeft = (int)DAYS.between(today, endOfMonth);
        return daysLeft;
    }
}

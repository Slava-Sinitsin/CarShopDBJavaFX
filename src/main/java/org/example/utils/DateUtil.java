package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date) {
        return (date == null) ? null : dateFormat.format(date);
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error of data formatting: " + e.getMessage());
        }
    }
}
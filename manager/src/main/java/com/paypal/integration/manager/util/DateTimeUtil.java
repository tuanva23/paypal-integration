package com.paypal.integration.manager.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static Date toDateTime (String dateTimeStr, String format) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateTimeStr);
    }

    public static Date toDateTime (String dateTimeStr) throws ParseException {
        return toDateTime(dateTimeStr, "yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    public static String toString (Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String toString (Date date) {
        return toString(date, "yyyy-MM-dd'T'HH:mm:ss'Z'");
    }
}

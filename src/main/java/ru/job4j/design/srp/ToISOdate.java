package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * convert() method converts Calendar type to string of date and time in ISO8601 format
 */
public class ToISOdate {

    public static String convert(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                .format(calendar.getTime());
    }
}

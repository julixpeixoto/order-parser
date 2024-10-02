package com.luizalabs.order_parser.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate formatStringToDate(String date) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date, parser);
    }
}

package br.com.stoom.store.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    
    public static final String yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format date to string
     * @param date
     * @param pattern
     * @return
     */
    public static String toFormat(LocalDateTime date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    /**
     * 
     * @param dataParam
     * @return
     */
    public static LocalDateTime toLocalDate(String dataParam, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // Convertendo a string para LocalDate
        return LocalDateTime.parse(dataParam, formatter);
    }
}

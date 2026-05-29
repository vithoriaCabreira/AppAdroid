package br.edu.utfpr.vithoriacabreira.minhasobras;

import androidx.room.TypeConverter;
import java.time.LocalDate;

public class DateConverter {
    @TypeConverter
    public static LocalDate toDate(String dateString) {
        return dateString == null ? null : LocalDate.parse(dateString);
    }

    @TypeConverter
    public static String toDateString(LocalDate date) {
        return date == null ? null : date.toString();
    }
}
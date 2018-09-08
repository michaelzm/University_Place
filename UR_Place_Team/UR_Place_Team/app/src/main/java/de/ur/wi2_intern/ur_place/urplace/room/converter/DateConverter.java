package de.ur.wi2_intern.ur_place.urplace.room.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

import de.ur.wi2_intern.ur_place.urplace.room.models.Gender;

public class DateConverter {
    @TypeConverter
    public static Date timestampToDate(Long timestamp) {
        if(timestamp == null){
            return null;
        } else {
            Date date = new Date();
            date.setTime(timestamp);
            return date;
        }
    }

    @TypeConverter
    public static Long genderToString(Date date) {
        return date == null ? null : date.getTime();
    }
}

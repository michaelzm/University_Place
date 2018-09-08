package de.ur.wi2_intern.ur_place.urplace.room.converter;

import android.arch.persistence.room.TypeConverter;

import de.ur.wi2_intern.ur_place.urplace.room.models.Gender;

public class GenderConverter {
    @TypeConverter
    public static Gender stringToGender(String nameValue){
        return Gender.valueOf(nameValue);
    }

    @TypeConverter
    public static String genderToString(Gender gender){
        return gender == null ? null : gender.name();
    }
}

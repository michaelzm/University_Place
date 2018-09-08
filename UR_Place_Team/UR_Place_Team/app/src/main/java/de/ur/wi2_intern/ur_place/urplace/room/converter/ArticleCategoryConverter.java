package de.ur.wi2_intern.ur_place.urplace.room.converter;

import android.arch.persistence.room.TypeConverter;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleCategory;

//todo categoryConverter
public class ArticleCategoryConverter {
    @TypeConverter
    public static String articleTypeToString(ArticleCategory articleCategory){
        return articleCategory.name;
    }
}



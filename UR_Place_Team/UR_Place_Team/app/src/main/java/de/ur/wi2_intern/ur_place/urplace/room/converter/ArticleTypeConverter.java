package de.ur.wi2_intern.ur_place.urplace.room.converter;

import android.arch.persistence.room.TypeConverter;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;


public class ArticleTypeConverter {
    @TypeConverter
    public static ArticleType stringToArticleType(String articleTypeValue){
        return ArticleType.valueOf(articleTypeValue);
    }

    @TypeConverter
    public static String articleTypeToString(ArticleType articletype){
        return articletype == null ? null : articletype.name();
    }
}

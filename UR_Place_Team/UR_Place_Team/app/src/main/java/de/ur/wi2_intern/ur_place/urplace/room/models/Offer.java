package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;
import de.ur.wi2_intern.ur_place.urplace.room.converter.ArticleTypeConverter;

@Entity(tableName = "offer")
public class Offer {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "offerID")
    private Long offerID;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "geo")
    private String geo;
    @ColumnInfo(name = "articleType")
    @TypeConverters(ArticleTypeConverter.class)
    private ArticleType articleType;
    @ColumnInfo(name = "picture")
    private String picture;

    public Offer() {
    }

    public Long getOfferID() {
        return offerID;
    }

    public void setOfferID(Long offerID) {
        this.offerID = offerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
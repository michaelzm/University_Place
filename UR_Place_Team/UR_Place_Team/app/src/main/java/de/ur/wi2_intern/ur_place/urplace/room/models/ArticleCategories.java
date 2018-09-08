package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "category")
public class ArticleCategories {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "categoryID")
    private long id;
    @ColumnInfo(name = "categoryTitle")
    private String title;
    @ColumnInfo(name = "dateCreated")
    private String dateCreated;
    @ColumnInfo(name = "lastUpdated")
    private String lastUpdated;




    public ArticleCategories(){

    }

    public ArticleCategories(long id, String title, String dateCreated, String lastUpdated) {
        this.id = id;
        this.title = title;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

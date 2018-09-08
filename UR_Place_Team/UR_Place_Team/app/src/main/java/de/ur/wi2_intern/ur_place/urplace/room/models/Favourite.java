package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
@Entity(foreignKeys = {}, tableName = "favourites")
public class Favourite {

    @PrimaryKey
    @ColumnInfo(name = "userID")
    Long userID;



    public Favourite() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

}

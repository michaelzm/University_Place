package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
@Entity(tableName = "declinedFriendOffers")


public class DeclinedFriendOffers {

    @PrimaryKey
    @ColumnInfo(name = "userID")
    Long userID;


    public DeclinedFriendOffers() {
    }

    public DeclinedFriendOffers(long userID){
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

}

package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Profile.class, parentColumns = "profileID", childColumns = "fromProfileID"),
        @ForeignKey(entity = Profile.class, parentColumns = "profileID", childColumns = "toProfileID")
}, tableName = "friendship")
public class Friendship {
    @PrimaryKey
    @ColumnInfo(name = "friendshipID")
    Long friendshipID;
    @ColumnInfo(name = "fromProfileID")
    Long fromProfileID;
    @ColumnInfo(name = "toProfileID")
    Long toProfileID;
    @ColumnInfo(name = "acceptanceDate")
    Date acceptanceDate;
    @ColumnInfo(name = "dateCreated")
    Date dateCreated;
    @ColumnInfo(name = "lastUpdated")
    Date lastUpdated;

    public Friendship() {
    }

    public Long getFriendshipID() {
        return friendshipID;
    }

    public void setFriendshipID(Long friendshipID) {
        this.friendshipID = friendshipID;
    }

    public Long getFromProfileID() {
        return fromProfileID;
    }

    public void setFromProfileID(Long fromProfileID) {
        this.fromProfileID = fromProfileID;
    }

    public Long getToProfileID() {
        return toProfileID;
    }

    public void setToProfileID(Long toProfileID) {
        this.toProfileID = toProfileID;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAccaptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

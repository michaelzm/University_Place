package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(foreignKeys = {
        @ForeignKey(entity = Post.class, parentColumns = "postID", childColumns = "postID"),
        @ForeignKey(entity = Profile.class, parentColumns = "profileID", childColumns = "profileID")
        },
        tableName = "comment")


public class Comment {
    @PrimaryKey
    @ColumnInfo(name = "commentID")
    Long commentID;
    @ColumnInfo(name = "postID")
    Long postID;
    @ColumnInfo(name = "profileID")
    Long profileID;
    @ColumnInfo(name = "text")
    String text;
    @ColumnInfo(name = "picture")
    String picture;
    @ColumnInfo(name = "latitude")
    Double latitude;
    @ColumnInfo(name = "longitude")
    Double longitude;
    @ColumnInfo(name = "dateCreated")
    private Date dateCreated;
    @ColumnInfo(name = "lastUpdated")
    private Date lastUpdated;

    public Comment() {
    }

    public Comment(long commentID,long postID,long profileID, String text, String picture){

        this.commentID = commentID;
        this.postID = postID;
        this.profileID = profileID;
        this.text = text;
        this.picture = picture;

    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public Long getProfileID() {
        return profileID;
    }

    public void setProfileID(Long profileID) {
        this.profileID = profileID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Profile.class, parentColumns = "profileID", childColumns = "profileID"),
        @ForeignKey(entity = Post.class, parentColumns = "postID", childColumns = "postID")},
        tableName = "post_like")
public class PostLike {
    @PrimaryKey
    @ColumnInfo(name = "postLikeID")
    Long postLikeID;
    @ColumnInfo(name = "profileID")
    Long profileID;
    @ColumnInfo(name = "postID")
    Long postID;
    @ColumnInfo(name = "dateCreated")
    private Date dateCreated;
    @ColumnInfo(name = "lastUpdated")
    private Date lastUpdated;

    public PostLike() {
    }

    public  PostLike(long postLikeID, long profileID, long postID){
        this.postLikeID = postLikeID;
        this.profileID = profileID;
        this.postID = postID;
    }

    public Long getPostLikeID() {
        return postLikeID;
    }

    public void setPostLikeID(Long postLikeID) {
        this.postLikeID = postLikeID;
    }

    public Long getProfileID() {
        return profileID;
    }

    public void setProfileID(Long profileID) {
        this.profileID = profileID;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
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

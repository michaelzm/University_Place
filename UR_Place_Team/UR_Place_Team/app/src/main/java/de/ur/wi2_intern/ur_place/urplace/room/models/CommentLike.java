package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Comment.class, parentColumns = "commentID", childColumns = "commentID"),
        @ForeignKey(entity = Profile.class, parentColumns = "profileID", childColumns = "profileID")
}, tableName = "comment_like")
public class CommentLike {
    @PrimaryKey
    @ColumnInfo(name = "commentLikeID")
    private Long commentLikeID;
    @ColumnInfo(name = "commentID")
    private Long commentID;
    @ColumnInfo(name = "profileID")
    private Long profileID;
    @ColumnInfo(name = "dateCreated")
    private Date dateCreated;
    @ColumnInfo(name = "lastUpdated")
    private Date lastUpdated;

    public CommentLike() {
    }

    public CommentLike(long commentLikeID, long profileID){
        this.commentLikeID = commentLikeID;

        this.profileID = profileID;
    }

    public Long getCommentLikeID() {
        return commentLikeID;
    }

    public void setCommentLikeID(Long commentLikeID) {
        this.commentLikeID = commentLikeID;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public Long getProfileID() {
        return profileID;
    }

    public void setProfileID(Long profileID) {
        this.profileID = profileID;
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

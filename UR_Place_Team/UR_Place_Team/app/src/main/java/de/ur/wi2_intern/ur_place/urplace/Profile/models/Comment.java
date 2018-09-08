package de.ur.wi2_intern.ur_place.urplace.Profile.models;

public class Comment {
    Profile profile;
    String commentText;
    String picture;
    String position;

    public Comment(Profile profile, String commentText, String picture, String position) {
        this.profile = profile;
        this.commentText = commentText;
        this.picture = picture;
        this.position = position;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

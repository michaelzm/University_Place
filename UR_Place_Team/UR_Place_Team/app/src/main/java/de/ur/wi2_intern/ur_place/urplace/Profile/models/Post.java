package de.ur.wi2_intern.ur_place.urplace.Profile.models;

import java.util.Collection;
import java.util.List;

public class Post {
    private long id;
    private String picture;
    private String text;
    private String position;
    private List<Comment> comments;

    public Post(long id, String picture, String text, String position, List<Comment> comments) {
        this.id = id;
        this.picture = picture;
        this.text = text;
        this.position = position;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

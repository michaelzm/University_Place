package de.ur.wi2_intern.ur_place.urplace.retrofit.models;

import java.util.ArrayList;

public class Post {
    public int id;
    public String text;
    public String picture;
    public ArrayList<Comment> comments;
    public ArrayList<PostLike> likes;
    public Profile profile;
    public GPS gps;
    public String dateCreated;
    public String lastUpdated;

    public Post() {
    }
}

package de.ur.wi2_intern.ur_place.urplace.retrofit.models;

import java.util.ArrayList;

public class Comment {
    public int id;
    public int post;
    public String text;
    public String picture;
    public GPS gps;
    public ArrayList<CommentLike> likes;
    public Profile profile;
    public String dateCreated;
    public String lastUpdated;
}

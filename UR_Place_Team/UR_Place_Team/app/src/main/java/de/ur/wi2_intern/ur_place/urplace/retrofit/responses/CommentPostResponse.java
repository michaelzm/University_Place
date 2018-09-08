package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.CommentLike;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.GPS;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class CommentPostResponse {
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

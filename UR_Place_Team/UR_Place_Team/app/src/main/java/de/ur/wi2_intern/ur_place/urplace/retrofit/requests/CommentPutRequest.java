package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.CommentLike;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.GPS;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class CommentPutRequest {
    public int id;
    public int post;
    public String text;
    public String picture;
    public GPS gps;
    public ArrayList<CommentLike> likes;
    public Profile profile;
    public String dateCreated;
    public String lastUpdated;

    public CommentPutRequest(int id, int post, String text, String picture, GPS gps, ArrayList<CommentLike> likes, Profile profile, String dateCreated, String lastUpdated) {
        this.id = id;
        this.post = post;
        this.text = text;
        this.picture = picture;
        this.gps = gps;
        this.likes = likes;
        this.profile = profile;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}

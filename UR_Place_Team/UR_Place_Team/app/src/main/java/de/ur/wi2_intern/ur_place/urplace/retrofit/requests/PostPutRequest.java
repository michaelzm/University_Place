package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.GPS;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.PostLike;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class PostPutRequest {
    public int id;
    public String text;
    public String picture;
    public ArrayList<Comment> comments;
    public ArrayList<PostLike> likes;
    public Profile profile;
    public GPS gps;
    public String dateCreated;
    public String lastUpdated;

    public PostPutRequest() {
    }

    public PostPutRequest(int id, String text, String picture, ArrayList<Comment> comments, ArrayList<PostLike> likes, Profile profile, GPS gps, String dateCreated, String lastUpdated) {
        this.id = id;
        this.text = text;
        this.picture = picture;
        this.comments = comments;
        this.likes = likes;
        this.profile = profile;
        this.gps = gps;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}

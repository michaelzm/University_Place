package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.GPS;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.PostLike;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class ProfilePostsGetResponse {
    public int id;
    public String text;
    public String picture;
    public ArrayList<Comment> comments;
    public ArrayList<PostLike> likes;
    public Profile profile;
    public GPS gps;
    public String dateCreated;
    public String lastUpdated;

    public ProfilePostsGetResponse() {
    }
}

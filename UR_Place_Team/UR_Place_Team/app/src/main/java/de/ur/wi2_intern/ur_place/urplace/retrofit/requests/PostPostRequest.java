package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.GPS;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.PostLike;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class PostPostRequest {
    public String text;
    public String picture;
    public GPS gps;

    public PostPostRequest() {

    }

    public PostPostRequest(String text, String picture, GPS gps) {
        this.text = text;
        this.picture = picture;
        this.gps = gps;
    }
}

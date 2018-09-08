package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.GPS;

public class CommentPostRequest {
    public String text;
    public String picture;
    public GPS gps;

    public CommentPostRequest(String text, String picture, GPS gps) {
        this.text = text;
        this.picture = picture;
        this.gps = gps;
    }
}

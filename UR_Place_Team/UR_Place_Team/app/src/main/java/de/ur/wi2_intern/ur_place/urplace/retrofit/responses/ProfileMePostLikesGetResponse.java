package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class ProfileMePostLikesGetResponse {
    public int id;
    public int post;
    public String text;
    public String picture;
    public Profile profile;
    public String dateCreated;
    public String lastUpdated;

    public ProfileMePostLikesGetResponse() {
    }
}

package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class ProfileMeCommentLikesGetResponse {
    public int id;
    public String comment;
    public Profile profile;
    public String dateCreated;
    public String lastUpdated;

    public ProfileMeCommentLikesGetResponse() {
    }
}

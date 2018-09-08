package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class ProfileMeDeclinedFriendRequestsGetResponse {
    public int id;
    public String friendshipRequestDate;
    public boolean accepted;
    public Profile from;
    public Profile to;
    public String dateCreated;
    public String lastUpdated;

    public ProfileMeDeclinedFriendRequestsGetResponse() {
    }
}

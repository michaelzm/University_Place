package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;


import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Friend;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;

public class ProfileMeFriendlistGetResponse {

    public int id;
    public Profile friend;
    public String friendshipRequestDate;
    public String friendshipAcceptedDate;
    public String dateCreated;
    public String lastUpdated;

    public ProfileMeFriendlistGetResponse() {
    }
}

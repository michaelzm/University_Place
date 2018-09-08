package de.ur.wi2_intern.ur_place.urplace.retrofit.models;

public class FriendRequest {
    public int id;
    public String friendshipRequestDate;
    public boolean accepted;
    public Profile from;
    public Profile to;
    public String dateCreated;
    public String lastUpdated;

    public FriendRequest() {
    }
}

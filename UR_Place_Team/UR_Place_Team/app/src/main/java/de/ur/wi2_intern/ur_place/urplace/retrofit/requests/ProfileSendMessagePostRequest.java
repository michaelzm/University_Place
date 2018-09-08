package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

public class ProfileSendMessagePostRequest {
    public String message;
    public String picture;

    public ProfileSendMessagePostRequest() {
    }

    public ProfileSendMessagePostRequest(String message, String picture) {
        this.message = message;
        this.picture = picture;
    }
}

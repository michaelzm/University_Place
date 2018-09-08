package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import java.util.ArrayList;

public class LoginResponse {
    public String username;
    public ArrayList<Role> roles;
    public String token_type;
    public String access_token;
    public int expires_in;
    public String refresh_roken;

    public LoginResponse() {
    }

}

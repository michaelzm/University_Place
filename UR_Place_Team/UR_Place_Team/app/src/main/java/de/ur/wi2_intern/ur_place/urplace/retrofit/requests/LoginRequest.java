package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

public class LoginRequest {
    public String username;
    public String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername (String u){
        username = u;
    }

    public void setPassword (String p){
        password = p;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}

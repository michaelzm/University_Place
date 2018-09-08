package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import de.ur.wi2_intern.ur_place.urplace.room.models.Gender;

public class RegisterRequest {
    //attributes could be private also


    public String username;
    public String password;
    public String nickname;
    public String firstname;
    public String lastname;
    public String birthdate;
    //birthdate ISO Formatted Date String 2018-08-08
    public Gender gender;
    public String profilePicture;


    public RegisterRequest(String un, String nn, String pw, String fn, String ln, String bd, Gender gd, String pp) {
        this.username = un;
        this.nickname = nn;
        this.password = pw;
        this.firstname = fn;
        this.lastname = ln;
        this.birthdate = bd;
        this.gender=gd;
        this.profilePicture=pp;
    }
}

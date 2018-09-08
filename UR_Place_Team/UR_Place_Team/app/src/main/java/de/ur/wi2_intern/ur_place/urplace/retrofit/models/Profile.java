package de.ur.wi2_intern.ur_place.urplace.retrofit.models;

import java.util.ArrayList;

public class Profile {
    public int id;
    public Gender gender;
    public String subject;
    public String nickname;
    public String firstname;
    public String lastname;
    public ArrayList<Hobby> hobbies;
    public String birthdate;
    public String profilePicture;
    public String dateCreated;
    public String lastUpdated;


    public Profile() {

    }

    public Profile(int id, Gender gender, String subject, String nickname, String firstname, String lastname, ArrayList<Hobby> hobbies, String birthdate, String profilePicture, String dateCreated, String lastUpdated) {
        this.id = id;
        this.gender = gender;
        this.subject = subject;
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hobbies = hobbies;
        this.birthdate = birthdate;
        this.profilePicture = profilePicture;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}

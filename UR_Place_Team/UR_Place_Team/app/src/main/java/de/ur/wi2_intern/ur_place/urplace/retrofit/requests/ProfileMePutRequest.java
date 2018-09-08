package de.ur.wi2_intern.ur_place.urplace.retrofit.requests;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Hobby;
import de.ur.wi2_intern.ur_place.urplace.room.models.Gender;

public class ProfileMePutRequest {
    public int id;
    public Gender gender;
    public String subject;
    public String nickname;
    public String firstname;
    public String lastname;
    public ArrayList<Hobby> hobbies;
    public String birthdate;
    public String profilePicture;

    public ProfileMePutRequest(int id, Gender gender, String subject, String nickname, String firstname, String lastname, ArrayList<Hobby> hobbies, String birthdate, String profilePicture) {
        this.id = id;
        this.gender = gender;
        this.subject = subject;
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hobbies = hobbies;
        this.birthdate = birthdate;
        this.profilePicture = profilePicture;
    }
}

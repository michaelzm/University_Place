package de.ur.wi2_intern.ur_place.urplace.retrofit.responses;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Hobby;
import de.ur.wi2_intern.ur_place.urplace.room.models.Gender;

public class ProfileMePatchResponse {
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
    public ProfileMePatchResponse(){

    }
}

package de.ur.wi2_intern.ur_place.urplace.room.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import de.ur.wi2_intern.ur_place.urplace.room.converter.GenderConverter;

@Entity(tableName = "profile")
public class Profile {
    @PrimaryKey
    @ColumnInfo(name="profileID")
    private Long profileID;
    @ColumnInfo(name = "prename")
    private String prename;
    @ColumnInfo(name = "lastname")
    private String lastname;
    @ColumnInfo(name = "nickname")
    private String nickname;
    @ColumnInfo(name = "gender")
    private Gender gender;
    @ColumnInfo(name = "birthdate")
    private Date birthdate;
    @ColumnInfo(name = "subject")
    private String subject;
    @ColumnInfo(name = "profilePicture")
    private String profilePicture;
    @ColumnInfo(name = "dateCreated")
    private Date dateCreated;
    @ColumnInfo(name = "lastUpdated")
    private Date lastUpdated;

    public Profile() {
    }

    public Profile(String prename, String lastname, String nickname, Gender gender, Date birthdate, String subject, String profilePicture) {
        this.prename = prename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.subject = subject;
        this.profilePicture = profilePicture;
        this.dateCreated = new Date();
        this.lastUpdated = new Date();
    }

    public Profile(long profileID, String prename, String lastname, String nickname, Gender gender, Date birthdate, String subject, String profilePicture) {
        this.profileID = profileID;
        this.prename = prename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.subject = subject;
        this.profilePicture = profilePicture;
        this.dateCreated = new Date();
        this.lastUpdated = new Date();
    }

    public Long getProfileID() {
        return profileID;
    }

    public void setProfileID(Long profileID) {
        this.profileID = profileID;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

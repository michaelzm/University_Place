package de.ur.wi2_intern.ur_place.urplace.Profile.models;

public class Profile {
    private long id;
    private String profilePicture;
    private String name;
    private int age;
    private String subject;
    private Gender gender;

    private Profile() {
    }

    public Profile(long id, String profilePicture, String name, int age, String subject, Gender gender) {
        this.name = name;
        this.age = age;
        this.subject = subject;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

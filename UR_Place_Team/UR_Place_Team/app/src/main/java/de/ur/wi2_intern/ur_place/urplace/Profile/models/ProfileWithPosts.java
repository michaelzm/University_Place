package de.ur.wi2_intern.ur_place.urplace.Profile.models;

import java.util.Collection;
import java.util.List;

public class ProfileWithPosts extends Profile{
    List<Post> posts;

    public ProfileWithPosts(long id, String profilePicture, String name, int age, String subject, Gender gender, List<Post> posts) {
        super(id, profilePicture, name, age, subject, gender);
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

package de.ur.wi2_intern.ur_place.urplace.Profile;

import java.util.Collection;
import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.Profile.models.Gender;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.Post;

public interface IMAView {
    void setProfileData(String name, int age, String subject, Gender gender);
    void setPostData(List<Post> posts);
    void showCommentSendToast();
    void showCommentSendErrorToast();
    void startProfileSearchActivity();
    void startFriendListActivity();
    void openNavigationDrawerMenu();

}

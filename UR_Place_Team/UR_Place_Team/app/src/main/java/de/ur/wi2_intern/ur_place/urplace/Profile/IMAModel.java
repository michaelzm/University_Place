package de.ur.wi2_intern.ur_place.urplace.Profile;

import de.ur.wi2_intern.ur_place.urplace.Profile.models.Profile;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.ProfileWithPosts;

public interface IMAModel {
    void saveProfile (ProfileWithPosts profile);

    ProfileWithPosts obtainProfile(Long id);

    int getProfileCount();

    boolean sendComment(Long postId,
                        String commentText,
                        String base64Picture,
                        Long latitude,
                        Long longitude);
}

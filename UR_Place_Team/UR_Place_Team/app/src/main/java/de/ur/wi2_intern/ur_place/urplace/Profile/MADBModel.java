package de.ur.wi2_intern.ur_place.urplace.Profile;

import android.util.Log;

import java.util.Date;
import java.util.List;
import java.util.Random;

import de.ur.wi2_intern.ur_place.urplace.Profile.models.ProfileWithPosts;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.DBDAO;
import de.ur.wi2_intern.ur_place.urplace.room.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.room.models.Gender;
import de.ur.wi2_intern.ur_place.urplace.room.models.Post;
import de.ur.wi2_intern.ur_place.urplace.room.models.Profile;

public class MADBModel implements IMAModel {
    public static final String TAG = "MADBModel";
    DB db;

    public MADBModel(DB db) {
        this.db = db;
    }

    @Override
    public int getProfileCount() {
        DBDAO dbdao = db.dbdao();
        return dbdao.profileCount();
    }

    @Override
    public void saveProfile(ProfileWithPosts profile) {
        Log.d(TAG, "START SAVING");
        DBDAO dbdao = db.dbdao();
        // dummy profile only
        Date birthdate = new Date();
        birthdate.setTime(654875999000l);
        Profile roomProfile = new Profile("Michael", "Bortlik", "Supportlik", Gender.MALE, birthdate, "Wirtschaftsinformatik", null);
        long profileId = dbdao.insertProfile(roomProfile);
        Post post = new Post(profileId, "Test Post", null, null, null);
        long postId = dbdao.insertPost(post);
        Comment comment = new Comment();
        long commentID = dbdao.insertComment(comment);
        Log.d(TAG, "START SAVING");
    }

    @Override
    public ProfileWithPosts obtainProfile(Long id) {
        DBDAO dbdao = db.dbdao();
        // TODO: Implement transformation DB viewmodel!

        return null;
    }

    @Override
    public boolean sendComment(Long postId, String commentText, String base64Picture, Long latitude, Long longitude) {
        // TODO network send comment
        Random random = new Random();
        return random.nextBoolean();
    }
}

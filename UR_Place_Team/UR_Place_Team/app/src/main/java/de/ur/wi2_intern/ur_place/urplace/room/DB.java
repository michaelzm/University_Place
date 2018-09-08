package de.ur.wi2_intern.ur_place.urplace.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import de.ur.wi2_intern.ur_place.urplace.room.converter.DateConverter;
import de.ur.wi2_intern.ur_place.urplace.room.converter.GenderConverter;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleRoom;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleCategories;
import de.ur.wi2_intern.ur_place.urplace.room.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.room.models.CommentLike;
import de.ur.wi2_intern.ur_place.urplace.room.models.DeclinedFriendOffers;
import de.ur.wi2_intern.ur_place.urplace.room.models.FriendOffers;
import de.ur.wi2_intern.ur_place.urplace.room.models.Friendship;
import de.ur.wi2_intern.ur_place.urplace.room.models.Post;
import de.ur.wi2_intern.ur_place.urplace.room.models.PostLike;
import de.ur.wi2_intern.ur_place.urplace.room.models.Profile;
import de.ur.wi2_intern.ur_place.urplace.room.models.Offer;

@Database(entities = {
        Comment.class, CommentLike.class, Friendship.class, Post.class, DeclinedFriendOffers.class,PostLike.class, Profile.class, Offer.class, FriendOffers.class,ArticleRoom.class, ArticleCategories.class
}, version = 1)

@TypeConverters({GenderConverter.class, DateConverter.class})
public abstract class DB extends RoomDatabase {
    private static DB INSTANCE;

    public abstract DBDAO dbdao();

    public static DB getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DB.class, "urplace_db")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    // TODO REMOVE!
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

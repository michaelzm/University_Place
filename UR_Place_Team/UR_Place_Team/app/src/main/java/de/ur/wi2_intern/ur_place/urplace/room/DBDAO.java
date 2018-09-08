package de.ur.wi2_intern.ur_place.urplace.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleRoom;

import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleCategories;
import de.ur.wi2_intern.ur_place.urplace.room.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.room.models.CommentLike;
import de.ur.wi2_intern.ur_place.urplace.room.models.DeclinedFriendOffers;
import de.ur.wi2_intern.ur_place.urplace.room.models.FriendOffers;
import de.ur.wi2_intern.ur_place.urplace.room.models.Post;
import de.ur.wi2_intern.ur_place.urplace.room.models.PostLike;
import de.ur.wi2_intern.ur_place.urplace.room.models.Profile;
import de.ur.wi2_intern.ur_place.urplace.room.models.Offer;

@Dao
public interface DBDAO {

    //profile related

    //->Querys

    @Query("SELECT count(*) FROM profile")
    int profileCount();

    @Query("SELECT * FROM profile")
    List<Profile> getAllProfiles();

    @Query("SELECT * FROM post")
    List<Post> getAllPosts();

    @Query("SELECT * FROM post_like")
    List<PostLike> getAllPostLikes();

    @Query("SELECT * FROM comment")
    List<Comment> getAllComments();

    @Query("SELECT * FROM profile WHERE profileID=:profileID")
    List<Profile> getSingleProfile(long profileID);

    @Query("SELECT * FROM post WHERE postID=:postID")
    List<Post> getSinglePost(long postID);

    @Query("SELECT * FROM comment WHERE commentID=:commentID")
    List<Comment> getSingleComment(long commentID);

    @Query("SELECT * FROM post WHERE profileID=:profileID")
    List<Post> getAllPostsFromUser(long profileID);

    @Query("SELECT * FROM comment WHERE postID=:postID")
    List<Comment> getAllCommentsFromPost(long postID);


    //->Inserts
    @Insert
    long insertProfile(Profile profile);

    @Insert
    long insertPost(Post post);

    @Insert
    long insertComment(Comment comment);

    @Insert
    long insertPostLike(PostLike postLike);

    @Insert
    long insertCommentLike(CommentLike commentLike);

    //-> Updates

    @Update
    void updateProfile(Profile profile);

    //friend related
    @Insert

    //->Inserts
    long insertFriendOffer(FriendOffers friendOffers);

    @Insert
    long insertDeclinedFriendOffer(DeclinedFriendOffers declinedFriendOffers);

    //->Queries
    @Query("SELECT * FROM friendOffers")
    List<FriendOffers> getAllFriendOffers();

    @Query("SELECT * FROM declinedFriendOffers")
    List<DeclinedFriendOffers> getAllDecliendFriendOffers();

    //->Deletes

    @Delete
    void deleteFriendOffer(FriendOffers friendOffers);

    //messaging related




    //marketplace related
    @Query("SELECT * FROM offer")
    List<Offer> getAllOffers();

    @Query("SELECT count(*) FROM offer")
    int offerCount();

    @Insert
    void insertOffer(Offer offer);

    @Insert
    void insertArticle(ArticleRoom article);

    @Query("SELECT * FROM Article WHERE bought = 0 ORDER BY created DESC")
    List<ArticleRoom> getAllArticles();

    @Query("SELECT * FROM Article WHERE favorite=1")
    List<ArticleRoom> getFavoriteArticles();

    @Query("SELECT * FROM Article WHERE sellerId=:id")
    List<ArticleRoom> getOwnArticles(int id);

    @Query("SELECT * FROM Article WHERE categoryTitle=:title")
    List<ArticleRoom> getArticleByCategory(String title);

    @Query("SELECT * FROM Article WHERE price BETWEEN :low and :high")
    List<ArticleRoom> getArticleByPrice(int low, int high);

    @Query("SELECT * FROM Article WHERE Title LIKE :title")
    List<ArticleRoom> getArticleBySearchTitle(String title);

    @Query("SELECT count(*) FROM article")
    int articleCount();

    //test


    @Query("DELETE FROM category")
    void nukeCategories();


    @Insert
    void insertCategory(ArticleCategories article);

    @Query("SELECT * FROM category")
    List<ArticleCategories> getAllCategories();

    @Query("SELECT count(*) FROM category")
    int articleCategoryCount();

    @Query("SELECT categoryID FROM category WHERE categoryTitle=:name")
    int getSpecificID(String name);

    @Query("SELECT * FROM article WHERE articleID=:id")
    ArticleRoom getArtilceById(int id);

    @Query("DELETE FROM article")
    void nukeArticles();

    @Update
    void updateArticle(ArticleRoom article);

}

package de.ur.wi2_intern.ur_place.urplace.retrofit;

import java.util.ArrayList;

import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ArticleCategoryIdPatchRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ArticleCategoryIdPutRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ArticleCategoryPostRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ArticleIdPatchRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ArticleIdPutRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ArticlePostRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.CommentPatchRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.CommentPostRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.CommentPutRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.LoginRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.PostPatchRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.PostPostRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.PostPutRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ProfileMePatchRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ProfileMePutRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.ProfileSendMessagePostRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.RegisterRequest;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleBuyGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleCategoryGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleCategoryIdDeleteResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleCategoryIdGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleCategoryIdPatchResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleCategoryIdPutResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleCategoryPostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleIdDeleteResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleIdGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleIdPatchResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleIdPutResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticlePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleSearchGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ArticleSellGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.CommentDeleteResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.CommentGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.CommentLikePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.CommentPatchResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.CommentPostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.CommentPutResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.CommentUnlikePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.FriendsAcceptPostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.FriendsDeclinePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.FriendsUnfriendDeleteResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.LoginResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.MarketplaceConverdationGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.MarketplaceConverdationIdGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.PostDeleteResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.PostGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.PostLikePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.PostPatchResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.PostPostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.PostPutResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.PostUnlikePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileAskForFriendshipPostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileFriendlistGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMeCommentLikesGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMeCommentsGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMeDeclinedFriendRequestsGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMeFriendlistGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMeGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMeOpenFriendRequestsGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMePatchResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMePostLikesGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMePostsGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMePutResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfilePostLikesGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfilePostsGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileSearchGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileCommentLikesGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileCommentsGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileSendMessagePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PDPServiceRepo {


    //Account
    @POST("/account/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/account/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);



    //MyProfile
    @GET("/profile/me")
    Call<ProfileMeGetResponse> profileMeGet(@Header("Authorization") String authorization);

    @PUT("/profile/me")
    Call<ProfileMePutResponse> profileMePut(@Header("Authorization") String authorization, @Body ProfileMePutRequest profileMePutRequest);

    @PATCH("/profile/me")
    Call<ProfileMePatchResponse> profileMePatch(@Header("Authorization") String authorization, @Body ProfileMePatchRequest profileMePatchRequest);

    @GET("/profile/me/friendlist")
    Call<ArrayList<ProfileMeFriendlistGetResponse>> profileMeFriendlistGet(@Header("Authorization") String authorization);

    @GET("/profile/me/open_friend_requests")
    Call<ArrayList<ProfileMeOpenFriendRequestsGetResponse>> profileMeOpenFriendRequestsGet(@Header("Authorization") String authorization);

    @GET("/profile/me/declined_friend_requests")
    Call<ArrayList<ProfileMeDeclinedFriendRequestsGetResponse>> profileMeDeclinedFriendRequestsGet(@Header("Authorization") String authorization);

    @GET("/profile/me/posts")
    Call<ArrayList<ProfileMePostsGetResponse>> profileMePostsGet(@Header("Authorization") String authorization);

    @GET("/profile/me/comments")
    Call<ArrayList<ProfileMeCommentsGetResponse>> profileMeCommentsGet(@Header("Authorization") String authorization);

    @GET("/profile/me/post_likes")
    Call<ArrayList<ProfileMePostLikesGetResponse>> profileMePostLikesGet(@Header("Authorization") String authorization);

    @GET("/profile/me/comment_likes")
    Call<ArrayList<ProfileMeCommentLikesGetResponse>> profileMeCommentLikesGet(@Header("Authorization") String authorization);



    //Profile
    @GET("/profile/search/{searchTerm}")
    Call<ArrayList<ProfileSearchGetResponse>> profileSearchGet(@Header("Authorization") String authorization, @Path("searchTerm") String searchTerm);

    @GET("/profile/{id}")
    Call<ProfileGetResponse> profileGet(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("/profile/{id}/friendlist")
    Call<ArrayList<ProfileFriendlistGetResponse>> profileFriendlistGet(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("/profile/{id}/posts")
    Call<ArrayList<ProfilePostsGetResponse>> profilePostsGet(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("/profile/{id}/comments")
    Call<ArrayList<ProfileCommentsGetResponse>> profileCommentsGet(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("/profile/{id}/post_likes")
    Call<ArrayList<ProfilePostLikesGetResponse>> profilePostLikesGet(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("/profile/{id}/comment_likes")
    Call<ArrayList<ProfileCommentLikesGetResponse>> profileCommentLikesGet(@Header("Authorization") String authorization, @Path("id") int id);

    @POST("/profile/{id}/send_message")
    Call<ProfileSendMessagePostResponse> profileSendMessagePost(@Header("Authorization") String authorization, @Path("id") int id, @Body ProfileSendMessagePostRequest profileSendMessagePostRequest);



    //Post
    @POST("/post")
    Call<PostPostResponse> postPost(@Header("Authorization") String authorization, @Body PostPostRequest postPostRequest);

    @GET("/post/{id}")
    Call<PostGetResponse> postGet(@Header("Authorization") String authorization, @Path("id") int id);

    @PUT("/post/{id}")
    Call<PostPutResponse> postPut(@Header("Authorization") String authorization, @Path("id") int id, @Body PostPutRequest postPostRequest);

    @PATCH("/post/{id}")
    Call<PostPatchResponse> postPatch(@Header("Authorization") String authorization, @Path("id") int id, @Body PostPatchRequest postPostRequest);

    @DELETE("/post/{id}")
    Call<PostDeleteResponse> postDelete(@Header("Authorization") String authorization, @Path("id") int id);

    @POST("/post/{id}/like")
    Call<PostLikePostResponse> postLikePost(@Header("Authorization") String authorization, @Path("id") int id);

    @POST("/post/{id}/unlike")
    Call<PostUnlikePostResponse> postUnlikePost(@Header("Authorization") String authorization, @Path("id") int id);



    //Comment
    @POST("/post/{id}/comment")
    Call<CommentPostResponse> commentPost(@Header("Authorization") String authorization, @Path("id") int id, @Body CommentPostRequest commentPostRequest);

    @GET("/comment/{id}")
    Call<CommentGetResponse> commentGet(@Header("Authorization") String authorization, @Path("id") int id);

    @PUT("/comment/{id}")
    Call<CommentPutResponse> commentPut(@Header("Authorization") String authorization, @Path("id") int id, @Body CommentPutRequest commentPostRequest);

    @PATCH("/comment/{id}")
    Call<CommentPatchResponse> commentPatch(@Header("Authorization") String authorization, @Path("id") int id, @Body CommentPatchRequest commentPostRequest);

    @DELETE("/comment/{id}")
    Call<CommentDeleteResponse> commentDelete(@Header("Authorization") String authorization, @Path("id") int id);

    @POST("/comment/{id}/like")
    Call<CommentLikePostResponse> commentLikePost(@Header("Authorization") String authorization, @Path("id") int id);

    @POST("/comment/{id}/unlike")
    Call<CommentUnlikePostResponse> commentUnlikePost(@Header("Authorization") String authorization, @Path("id") int id);



    //Friends
    @POST("/profile/{id}/ask_for_friendship")
    Call<ProfileAskForFriendshipPostResponse> profileAskForFriendshipPost(@Header("Authorization") String authorization, @Path("id") int id);

    @POST("/friendship/{id}/accept")
    Call<FriendsAcceptPostResponse> friendsAcceptPost(@Header("Authorization") String authorization, @Path("id") int id);

    @POST("/friendship/{id}/decline")
    Call<FriendsDeclinePostResponse> friendsDeclinePost(@Header("Authorization") String authorization, @Path("id") int id);

    @DELETE("/friendship/{id}/unfriend")
    Call<FriendsUnfriendDeleteResponse> friendsUnfriendDelete(@Header("Authorization") String authorization, @Path("id") int id);



    //Market Place
    // article_category
    @GET("/article_category")
    Call<ArrayList<ArticleCategoryGetResponse>> articleCategoryGet(@Header("Authorization") String authorization);

    @POST("/article_category")
    Call<ArticleCategoryPostResponse> articleCategoryPost(@Header("Authorization") String authorization, @Body ArticleCategoryPostRequest articleCategoryPostRequest);

    @GET("/article_category/{id}")
    Call<ArticleCategoryIdGetResponse> articleCategoryIdGet(@Header("Authorization") String authorization, @Path("id") int id);

    @PUT("/article_category/{id}")
    Call<ArticleCategoryIdPutResponse> articleCategoryIdPut(@Header("Authorization") String authorization, @Path("id") int id, @Body ArticleCategoryIdPutRequest articleCategoryIdPutRequest);

    @PATCH("/article_category/{id}")
    Call<ArticleCategoryIdPatchResponse> articleCategoryIdPatch(@Header("Authorization") String authorization, @Path("id") int id, @Body ArticleCategoryIdPatchRequest articleCategoryIdPatchRequest);

    @DELETE("/article_category/{id}")
    Call<ArticleCategoryIdDeleteResponse> articleCategoryIdDelete(@Header("Authorization") String authorization, @Path("id") int id);

    //article
    @GET("/article/search/{searchterm}")
    Call<ArrayList<ArticleSearchGetResponse>> articleSearchGet(@Header("Authorization") String authorization, @Path("searchterm") String searchterm);

    @GET("/article")
    Call<ArrayList<ArticleGetResponse>> articleGet(@Header("Authorization") String authorization);

    @POST("/article")
    Call<ArticlePostResponse> articlePost(@Header("Authorization") String authorization, @Body ArticlePostRequest articlePostRequest);

    @GET("/article/sell")
    Call<ArrayList<ArticleSellGetResponse>> articleSellGet(@Header("Authorization") String authorization);

    @GET("/article/buy")
    Call<ArrayList<ArticleBuyGetResponse>> articleBuyGet(@Header("Authorization") String authorization);

    @GET("/article/{id}")
    Call<ArticleIdGetResponse> articleIdGet(@Header("Authorization") String authorization, @Path("id") int id);

    @PUT("/article/{id}")
    Call<ArticleIdPutResponse> articleIdPut(@Header("Authorization") String authorization, @Path("id") int id, @Body ArticleIdPutRequest articleIdPutRequest);

    @PATCH("/article/{id}")
    Call<ArticleIdPatchResponse> articleIdPatch(@Header("Authorization") String authorization, @Path("id") int id, @Body ArticleIdPatchRequest articleIdPatchRequest);

    @DELETE("/article/{id}")
    Call<ArticleIdDeleteResponse> articleIdDelete(@Header("Authorization") String authorization, @Path("id") int id);

    //conversation
    @GET("/conversation")
    Call<MarketplaceConverdationGetResponse> marketplaceConverdationGet(@Header("Authorization") String authorization);

    @GET("/conversation/{id}")
    Call<MarketplaceConverdationIdGetResponse> marketplaceConverdationIdGet(@Header("Authorization") String authorization, @Path("id") int id);

}

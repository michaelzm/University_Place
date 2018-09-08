package de.ur.wi2_intern.ur_place.urplace.retrofit;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.OptionsActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleCategory;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleContactData;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.CommentLike;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.GPS;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Hobby;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Post;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.PostLike;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Profile;
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
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileCommentLikesGetResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileCommentsGetResponse;
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
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileSendMessagePostResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.RegisterResponse;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.Role;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.converter.DateConverter;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleCategories;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleRoom;
import de.ur.wi2_intern.ur_place.urplace.room.models.DeclinedFriendOffers;
import de.ur.wi2_intern.ur_place.urplace.room.models.FriendOffers;
import de.ur.wi2_intern.ur_place.urplace.room.models.Gender;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static de.ur.wi2_intern.ur_place.urplace.room.converter.ArticleTypeConverter.stringToArticleType;

public class NetworkService extends IntentService {
    public static final String TAG = "NetworkService";
    //Database Connection Room

    //Login/Register/Update
    public static final String LOGIN_ACTION = "LOGIN";
    public static final String REGISTER_ACTION = "REGISTER";
    public static final String UPDATE_ACTION = "UPDATE";

    //MyProfile
    public static final String PROFILEMEGET_ACTION = "PROFILEMEGET";
    public static final String PROFILEMEPUT_ACTION = "PROFILEMEPUT";
    public static final String PROFILEMEPATCH_ACTION = "PROFILEMEPATCH";
    public static final String PROFILEMEFRIENDLISTGET_ACTION = "PROFILEMEFRIENDLISTGET";
    public static final String PROFILEMEOPENFRIENDREQUESTSGET_ACTION = "PROFILEMEOPENFRIENDREQUESTGET";
    public static final String PROFILEMEDECLINEDFRIENDREQUESTGET_ACTION = "PROFILEMEDECLINEDFRIENDREQUESTGET";
    public static final String PROFILEMEPOSTSGET_ACTION = "PROFILEMEPOSTSGET";
    public static final String PROFILEMECOMMENTSGET_ACTION = "PROFILEMECOMMENTSGET";
    public static final String PROFILEMEPOSTLIKESGET_ACTION = "PROFILEMEPOSTLIKESGET";
    public static final String PROFILEMECOMMENTLIKESGET_ACTION = "PROFILEMECOMMENTLIKESGET";

    //Profile
    public static final String PROFILESEARCHGET_ACTION = "PROFILESEARCHGET";
    public static final String PROFILEGET_ACTION = "PROFILEGET";
    public static final String PROFILEFRIENDLIST_ACTION = "PROFILEFRIENDLIST";
    public static final String PROFILEPOSTSGET_ACTION = "PROFILEPOSTSGET";
    public static final String PROFILECOMMENTSGET_ACTION = "PROFILECOMMENTSGET";
    public static final String PROFILEPOSTLIKESGET_ACTION = "PROFILEPOSTLIKESGET";
    public static final String PROFILECOMMENTLIKESGET_ACTION = "PROFILECOMMENTLIKESGET";
    public static final String PROFILESENDMESSAGEPOST_ACTION = "PROFILESENDMESSAGEGET";

    //Post
    public static final String POSTPOST_ACTION = "POSTPOST";
    public static final String POSTGET_ACTION = "POSTGET";
    public static final String POSTPUT_ACTION = "POSTPUT";
    public static final String POSTPATCH_ACTION = "POSTPATCH";
    public static final String POSTDELETE_ACTION = "POSTDELETE";
    public static final String POSTLIKEPOST_ACTION = "POSTLIKEPOST";
    public static final String POSTUNLIKEPOST_ACTION = "POSTUNLIKEPOST";

    //Comment
    public static final String COMMENTPOST_ACTION = "COMMENTPOST";
    public static final String COMMENTGET_ACTION = "COMMENTGET";
    public static final String COMMENTPUT_ACTION = "COMMENTPUT";
    public static final String COMMENTPATCH_ACTION = "COMMENTPATCH";
    public static final String COMMENTDELETE_ACTION = "COMMENTDELETE";
    public static final String COMMENTLIKEPOST_ACTION = "COMMENTLIKEPOST";
    public static final String COMMENTUNLIKEPOST_ACTION = "COMMENTUNLIKEPOST";

    //Friends
    public static final String PROFILEASKFORFRIENDSHIPPOST_ACTION = "PROFILEASKFORFRIENDSHIPPOST";
    public static final String FRIENDSFRIENDSHIPACCEPTPOST_ACTION = "FRIENDSFRIENDSHIPACCEPTPOST";
    public static final String FRIENDSFRIENDSHIPDECLINEPOST_ACTION = "FRIENDSFRIENDSHIPDECLINEPOST";
    public static final String FRIENDSFRIENDSHIPUNFRIENDELETE_ACTION = "FRIENDSFRIENDSHIPUNFRIENDELETE";

    //Market Place
    //article_category
    public static final String ARTICLECATEGORYGET_ACTION = "ARTICLECATEGORYGET";
    public static final String ARTICLECATEGORYPOST_ACTION = "ARTICLECATEGORYPOST";
    public static final String ARTICLECATEGORYIDGET_ACTION = "ARTICLECATEGORYIDGET";
    public static final String ARTICLECATEGORYIDPUT_ACTION = "ARTICLECATEGORYIDPUT";
    public static final String ARTICLECATEGORYIDPATCH_ACTION = "ARTICLECATEGORYIDPATCH";
    public static final String ARTICLECATEGORYIDDELETE_ACTION = "ARTICLECATEGORYIDDELETE";
    //article
    public static final String ARTICLESEARCHGET_ACTION = "ARTICLESEARCHGET";
    public static final String ARTICLEGET_ACTION = "ARTICLEGET";
    public static final String ARTICLEPOST_ACTION = "ARTICLEPOST";
    public static final String ARTICLESELLGET_ACTION = "ARTICLESELLGET";
    public static final String ARTICLEBUYGET_ACTION = "ARTICLEBUYGET";
    public static final String ARTICLEIDGET_ACTION = "ARTICLEIDGET";
    public static final String ARTICLEIDPUT_ACTION = "ARTICLEIDPUT";
    public static final String ARTICLEIDPATCH_ACTION = "ARTICLEIDPATCH";
    public static final String ARTICLEIDDELETE_ACTION = "ARTICLEIDDELETEGET";
    //conversation
    public static final String MARKETPLACECOMVERSATIONGET_ACTION = "MARKETPLACECOMVERSATIONGET";
    public static final String MARKETPLACECOMVERSATIONIDGET_ACTION = "MARKETPLACECOMVERSATIONIDGET";

    public static final String TITLE_KEY = "title";
    public static final String DESCRIPTION_KEY = "description";
    public static final String ARTICLECATEGORYNAME_KEY = "categorytitle";
    public static final String NAME_KEY = "name";
    public static final String STREET_KEY = "street";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";
    public static final String CATEGORY_KEY = "category";
    public static final String IDCATEGORY_KEY = "idcategory";
    public static final String INSHOPUNTIL_KEY = "inShopUntil";
    public static final String PRICE_KEY = "price";
    public static final String ARTICLETYPE_KEY = "type";
    public static final String ARTICLEBOUGHT_KEY = "bought";

    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";
    public static final String NICKNAME_KEY = "nickname";
    public static final String FIRSTNAME_KEY = "firstname";
    public static final String LASTNAME_KEY = "lastname";
    public static final String BIRTHDATE_KEY = "birthdate";
    public static final String GENDERD_KEY = "gender";
    public static final String PICTURE_KEY = "picture";
    public static final String ID_KEY = "id";
    public static final String SUBJECT_KEY = "subject";
    public static final String SEARCH_KEY = "search";
    public static final String MESSAGE_KEY = "message";
    public static final String TEXT_KEY = "text";
    public static final String GPS_LA_KEY = "gps_la";
    public static final String GPS_LO_KEY = "gps_lo";


    public NetworkService() {
        super(TAG);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wi2-intern.ur.de:40208/").addConverterFactory(GsonConverterFactory.create())
                .build();
        PDPServiceRepo service = retrofit.create(PDPServiceRepo.class);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String authorization = "Bearer " + preferences.getString("access_token", "");
        Bundle extras;
        int idGet = 0;

        //callablePMCLGR (=Profile Me Comment Likes Get Response)...

        switch (intent.getAction()) {
            //Login/Register/Update
            case LOGIN_ACTION:
                extras = intent.getExtras();

                String username = extras.getString(USERNAME_KEY, "");
                final String password = extras.getString(PASSWORD_KEY, "");

                LoginRequest loginRequest = new LoginRequest(username, password);
                Call<LoginResponse> callable = service.login(loginRequest);
                callable.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: LOGIN SUCCESS ! => AT: " + response.body().access_token);
                            Toast.makeText(NetworkService.this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();

                            //gibt alle Daten des Loginrequests zum speichern weiter
                            saveLoginResponse(response.body().username, password, response.body().roles, response.body().token_type, response.body().access_token, response.body().expires_in, response.body().refresh_roken);
                            setLogin();

                            Intent implicitIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(implicitIntent);
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: Not a valid user!");
                            Toast.makeText(NetworkService.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        Toast.makeText(NetworkService.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();

                    }
                });

                break;
            case REGISTER_ACTION:
                extras = intent.getExtras();

                final String usernameR = extras.getString(USERNAME_KEY, "");
                final String passwordR = extras.getString(PASSWORD_KEY, "");
                String nickname = extras.getString(NICKNAME_KEY, "");
                String firstname = extras.getString(FIRSTNAME_KEY, "");
                String lastname = extras.getString(LASTNAME_KEY, "");
                String birthdate = extras.getString(BIRTHDATE_KEY, "");
                Gender gender = getGenderFromString(extras.getString(GENDERD_KEY, ""));
                //String profilepicture = extras.getString(PICTURE_KEY,"");
                String profilepicture = preferences.getString("picture", "");

                RegisterRequest registerRequest = new RegisterRequest(usernameR, nickname, passwordR, firstname, lastname, birthdate, gender, profilepicture);
                Call<RegisterResponse> callableR = service.register(registerRequest);
                callableR.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: REGISTER SUCCESS ! => AT: " + response.body().id);
                            Toast.makeText(NetworkService.this, "REGISTER SUCCESS", Toast.LENGTH_SHORT).show();
                            saveRegisterResponse(response.body().id);

                            Intent loginIntent = new Intent(getApplicationContext(), NetworkService.class);
                            loginIntent.setAction(LOGIN_ACTION);
                            loginIntent.putExtra(USERNAME_KEY, usernameR);
                            loginIntent.putExtra(PASSWORD_KEY, passwordR);
                            startService(loginIntent);
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: Not a valid imput!");
                            Toast.makeText(NetworkService.this, "REGISTER FAILED", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        Toast.makeText(NetworkService.this, "REGISTER FAILED", Toast.LENGTH_SHORT).show();

                    }
                });
                break;
                /* ULT
            case UPDATE_ACTION:

                String usernameUpdate = preferences.getString("username", "u");
                final String passwordUpdate = preferences.getString("password", "p");

                loginRequest = new LoginRequest(usernameUpdate, passwordUpdate);
                callable = service.login(loginRequest);
                callable.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: TOKENUPDATE SUCCESS ! => AT: " + response.body().access_token);
                            Toast.makeText(NetworkService.this, "TOKENUPDATE SUCCESS", Toast.LENGTH_SHORT).show();

                            //gibt alle Daten des Loginrequests zum speichern weiter
                            saveLoginResponse(response.body().username, passwordUpdate, response.body().roles, response.body().token_type, response.body().access_token, response.body().expires_in, response.body().refresh_roken);

                        }catch (NullPointerException e){
                            Log.e(TAG, "onRespone: Not a valid user!");
                            Toast.makeText(NetworkService.this, "TOKENUPDATE FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        Toast.makeText(NetworkService.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();

                    }
                });
                break; */

            //MyProfile
            case PROFILEMEGET_ACTION:
                Call<ProfileMeGetResponse> callablePMGR = service.profileMeGet(authorization);
                callablePMGR.enqueue(new Callback<ProfileMeGetResponse>() {
                    @Override
                    public void onResponse(Call<ProfileMeGetResponse> call, Response<ProfileMeGetResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: PROFILEMEGET SUCCESS ! => AT: " + response.body().id);
                            saveID(response.body().id);
                            saveBD(response.body().birthdate);

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("picture", response.body().profilePicture);
                            editor.commit();

                            //writes profile to db
                            final de.ur.wi2_intern.ur_place.urplace.room.models.Profile profile = new de.ur.wi2_intern.ur_place.urplace.room.models.Profile(
                                    response.body().firstname, response.body().lastname, response.body().nickname, response.body().gender, null,
                                    response.body().subject, response.body().profilePicture
                            );
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    DB db = DB.getAppDatabase(getApplicationContext());
                                    db.dbdao().insertProfile(profile);
                                }
                            }).start();


                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: PROFILEMEGET FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileMeGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMEPUT_ACTION:
                extras = intent.getExtras();


                int id = extras.getInt(ID_KEY, 0);
                gender = getGenderFromString(extras.getString(GENDERD_KEY, ""));
                String subject = extras.getString(SUBJECT_KEY, "");
                nickname = extras.getString(NICKNAME_KEY, "");
                firstname = extras.getString(FIRSTNAME_KEY, "");
                lastname = extras.getString(LASTNAME_KEY, "");
                ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
                birthdate = extras.getString(BIRTHDATE_KEY, "");
                //String profilepicture = extras.getString(PICTURE_KEY,"");
                profilepicture = preferences.getString("picture", "defValue");


                ProfileMePutRequest profileMePut = new ProfileMePutRequest(id, gender, subject, nickname, firstname, lastname, hobbies, birthdate, profilepicture);
                Call<ProfileMePutResponse> callablePMPuR = service.profileMePut(authorization, profileMePut);
                callablePMPuR.enqueue(new Callback<ProfileMePutResponse>() {
                    @Override
                    public void onResponse(Call<ProfileMePutResponse> call, Response<ProfileMePutResponse> response) {
                        try {
                            long pid = response.body().id;
                            //saves to db
                            de.ur.wi2_intern.ur_place.urplace.room.models.Profile pro = new de.ur.wi2_intern.ur_place.urplace.room.models.Profile(pid,
                                    response.body().firstname, response.body().lastname, response.body().nickname, response.body().gender, null,
                                    response.body().subject, response.body().profilePicture
                            );

                            DB db = DB.getAppDatabase(getApplicationContext());
                            db.dbdao().updateProfile(pro);

                            Log.d(TAG, "onResponse: SUCCESS!");

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileMePutResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMEPATCH_ACTION:
                extras = intent.getExtras();

                id = extras.getInt(ID_KEY, 0);
                gender = getGenderFromString(extras.getString(GENDERD_KEY, ""));
                subject = extras.getString(SUBJECT_KEY, "");
                nickname = extras.getString(NICKNAME_KEY, "");
                firstname = extras.getString(FIRSTNAME_KEY, "");
                lastname = extras.getString(LASTNAME_KEY, "");
                hobbies = new ArrayList<Hobby>();
                birthdate = extras.getString(BIRTHDATE_KEY, "");
                //String profilepicture = extras.getString(PICTURE_KEY,"");
                profilepicture = preferences.getString("picture", "");

                ProfileMePatchRequest profileMePatch = new ProfileMePatchRequest(id, gender, subject, nickname, firstname, lastname, hobbies, birthdate, profilepicture);

                Call<ProfileMePatchResponse> callablePMPaR = service.profileMePatch(authorization, profileMePatch);
                callablePMPaR.enqueue(new Callback<ProfileMePatchResponse>() {
                    @Override
                    public void onResponse(Call<ProfileMePatchResponse> call, Response<ProfileMePatchResponse> response) {
                        try {
                            long pid = response.body().id;
                            //saves to db
                            de.ur.wi2_intern.ur_place.urplace.room.models.Profile pro = new de.ur.wi2_intern.ur_place.urplace.room.models.Profile(pid,
                                    response.body().firstname, response.body().lastname, response.body().nickname, response.body().gender, null,
                                    response.body().subject, response.body().profilePicture
                            );
                            DB db = DB.getAppDatabase(getApplicationContext());
                            db.dbdao().updateProfile(pro);

                            Log.d(TAG, "onResponse: SUCCESS!");

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileMePatchResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMEFRIENDLISTGET_ACTION:
                Call<ArrayList<ProfileMeFriendlistGetResponse>> callablePMFGR = service.profileMeFriendlistGet(authorization);
                callablePMFGR.enqueue(new Callback<ArrayList<ProfileMeFriendlistGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileMeFriendlistGetResponse>> call, Response<ArrayList<ProfileMeFriendlistGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //durchlauft alle zurückgegebenen profile
                            for (ProfileMeFriendlistGetResponse profile : response.body()) {

                                //saves to db
                                de.ur.wi2_intern.ur_place.urplace.room.models.Profile p = new de.ur.wi2_intern.ur_place.urplace.room.models.Profile(
                                        profile.friend.firstname, profile.friend.lastname, profile.friend.nickname, null,
                                        null, profile.friend.subject, profile.friend.profilePicture
                                );
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertProfile(p);


                                //profile.friend.id ruft id des freundes auf
                                Log.d(TAG, "onResponse: SUCCESS!" + profile.friend.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileMeFriendlistGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMEOPENFRIENDREQUESTSGET_ACTION:
                Call<ArrayList<ProfileMeOpenFriendRequestsGetResponse>> callablePMOGR = service.profileMeOpenFriendRequestsGet(authorization);
                callablePMOGR.enqueue(new Callback<ArrayList<ProfileMeOpenFriendRequestsGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileMeOpenFriendRequestsGetResponse>> call, Response<ArrayList<ProfileMeOpenFriendRequestsGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //durchlauft alle zurückgegebenen profile
                            for (ProfileMeOpenFriendRequestsGetResponse friendRequestList : response.body()) {

                                //saves friendoffers to db
                                FriendOffers f = new FriendOffers(friendRequestList.from.id);
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertFriendOffer(f);


                                //friendRequestList.from.id ruft id des anfragers auf
                                Log.d(TAG, "onResponse: SUCCESS!" + friendRequestList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileMeOpenFriendRequestsGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMEDECLINEDFRIENDREQUESTGET_ACTION:
                Call<ArrayList<ProfileMeDeclinedFriendRequestsGetResponse>> callablePMDGR = service.profileMeDeclinedFriendRequestsGet(authorization);
                callablePMDGR.enqueue(new Callback<ArrayList<ProfileMeDeclinedFriendRequestsGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileMeDeclinedFriendRequestsGetResponse>> call, Response<ArrayList<ProfileMeDeclinedFriendRequestsGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileMeDeclinedFriendRequestsGetResponse friendDeclinedList : response.body()) {
                                //saves to db

                                DeclinedFriendOffers f = new DeclinedFriendOffers(friendDeclinedList.from.id);
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertDeclinedFriendOffer(f);

                                //friendDeclinedList.from.id ruft id des anfragers auf
                                Log.d(TAG, "onResponse: SUCCESS!" + friendDeclinedList.id);
                            }

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileMeDeclinedFriendRequestsGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMEPOSTSGET_ACTION:
                Call<ArrayList<ProfileMePostsGetResponse>> callablePMPGR = service.profileMePostsGet(authorization);
                callablePMPGR.enqueue(new Callback<ArrayList<ProfileMePostsGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileMePostsGetResponse>> call, Response<ArrayList<ProfileMePostsGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileMePostsGetResponse postList : response.body()) {

                                //saves Post and comments to db
                                de.ur.wi2_intern.ur_place.urplace.room.models.Post p = new de.ur.wi2_intern.ur_place.urplace.room.models.Post(
                                        Long.valueOf(postList.profile.id), postList.text, postList.picture, null, null);
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertPost(p);

                                for (Comment comment : postList.comments) {
                                    de.ur.wi2_intern.ur_place.urplace.room.models.Comment c = new de.ur.wi2_intern.ur_place.urplace.room.models.Comment(
                                            comment.id, comment.post, comment.profile.id, comment.text, comment.picture);
                                    db.dbdao().insertComment(c);
                                }


                                //postList.id ruft id des posts auf
                                Log.d(TAG, "onResponse: SUCCESS!" + postList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileMePostsGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMECOMMENTSGET_ACTION:
                Call<ArrayList<ProfileMeCommentsGetResponse>> callablePMCGR = service.profileMeCommentsGet(authorization);
                callablePMCGR.enqueue(new Callback<ArrayList<ProfileMeCommentsGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileMeCommentsGetResponse>> call, Response<ArrayList<ProfileMeCommentsGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileMeCommentsGetResponse commentList : response.body()) {

                                //saves comments to db
                                de.ur.wi2_intern.ur_place.urplace.room.models.Comment c = new de.ur.wi2_intern.ur_place.urplace.room.models.Comment(
                                        commentList.id, commentList.post, commentList.profile.id, commentList.text, commentList.picture
                                );
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertComment(c);

                                //commentList.id ruft id des comments auf
                                Log.d(TAG, "onResponse: SUCCESS!" + commentList.id);
                            }

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileMeCommentsGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMEPOSTLIKESGET_ACTION:
                Call<ArrayList<ProfileMePostLikesGetResponse>> callablePMPLGR = service.profileMePostLikesGet(authorization);
                callablePMPLGR.enqueue(new Callback<ArrayList<ProfileMePostLikesGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileMePostLikesGetResponse>> call, Response<ArrayList<ProfileMePostLikesGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileMePostLikesGetResponse postLikeList : response.body()) {

                                //saves to DB
                                de.ur.wi2_intern.ur_place.urplace.room.models.PostLike p = new de.ur.wi2_intern.ur_place.urplace.room.models.PostLike(
                                        postLikeList.id, postLikeList.profile.id, postLikeList.post
                                );
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertPostLike(p);
                                //postLikeList.id ruft id des postLikes auf
                                Log.d(TAG, "onResponse: SUCCESS!" + postLikeList.id);
                            }

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileMePostLikesGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEMECOMMENTLIKESGET_ACTION:
                Call<ArrayList<ProfileMeCommentLikesGetResponse>> callablePMCLGR = service.profileMeCommentLikesGet(authorization);
                callablePMCLGR.enqueue(new Callback<ArrayList<ProfileMeCommentLikesGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileMeCommentLikesGetResponse>> call, Response<ArrayList<ProfileMeCommentLikesGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileMeCommentLikesGetResponse commentLikeList : response.body()) {
                                //saves to db
                                de.ur.wi2_intern.ur_place.urplace.room.models.CommentLike c = new de.ur.wi2_intern.ur_place.urplace.room.models.CommentLike(
                                        commentLikeList.id, commentLikeList.profile.id
                                );

                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertCommentLike(c);


                                //commentLikeList.id ruft id des commentLikes auf
                                Log.d(TAG, "onResponse: SUCCESS!" + commentLikeList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileMeCommentLikesGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;
            //add_Hobby not needed
            //remove_hobby not needed


            //Profile
            case PROFILESEARCHGET_ACTION:
                extras = intent.getExtras();

                String searchTerm = extras.getString(SEARCH_KEY, "");

                Call<ArrayList<ProfileSearchGetResponse>> callablePSGR = service.profileSearchGet(authorization, searchTerm);
                callablePSGR.enqueue(new Callback<ArrayList<ProfileSearchGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileSearchGetResponse>> call, Response<ArrayList<ProfileSearchGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileSearchGetResponse searchList : response.body()) {
                                //TODO broadcast intent


                                //searchList.id ruft id des searches auf
                                Log.d(TAG, "onResponse: SUCCESS!" + searchList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileSearchGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ProfileGetResponse> callablePGR = service.profileGet(authorization, idGet);
                callablePGR.enqueue(new Callback<ProfileGetResponse>() {
                    @Override
                    public void onResponse(Call<ProfileGetResponse> call, Response<ProfileGetResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            de.ur.wi2_intern.ur_place.urplace.room.models.Profile p = new de.ur.wi2_intern.ur_place.urplace.room.models.Profile(

                            );

                            //TODO no response


                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEFRIENDLIST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArrayList<ProfileFriendlistGetResponse>> callablePFGR = service.profileFriendlistGet(authorization, idGet);
                callablePFGR.enqueue(new Callback<ArrayList<ProfileFriendlistGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileFriendlistGetResponse>> call, Response<ArrayList<ProfileFriendlistGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileFriendlistGetResponse friendList : response.body()) {
                                //not necessary for basic functions
                                //friendList.id ruft id des friends auf
                                Log.d(TAG, "onResponse: SUCCESS!" + friendList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileFriendlistGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEPOSTSGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArrayList<ProfilePostsGetResponse>> callablePPGR = service.profilePostsGet(authorization, idGet);
                callablePPGR.enqueue(new Callback<ArrayList<ProfilePostsGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfilePostsGetResponse>> call, Response<ArrayList<ProfilePostsGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfilePostsGetResponse postList : response.body()) {
                                //saves on db

                                de.ur.wi2_intern.ur_place.urplace.room.models.Post p = new de.ur.wi2_intern.ur_place.urplace.room.models.Post(
                                        Long.valueOf(postList.profile.id), postList.text, postList.picture, null, null);
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertPost(p);

                                for (Comment comment : postList.comments) {
                                    de.ur.wi2_intern.ur_place.urplace.room.models.Comment c = new de.ur.wi2_intern.ur_place.urplace.room.models.Comment(
                                            comment.id, comment.post, comment.profile.id, comment.text, comment.picture);
                                    db.dbdao().insertComment(c);
                                }

                                //postList.id ruft id des post auf
                                Log.d(TAG, "onResponse: SUCCESS!" + postList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfilePostsGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILECOMMENTSGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArrayList<ProfileCommentsGetResponse>> callablePCGR = service.profileCommentsGet(authorization, idGet);
                callablePCGR.enqueue(new Callback<ArrayList<ProfileCommentsGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileCommentsGetResponse>> call, Response<ArrayList<ProfileCommentsGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileCommentsGetResponse commentList : response.body()) {
                                //saves to db

                                de.ur.wi2_intern.ur_place.urplace.room.models.Comment c = new de.ur.wi2_intern.ur_place.urplace.room.models.Comment(
                                        commentList.id, commentList.post, commentList.profile.id, commentList.text, commentList.picture
                                );
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertComment(c);
                                //commentList.id ruft id des comment auf
                                Log.d(TAG, "onResponse: SUCCESS!" + commentList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileCommentsGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILEPOSTLIKESGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArrayList<ProfilePostLikesGetResponse>> callablePPLGR = service.profilePostLikesGet(authorization, idGet);
                callablePPLGR.enqueue(new Callback<ArrayList<ProfilePostLikesGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfilePostLikesGetResponse>> call, Response<ArrayList<ProfilePostLikesGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfilePostLikesGetResponse postLikeList : response.body()) {
                                //saves to db
                                de.ur.wi2_intern.ur_place.urplace.room.models.PostLike p = new de.ur.wi2_intern.ur_place.urplace.room.models.PostLike(
                                        postLikeList.id, postLikeList.profile.id, postLikeList.post
                                );
                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertPostLike(p);
                                //postLikeList.id ruft id des postLike auf
                                Log.d(TAG, "onResponse: SUCCESS!" + postLikeList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfilePostLikesGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILECOMMENTLIKESGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArrayList<ProfileCommentLikesGetResponse>> callablePCLGR = service.profileCommentLikesGet(authorization, idGet);
                callablePCLGR.enqueue(new Callback<ArrayList<ProfileCommentLikesGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileCommentLikesGetResponse>> call, Response<ArrayList<ProfileCommentLikesGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ProfileCommentLikesGetResponse commentLikeList : response.body()) {
                                //saves to db
                                de.ur.wi2_intern.ur_place.urplace.room.models.CommentLike c = new de.ur.wi2_intern.ur_place.urplace.room.models.CommentLike(
                                        commentLikeList.id, commentLikeList.profile.id
                                );

                                DB db = DB.getAppDatabase(getApplicationContext());
                                db.dbdao().insertCommentLike(c);

                                //commentLikeList.id ruft id des commentLike auf
                                Log.d(TAG, "onResponse: SUCCESS!" + commentLikeList.id);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileCommentLikesGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case PROFILESENDMESSAGEPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);
                String picture = extras.getString(PICTURE_KEY, "");
                String message = extras.getString(MESSAGE_KEY, "");

                ProfileSendMessagePostRequest profileSendMessage = new ProfileSendMessagePostRequest(message, picture);


                Call<ProfileSendMessagePostResponse> callablePSMPR = service.profileSendMessagePost(authorization, idGet, profileSendMessage);
                callablePSMPR.enqueue(new Callback<ProfileSendMessagePostResponse>() {
                    @Override
                    public void onResponse(Call<ProfileSendMessagePostResponse> call, Response<ProfileSendMessagePostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            Toast.makeText(getApplicationContext(), "Nachricht gesendet",
                                    Toast.LENGTH_LONG).show();

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileSendMessagePostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;


            //Post
            case POSTPOST_ACTION:
                extras = intent.getExtras();

                String text = extras.getString(TEXT_KEY, "");
                picture = extras.getString(PICTURE_KEY, "");
                GPS gps = new GPS(extras.getDouble(GPS_LA_KEY, 0), extras.getDouble(GPS_LO_KEY, 0));

                PostPostRequest postPost = new PostPostRequest(text, picture, gps);
                Call<PostPostResponse> callablePPR = service.postPost(authorization, postPost);
                callablePPR.enqueue(new Callback<PostPostResponse>() {
                    @Override
                    public void onResponse(Call<PostPostResponse> call, Response<PostPostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code
                            Log.d(TAG, "onResponse: SUCCESS!" + response.body().id);

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<PostPostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case POSTGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<PostGetResponse> callablePoGR = service.postGet(authorization, idGet);
                callablePoGR.enqueue(new Callback<PostGetResponse>() {
                    @Override
                    public void onResponse(Call<PostGetResponse> call, Response<PostGetResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            de.ur.wi2_intern.ur_place.urplace.room.models.Post p = new de.ur.wi2_intern.ur_place.urplace.room.models.Post(
                                    Long.valueOf(response.body().id), response.body().text, response.body().picture, response.body().gps.latitutde, response.body().gps.longitude
                            );
                            DB db = DB.getAppDatabase(getApplicationContext());
                            db.dbdao().insertPost(p);
                            for (Comment comment : response.body().comments) {
                                de.ur.wi2_intern.ur_place.urplace.room.models.Comment c = new de.ur.wi2_intern.ur_place.urplace.room.models.Comment(
                                        comment.id, comment.post, comment.profile.id, comment.text, comment.picture
                                );
                                db.dbdao().insertComment(c);

                            }


                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<PostGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case POSTPUT_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                //TODO post aus DB mitgeben
                Post post = new Post();
                id = 0;
                text = "";
                picture = "";
                ArrayList<Comment> comments = new ArrayList<Comment>();
                ArrayList<PostLike> likes = new ArrayList<PostLike>();
                Profile profile = new Profile();
                gps = null;
                String dateCreated = "";
                String lastUpdated = "";


                PostPutRequest postPut = new PostPutRequest(id, text, picture, comments, likes, profile, gps, dateCreated, lastUpdated);
                Call<PostPutResponse> callablePPuR = service.postPut(authorization, idGet, postPut);
                callablePPuR.enqueue(new Callback<PostPutResponse>() {
                    @Override
                    public void onResponse(Call<PostPutResponse> call, Response<PostPutResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<PostPutResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case POSTPATCH_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                //TODO post aus DB mitgeben
                post = new Post();
                id = 0;
                text = "";
                picture = "";
                comments = new ArrayList<Comment>();
                likes = new ArrayList<PostLike>();
                profile = new Profile();
                gps = null;
                dateCreated = "";
                lastUpdated = "";

                PostPatchRequest postPatch = new PostPatchRequest(id, text, picture, comments, likes, profile, gps, dateCreated, lastUpdated);
                Call<PostPatchResponse> callablePPaR = service.postPatch(authorization, idGet, postPatch);
                callablePPaR.enqueue(new Callback<PostPatchResponse>() {
                    @Override
                    public void onResponse(Call<PostPatchResponse> call, Response<PostPatchResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<PostPatchResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case POSTDELETE_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<PostDeleteResponse> callablePDR = service.postDelete(authorization, idGet);
                callablePDR.enqueue(new Callback<PostDeleteResponse>() {
                    @Override
                    public void onResponse(Call<PostDeleteResponse> call, Response<PostDeleteResponse> response) {
                        Log.d(TAG, "onResponse: SUCCESS!");
                        //TODO Hier code
                    }

                    @Override
                    public void onFailure(Call<PostDeleteResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case POSTLIKEPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<PostLikePostResponse> callablePLPR = service.postLikePost(authorization, idGet);
                callablePLPR.enqueue(new Callback<PostLikePostResponse>() {
                    @Override
                    public void onResponse(Call<PostLikePostResponse> call, Response<PostLikePostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code


                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<PostLikePostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case POSTUNLIKEPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<PostUnlikePostResponse> callablePULPR = service.postUnlikePost(authorization, idGet);
                callablePULPR.enqueue(new Callback<PostUnlikePostResponse>() {
                    @Override
                    public void onResponse(Call<PostUnlikePostResponse> call, Response<PostUnlikePostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<PostUnlikePostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            //Comment
            case COMMENTPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                text = extras.getString(TEXT_KEY, "");
                picture = extras.getString(PICTURE_KEY, "");
                gps = new GPS(extras.getDouble(GPS_LA_KEY, 0), extras.getDouble(GPS_LO_KEY, 0));

                CommentPostRequest commentPost = new CommentPostRequest(text, picture, gps);
                Call<CommentPostResponse> callableCPR = service.commentPost(authorization, idGet, commentPost);
                callableCPR.enqueue(new Callback<CommentPostResponse>() {
                    @Override
                    public void onResponse(Call<CommentPostResponse> call, Response<CommentPostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentPostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case COMMENTGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<CommentGetResponse> callableCoGR = service.commentGet(authorization, idGet);
                callableCoGR.enqueue(new Callback<CommentGetResponse>() {
                    @Override
                    public void onResponse(Call<CommentGetResponse> call, Response<CommentGetResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            de.ur.wi2_intern.ur_place.urplace.room.models.Comment c = new de.ur.wi2_intern.ur_place.urplace.room.models.Comment(
                                    response.body().id, response.body().post, response.body().profile.id, response.body().text, response.body().picture
                            );
                            DB db = DB.getAppDatabase(getApplicationContext());
                            db.dbdao().insertComment(c);

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;


            case COMMENTPUT_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                //TODO comment aus DB mitgeben
                id = 0;
                int postI = 0;
                text = "";
                picture = "";
                gps = null;
                ArrayList<CommentLike> likesC = new ArrayList<CommentLike>();
                profile = null;
                dateCreated = "";
                lastUpdated = "";

                CommentPutRequest commentPut = new CommentPutRequest(id, postI, text, picture, gps, likesC, profile, dateCreated, lastUpdated);
                Call<CommentPutResponse> callableCPuR = service.commentPut(authorization, idGet, commentPut);
                callableCPuR.enqueue(new Callback<CommentPutResponse>() {
                    @Override
                    public void onResponse(Call<CommentPutResponse> call, Response<CommentPutResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentPutResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;


            case COMMENTPATCH_ACTION:
                extras = intent.getExtras();

                //TODO comment aus DB mitgeben
                id = 0;
                postI = 0;
                text = "";
                picture = "";
                gps = null;
                likesC = new ArrayList<CommentLike>();
                profile = null;
                dateCreated = "";
                lastUpdated = "";

                idGet = extras.getInt(ID_KEY, 0);

                CommentPatchRequest commentPatch = new CommentPatchRequest(id, postI, text, picture, gps, likesC, profile, dateCreated, lastUpdated);
                Call<CommentPatchResponse> callableCPaR = service.commentPatch(authorization, idGet, commentPatch);
                callableCPaR.enqueue(new Callback<CommentPatchResponse>() {
                    @Override
                    public void onResponse(Call<CommentPatchResponse> call, Response<CommentPatchResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentPatchResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case COMMENTDELETE_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<CommentDeleteResponse> callableCDR = service.commentDelete(authorization, idGet);
                callableCDR.enqueue(new Callback<CommentDeleteResponse>() {
                    @Override
                    public void onResponse(Call<CommentDeleteResponse> call, Response<CommentDeleteResponse> response) {
                        Log.d(TAG, "onResponse: SUCCESS!");
                        //TODO Hier code
                    }

                    @Override
                    public void onFailure(Call<CommentDeleteResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case COMMENTLIKEPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<CommentLikePostResponse> callableCLPR = service.commentLikePost(authorization, idGet);
                callableCLPR.enqueue(new Callback<CommentLikePostResponse>() {
                    @Override
                    public void onResponse(Call<CommentLikePostResponse> call, Response<CommentLikePostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentLikePostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case COMMENTUNLIKEPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<CommentUnlikePostResponse> callableCULPR = service.commentUnlikePost(authorization, idGet);
                callableCULPR.enqueue(new Callback<CommentUnlikePostResponse>() {
                    @Override
                    public void onResponse(Call<CommentUnlikePostResponse> call, Response<CommentUnlikePostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentUnlikePostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            //Friends
            case PROFILEASKFORFRIENDSHIPPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ProfileAskForFriendshipPostResponse> callablePAFFPR = service.profileAskForFriendshipPost(authorization, idGet);
                callablePAFFPR.enqueue(new Callback<ProfileAskForFriendshipPostResponse>() {
                    @Override
                    public void onResponse(Call<ProfileAskForFriendshipPostResponse> call, Response<ProfileAskForFriendshipPostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileAskForFriendshipPostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case FRIENDSFRIENDSHIPACCEPTPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<FriendsAcceptPostResponse> callableFFAPR = service.friendsAcceptPost(authorization, idGet);
                callableFFAPR.enqueue(new Callback<FriendsAcceptPostResponse>() {
                    @Override
                    public void onResponse(Call<FriendsAcceptPostResponse> call, Response<FriendsAcceptPostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<FriendsAcceptPostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case FRIENDSFRIENDSHIPDECLINEPOST_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<FriendsDeclinePostResponse> callableFFDPR = service.friendsDeclinePost(authorization, idGet);
                callableFFDPR.enqueue(new Callback<FriendsDeclinePostResponse>() {
                    @Override
                    public void onResponse(Call<FriendsDeclinePostResponse> call, Response<FriendsDeclinePostResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<FriendsDeclinePostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case FRIENDSFRIENDSHIPUNFRIENDELETE_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<FriendsUnfriendDeleteResponse> callableFFUDR = service.friendsUnfriendDelete(authorization, idGet);
                callableFFUDR.enqueue(new Callback<FriendsUnfriendDeleteResponse>() {
                    @Override
                    public void onResponse(Call<FriendsUnfriendDeleteResponse> call, Response<FriendsUnfriendDeleteResponse> response) {
                        Log.d(TAG, "onResponse: SUCCESS!");
                        //TODO Hier code

                    }

                    @Override
                    public void onFailure(Call<FriendsUnfriendDeleteResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            //Market Place
            //article_categoty
            case ARTICLECATEGORYGET_ACTION:
                final DB database = DB.getAppDatabase(this);
                database.dbdao().nukeCategories();
                Call<ArrayList<ArticleCategoryGetResponse>> callableACGR = service.articleCategoryGet(authorization);
                callableACGR.enqueue(new Callback<ArrayList<ArticleCategoryGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ArticleCategoryGetResponse>> call, Response<ArrayList<ArticleCategoryGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ArticleCategoryGetResponse articleCategoryList : response.body()) {
                                //TODO Hier code
                                //articleCategoryList.id ruft id der articleCategory auf
                                ArticleCategories newCategory = new ArticleCategories(articleCategoryList.id, articleCategoryList.name, articleCategoryList.dateCreated, articleCategoryList.lastUpdated);
                                database.dbdao().insertCategory(newCategory);
                                Log.d(TAG, "onResponse: SUCCESS!" + articleCategoryList.name + " " + articleCategoryList.id);
                            }

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ArticleCategoryGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLECATEGORYPOST_ACTION:
                extras = intent.getExtras();

                String category = extras.getString(CATEGORY_KEY, "");
                Log.d(TAG, "onResponse: ! name" + category);

                ArticleCategoryPostRequest articleCategoryPost = new ArticleCategoryPostRequest(category);
                Call<ArticleCategoryPostResponse> callableACPR = service.articleCategoryPost(authorization, articleCategoryPost);
                callableACPR.enqueue(new Callback<ArticleCategoryPostResponse>() {
                    @Override
                    public void onResponse(Call<ArticleCategoryPostResponse> call, Response<ArticleCategoryPostResponse> response) {
                        try {
                            Log.e(TAG, "onRespone: " + response.body().name);
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE! not a new Category");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleCategoryPostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLECATEGORYIDGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArticleCategoryIdGetResponse> callableACIGR = service.articleCategoryIdGet(authorization, idGet);
                callableACIGR.enqueue(new Callback<ArticleCategoryIdGetResponse>() {
                    @Override
                    public void onResponse(Call<ArticleCategoryIdGetResponse> call, Response<ArticleCategoryIdGetResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");

                            //TODO Hier code
                            //response.body().name gibt name der articlecategory id zurück

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                            //wenn diese articlecategory id nicht existiert
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleCategoryIdGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLECATEGORYIDPUT_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);
                category = extras.getString(CATEGORY_KEY, "");
                dateCreated = "";
                lastUpdated = "";

                ArticleCategoryIdPutRequest articleCategoryIdPutRequest = new ArticleCategoryIdPutRequest(idGet, category, dateCreated, lastUpdated);

                Call<ArticleCategoryIdPutResponse> callableACIPuR = service.articleCategoryIdPut(authorization, idGet, articleCategoryIdPutRequest);
                callableACIPuR.enqueue(new Callback<ArticleCategoryIdPutResponse>() {
                    @Override
                    public void onResponse(Call<ArticleCategoryIdPutResponse> call, Response<ArticleCategoryIdPutResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");

                            //TODO Hier code
                            //response.body().name gibt name der articlecategory id zurück

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                            //wenn diese articlecategory id nicht existiert
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleCategoryIdPutResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLECATEGORYIDPATCH_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);
                category = extras.getString(CATEGORY_KEY, "");
                dateCreated = "";
                lastUpdated = "";

                ArticleCategoryIdPatchRequest articleCategoryIdPatchRequest = new ArticleCategoryIdPatchRequest(idGet, category, dateCreated, lastUpdated);

                Call<ArticleCategoryIdPatchResponse> callableACIPaR = service.articleCategoryIdPatch(authorization, idGet, articleCategoryIdPatchRequest);
                callableACIPaR.enqueue(new Callback<ArticleCategoryIdPatchResponse>() {
                    @Override
                    public void onResponse(Call<ArticleCategoryIdPatchResponse> call, Response<ArticleCategoryIdPatchResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");

                            //TODO Hier code
                            //response.body().name gibt name der articlecategory id zurück

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                            //wenn diese articlecategory id nicht existiert

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleCategoryIdPatchResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLECATEGORYIDDELETE_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArticleCategoryIdDeleteResponse> callableACIDR = service.articleCategoryIdDelete(authorization, idGet);
                callableACIDR.enqueue(new Callback<ArticleCategoryIdDeleteResponse>() {
                    @Override
                    public void onResponse(Call<ArticleCategoryIdDeleteResponse> call, Response<ArticleCategoryIdDeleteResponse> response) {
                        Log.d(TAG, "onResponse: SUCCESS!");
                        //TODO Hier code
                        //articlecategory gelöscht
                    }

                    @Override
                    public void onFailure(Call<ArticleCategoryIdDeleteResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                        //wenn diese articlecategory id nicht existiert

                    }
                });
                break;

            //article
            case ARTICLESEARCHGET_ACTION:
                extras = intent.getExtras();

                searchTerm = extras.getString(SEARCH_KEY, "");

                Call<ArrayList<ArticleSearchGetResponse>> callableASGR = service.articleSearchGet(authorization, searchTerm);
                callableASGR.enqueue(new Callback<ArrayList<ArticleSearchGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ArticleSearchGetResponse>> call, Response<ArrayList<ArticleSearchGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ArticleSearchGetResponse articleSearchList : response.body()) {
                                //TODO Hier code
                                //articleSearchList.title gibt den title des articels zurück
                                Log.d(TAG, "onResponse: !title " + articleSearchList.title);

                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ArticleSearchGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLEGET_ACTION:
                final DB databaseArticle = DB.getAppDatabase(this);
                databaseArticle.dbdao().nukeArticles();
                Call<ArrayList<ArticleGetResponse>> callableAGR = service.articleGet(authorization);
                callableAGR.enqueue(new Callback<ArrayList<ArticleGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ArticleGetResponse>> call, Response<ArrayList<ArticleGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");

                            for (ArticleGetResponse articleList : response.body()) {
                                Log.d(TAG, "onResponse: SUCCESS! eingefügt mit titel " + articleList.title);
                                ArticleRoom newArticle = new ArticleRoom(articleList.id, articleList.title, articleList.description, articleList.articleType,
                                        articleList.inShopUntil, articleList.price, articleList.picture, articleList.articleContactData.name,
                                        articleList.articleContactData.street, articleList.articleContactData.telephone, articleList.articleContactData.email,
                                        articleList.dateCreated, articleList.seller.id, articleList.bought, articleList.dateCreated);
                                Log.d(TAG, "onResponse: articleId " + articleList.seller.id);
                                //für fall Kategorie gelöscht, ->outofBounds abfangen
                                try {
                                    newArticle.setArticleCategoryTitle(articleList.articleCategories.get(0).name);
                                    newArticle.setCategoryId(articleList.articleCategories.get(0).id);

                                } catch (IndexOutOfBoundsException e) {
                                    Log.e(TAG, "onResponse: no category set, defaults applied");
                                    newArticle.setArticleCategoryTitle("unbekannte Kategorie");
                                    newArticle.setCategoryId(1000);
                                }
                                databaseArticle.dbdao().insertArticle(newArticle);
                            }

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ArticleGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLEPOST_ACTION:
                extras = intent.getExtras();

                //Look at test method in Aboutactivity for intentemplate
                String title = extras.getString(TITLE_KEY, "");
                String description = extras.getString(DESCRIPTION_KEY, "");
                int idCategory = extras.getInt(ID_KEY, 0);

                String categoryTitle = extras.getString(ARTICLECATEGORYNAME_KEY, "");

                //TODO set last created/updated
                ArrayList<ArticleCategory> articleCategoryList = new ArrayList<ArticleCategory>();
                Log.d(TAG, "onHandleIntent KATEGORIE NAME IN NETWORKSERVICE: " + categoryTitle);
                ArticleCategory articleCategory = new ArticleCategory(idCategory, categoryTitle, "", "");

                articleCategoryList.add(articleCategory);
                ArticleType articleType = stringToArticleType(extras.getString(ARTICLETYPE_KEY, "BUY"));
                String inShopUntil = extras.getString(INSHOPUNTIL_KEY, "");
                double price = extras.getDouble(PRICE_KEY, 0);
                String nameSeller = extras.getString(NAME_KEY, "");
                String streetSeller = extras.getString(STREET_KEY, "");
                String telephoneSeller = extras.getString(PHONE_KEY, "");
                String emailSeller = extras.getString(EMAIL_KEY, "");
                ArticleContactData articleContactData = new ArticleContactData(nameSeller, streetSeller, telephoneSeller, emailSeller);
                picture = preferences.getString("articlePicture", "");


                ArticlePostRequest articlePost = new ArticlePostRequest(title, description, articleCategoryList, articleType, inShopUntil, price, picture, articleContactData);
                Call<ArticlePostResponse> callableAPR = service.articlePost(authorization, articlePost);
                callableAPR.enqueue(new Callback<ArticlePostResponse>() {
                    @Override
                    public void onResponse(Call<ArticlePostResponse> call, Response<ArticlePostResponse> response) {
                        try {
                            Toast.makeText(NetworkService.this, response.body().title, Toast.LENGTH_SHORT).show();

                            Log.e(TAG, "onRespone: " + response.body().title);
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE! not a new item");
                            //TODO Hier code

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticlePostResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLESELLGET_ACTION:
                Call<ArrayList<ArticleSellGetResponse>> callableASellGR = service.articleSellGet(authorization);
                callableASellGR.enqueue(new Callback<ArrayList<ArticleSellGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ArticleSellGetResponse>> call, Response<ArrayList<ArticleSellGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ArticleSellGetResponse articleSellList : response.body()) {
                                //TODO Hier code
                                //articleSellList.id ruft id des articles auf
                                Log.d(TAG, "onResponse: !" + articleSellList.title);
                            }

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ArticleSellGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code

                    }
                });
                break;

            case ARTICLEBUYGET_ACTION:
                Call<ArrayList<ArticleBuyGetResponse>> callableABGR = service.articleBuyGet(authorization);
                callableABGR.enqueue(new Callback<ArrayList<ArticleBuyGetResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ArticleBuyGetResponse>> call, Response<ArrayList<ArticleBuyGetResponse>> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            for (ArticleBuyGetResponse articleBuyList : response.body()) {
                                //TODO Hier code
                                //articleBuyList.id ruft id des articles auf
                                Log.d(TAG, "onResponse: !" + articleBuyList.title);
                            }

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ArticleBuyGetResponse>> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code

                    }
                });
                break;

            case ARTICLEIDGET_ACTION:
                extras = intent.getExtras();

                id = extras.getInt(ID_KEY, 0);

                Call<ArticleIdGetResponse> callableAIGR = service.articleIdGet(authorization, id);
                callableAIGR.enqueue(new Callback<ArticleIdGetResponse>() {
                    @Override
                    public void onResponse(Call<ArticleIdGetResponse> call, Response<ArticleIdGetResponse> response) {
                        try {
                            Toast.makeText(NetworkService.this, response.body().title, Toast.LENGTH_SHORT).show();

                            Log.e(TAG, "onRespone: " + response.body().title);
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE! not a new item");
                            //TODO Hier code

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleIdGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLEIDPUT_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                title = extras.getString(TITLE_KEY, "");
                description = extras.getString(DESCRIPTION_KEY, "");
                idCategory = extras.getInt(IDCATEGORY_KEY, 0);

                categoryTitle = extras.getString(ARTICLECATEGORYNAME_KEY, "");

                //TODO set last created/updated
                dateCreated = "";
                lastUpdated = "";
                articleCategoryList = new ArrayList<ArticleCategory>();
                Log.d(TAG, "onHandleIntent KATEGORIE NAME IN NETWORKSERVICE: " + categoryTitle);
                articleCategory = new ArticleCategory(idCategory, categoryTitle, "", "");

                articleCategoryList.add(articleCategory);
                articleType = stringToArticleType(extras.getString(ARTICLETYPE_KEY, "BUY"));
                inShopUntil = extras.getString(INSHOPUNTIL_KEY, "");
                price = extras.getDouble(PRICE_KEY, 0);
                picture = extras.getString(PICTURE_KEY, "");
                nameSeller = extras.getString(NAME_KEY, "");
                streetSeller = extras.getString(STREET_KEY, "");
                telephoneSeller = extras.getString(PHONE_KEY, "");
                emailSeller = extras.getString(EMAIL_KEY, "");
                articleContactData = new ArticleContactData(nameSeller, streetSeller, telephoneSeller, emailSeller);


                ArticleIdPutRequest articleIdPut = new ArticleIdPutRequest(idGet, title, description, articleCategoryList, articleType, inShopUntil, price, picture, articleContactData, dateCreated, lastUpdated);
                Call<ArticleIdPutResponse> callableAIPuR = service.articleIdPut(authorization, idGet, articleIdPut);
                callableAIPuR.enqueue(new Callback<ArticleIdPutResponse>() {
                    @Override
                    public void onResponse(Call<ArticleIdPutResponse> call, Response<ArticleIdPutResponse> response) {
                        try {
                            Toast.makeText(NetworkService.this, response.body().title, Toast.LENGTH_SHORT).show();

                            Log.e(TAG, "onRespone: " + response.body().title);
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE! not a new item");
                            //TODO Hier code

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleIdPutResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLEIDPATCH_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                title = extras.getString(TITLE_KEY, "");
                description = extras.getString(DESCRIPTION_KEY, "");
                idCategory = extras.getInt(IDCATEGORY_KEY, 0);

                categoryTitle = extras.getString(ARTICLECATEGORYNAME_KEY, "");

                //TODO set last created/updated
                dateCreated = "";
                lastUpdated = "";
                articleCategoryList = new ArrayList<ArticleCategory>();
                Log.d(TAG, "onHandleIntent KATEGORIE NAME IN NETWORKSERVICE: " + categoryTitle);
                articleCategory = new ArticleCategory(idCategory, categoryTitle, "", "");

                articleCategoryList.add(articleCategory);
                articleType = stringToArticleType(extras.getString(ARTICLETYPE_KEY, "BUY"));
                inShopUntil = extras.getString(INSHOPUNTIL_KEY, "");
                price = extras.getDouble(PRICE_KEY, 0);
                //picture = extras.getString(PICTURE_KEY, "");
                nameSeller = extras.getString(NAME_KEY, "");
                streetSeller = extras.getString(STREET_KEY, "");
                telephoneSeller = extras.getString(PHONE_KEY, "");
                emailSeller = extras.getString(EMAIL_KEY, "");
                articleContactData = new ArticleContactData(nameSeller, streetSeller, telephoneSeller, emailSeller);

                boolean bought = extras.getBoolean(ARTICLEBOUGHT_KEY, false);

                picture = preferences.getString("articlePicture", "");

                ArticleIdPatchRequest articleIdPatch = new ArticleIdPatchRequest(idGet, title, description, articleCategoryList, articleType, inShopUntil, price, picture, articleContactData, dateCreated, lastUpdated, bought);
                Call<ArticleIdPatchResponse> callableAIPaR = service.articleIdPatch(authorization, idGet, articleIdPatch);
                callableAIPaR.enqueue(new Callback<ArticleIdPatchResponse>() {
                    @Override
                    public void onResponse(Call<ArticleIdPatchResponse> call, Response<ArticleIdPatchResponse> response) {
                        try {
                            Toast.makeText(NetworkService.this, response.body().title, Toast.LENGTH_SHORT).show();

                            Log.e(TAG, "onRespone: " + response.body().title);
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE! not a new item");
                            //TODO Hier code

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleIdPatchResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case ARTICLEIDDELETE_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<ArticleIdDeleteResponse> callableAIDR = service.articleIdDelete(authorization, idGet);
                callableAIDR.enqueue(new Callback<ArticleIdDeleteResponse>() {
                    @Override
                    public void onResponse(Call<ArticleIdDeleteResponse> call, Response<ArticleIdDeleteResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE! not a new item");
                            //TODO Hier code
                            //element existiert nicht

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleIdDeleteResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            //conversation
            case MARKETPLACECOMVERSATIONGET_ACTION:

                Call<MarketplaceConverdationGetResponse> callableMCGR = service.marketplaceConverdationGet(authorization);
                callableMCGR.enqueue(new Callback<MarketplaceConverdationGetResponse>() {
                    @Override
                    public void onResponse(Call<MarketplaceConverdationGetResponse> call, Response<MarketplaceConverdationGetResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<MarketplaceConverdationGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;

            case MARKETPLACECOMVERSATIONIDGET_ACTION:
                extras = intent.getExtras();

                idGet = extras.getInt(ID_KEY, 0);

                Call<MarketplaceConverdationIdGetResponse> callableMCIGR = service.marketplaceConverdationIdGet(authorization, idGet);
                callableMCIGR.enqueue(new Callback<MarketplaceConverdationIdGetResponse>() {
                    @Override
                    public void onResponse(Call<MarketplaceConverdationIdGetResponse> call, Response<MarketplaceConverdationIdGetResponse> response) {
                        try {
                            Log.d(TAG, "onResponse: SUCCESS!");
                            //TODO Hier code

                        } catch (NullPointerException e) {
                            Log.e(TAG, "onRespone: FAILURE!");
                            //TODO Hier code
                        }
                    }

                    @Override
                    public void onFailure(Call<MarketplaceConverdationIdGetResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: INITAL FILE LOAD FAILED!");
                        //TODO Hier code
                    }
                });
                break;


            default:
                Log.d(TAG, "onHandleIntent: NO ACITON FOR " + intent.getAction());
                break;
        }
        Log.d(TAG, "Intent received");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "on create network service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "on destroy network service");
    }

    //Daten werden gespeichert in den DefaultSharedPreferences
    private void saveLoginResponse(String username, String password, ArrayList<Role> role, String token_type, String access_token, int expires_in, String refresh_roken) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("role", role.toString());//Role wird zu string konvertiert
        editor.putString("token_type", token_type);
        editor.putString("access_token", access_token);
        editor.putInt("expires_in", expires_in);
        editor.putString("refresh_roken", refresh_roken);
        editor.commit();
    }

    //Daten werden gespeichert in den DefaultSharedPreferences
    private void saveRegisterResponse(int id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", id);
        editor.commit();
    }

    private void setLogin() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getString("loginSwitch", "").equals("true")) {
            editor.putString("login", "true");
        } else {
            editor.putString("login", "false");
        }
        editor.commit();
    }

    private void saveID(int id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("nutzerID", id);
        editor.commit();
    }

    private void saveBD(String bd) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nutzerBD", bd);
        editor.commit();
    }

    //Auf die gespeicherten Werte zugreifen:
    public String beispielGetAccessToken() {
        // 1. SharedPreferences laden:
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // 2. die gewünschte preference auslesen(key wie oben; defValue leer lassen):
        String access_token = preferences.getString("access_token", "");
        return access_token;
    }

    public Gender getGenderFromString(String gender) {
        Gender g;
        switch (gender) {
            case "weiblich":
                g = Gender.FEMALE;
                break;
            case "männlich":
                g = Gender.MALE;
                break;
            case "unbekannt":
                g = Gender.UNKNOWN;
                break;
            default:
                g = Gender.UNKNOWN;
                break;
        }
        return g;
    }
}

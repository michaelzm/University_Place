package de.ur.wi2_intern.ur_place.urplace.Profile;

import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import de.ur.wi2_intern.ur_place.urplace.BasePresenter;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.ProfileWithPosts;

public class MAPresenter extends BasePresenter<IMAModel, IMAView> implements IMAPresenter {
    public static final String TAG = "MAPresenter";
    ProfileWithPosts profileWithPosts;


    public MAPresenter(IMAModel imaModel, IMAView view) {
        setModel(imaModel);
        setView(view);
    }

    @Override
    public void updateView() {
        profileWithPosts = getModel().obtainProfile(1l);
        getView().setProfileData(profileWithPosts.getName(), profileWithPosts.getAge(), profileWithPosts.getSubject(), profileWithPosts.getGender());
        getView().setPostData(profileWithPosts.getPosts());
    }

    @Override
    public void searchMenuItemClicked() {
        getView().startProfileSearchActivity();
    }

    @Override
    public void friendListMenuItemClicked() {
        getView().startFriendListActivity();
    }

    @Override
    public void navigationDrawerMenuItemClicked() {
        getView().openNavigationDrawerMenu();
    }

    @Override
    public void cameraIconClicked(long postId) {
        // start photoAcitivty
        Log.d(TAG, "start photoactivity");
    }

    @Override
    public void imageIconClicked(long postId) {
        // start photoAcitivty
        Log.d(TAG, "start imageactivity");
    }

    @Override
    public void gpsIconClicked(long postId) {
        // start photoAcitivty
        Log.d(TAG, "start startGPS obtaining");
    }

    @Override
    public void sendCommentButtonClicked(long postId, String commentText, String picture, Long latitude, Long longitude) {
        boolean succSend = getModel().sendComment(postId, commentText, picture, longitude, latitude);
        if (succSend) {
            Log.d(TAG, "Comment successfully send");
            getView().showCommentSendToast();
        } else {
            Log.d(TAG, "Comment unsuccessfully send");
            getView().showCommentSendErrorToast();
        }
    }


}

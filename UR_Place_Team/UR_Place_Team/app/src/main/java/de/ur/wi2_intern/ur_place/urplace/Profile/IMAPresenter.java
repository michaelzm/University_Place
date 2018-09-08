package de.ur.wi2_intern.ur_place.urplace.Profile;

import de.ur.wi2_intern.ur_place.urplace.BasePresenterInterface;

public interface IMAPresenter extends BasePresenterInterface<IMAModel, IMAView> {
    void searchMenuItemClicked();

    void friendListMenuItemClicked();

    void navigationDrawerMenuItemClicked();

    void cameraIconClicked(long postId);

    void imageIconClicked(long postId);

    void gpsIconClicked(long postId);

    void sendCommentButtonClicked(long postId, String commentText, String picture, Long latitude, Long longitude);
}

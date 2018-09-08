package de.ur.wi2_intern.ur_place.urplace.Profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.Profile.models.Comment;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.Post;
import de.ur.wi2_intern.ur_place.urplace.R;

public class PostAdapter extends BaseExpandableListAdapter {

    public static final int COMMENT_NO_TEXT = 0;
    public static final int COMMENT_NO_PICTURE = 1;
    public static final int COMMENT_ALL = 2;
    public static final int COMMENT_ADD = 3;

    public static final int POST_NO_TEXT = 4;
    public static final int POST_NO_PICTURE = 5;
    public static final int POST_ALL = 6;

    public static final String TAG = "PostAdapter";
    Context context;
    List<Post> posts;
    IMAPresenter presenter;

    public PostAdapter(Context context, IMAPresenter imaPresenter, List<Post> posts) {
        this.context = context;
        this.presenter = imaPresenter;
        this.posts = posts;
    }

    @Override
    public int getGroupCount() {
        return posts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d(TAG, "COMMENTS: " + posts.get(groupPosition).getComments().size());
        return posts.get(groupPosition).getComments().size() + 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return posts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (childPosition == posts.get(groupPosition).getComments().size())
            return null;
        else
            return posts.get(groupPosition).getComments().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Post post = posts.get(groupPosition);

        LayoutInflater groupInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolderPost viewHolderPost = null;
        if (convertView == null) {
            if (post.getPicture() == null && post.getText() != null) {
                // only text
                convertView = groupInflater.inflate(R.layout.post_group_item_no_picture, null);
                TextView postText = (TextView) convertView.findViewById(R.id.tv_pgi_post);
                TextView position = (TextView) convertView.findViewById(R.id.tv_pgi_gps);
                viewHolderPost = new ViewHolderPost(convertView, null, postText, position);
            } else if (post.getText() == null && post.getPicture() != null) {
                // only picture
                convertView = groupInflater.inflate(R.layout.post_group_item_no_text, null);
                ImageView postPicture = (ImageView) convertView.findViewById(R.id.iv_pgi_picture);
                TextView position = (TextView) convertView.findViewById(R.id.tv_pgi_gps);
                viewHolderPost = new ViewHolderPost(convertView, postPicture, null, position);
            } else {
                convertView = groupInflater.inflate(R.layout.post_group_item, null);
                TextView postText = (TextView) convertView.findViewById(R.id.tv_pgi_post);
                ImageView postPicture = (ImageView) convertView.findViewById(R.id.iv_pgi_picture);
                TextView position = (TextView) convertView.findViewById(R.id.tv_pgi_gps);
                viewHolderPost = new ViewHolderPost(convertView, postPicture, postText, position);
            }
            convertView.setTag(viewHolderPost);
        } else {
            viewHolderPost = (ViewHolderPost) convertView.getTag();
        }

        if (post.getPicture() != null) {
            viewHolderPost.setPicture(post.getPicture());
        }

        if (post.getText() != null) {
            viewHolderPost.setText(post.getText());
        }

        if (post.getPosition() != null) {
            viewHolderPost.setPosition(post.getPosition());
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater groupInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Post post = posts.get(groupPosition);
        if (!isLastChild) {
            Comment comment = post.getComments().get(childPosition);

            ViewHolderComment viewHolderComment = null;

            if (convertView == null) {
                if (comment.getPicture() == null && comment.getCommentText() != null) {
                    // only text
                    convertView = groupInflater.inflate(R.layout.comment_child_item_no_picture, null);
                    ImageView commentProfilePicture = (ImageView) convertView.findViewById(R.id.iv_cci_profile_picture);
                    TextView commentText = (TextView) convertView.findViewById(R.id.tv_cci_text);
                    TextView position = (TextView) convertView.findViewById(R.id.tv_cci_gps);
                    viewHolderComment = new ViewHolderComment(convertView, commentProfilePicture, null, commentText, position);
                } else if (comment.getCommentText() == null && comment.getPicture() != null) {
                    // only picture
                    convertView = groupInflater.inflate(R.layout.comment_child_item_no_text, null);
                    ImageView commentProfilePicture = (ImageView) convertView.findViewById(R.id.iv_cci_profile_picture);
                    ImageView commentPicture = (ImageView) convertView.findViewById(R.id.iv_cci_picture);
                    TextView position = (TextView) convertView.findViewById(R.id.tv_cci_gps);
                    viewHolderComment = new ViewHolderComment(convertView, commentProfilePicture, commentPicture, null, position);
                } else {
                    convertView = groupInflater.inflate(R.layout.comment_child_item, null);
                    ImageView commentProfilePicture = (ImageView) convertView.findViewById(R.id.iv_cci_profile_picture);
                    TextView commentText = (TextView) convertView.findViewById(R.id.tv_cci_text);
                    ImageView commentPicture = (ImageView) convertView.findViewById(R.id.iv_cci_picture);
                    TextView position = (TextView) convertView.findViewById(R.id.tv_cci_gps);
                    viewHolderComment = new ViewHolderComment(convertView, commentProfilePicture, commentPicture, commentText, position);
                }
                convertView.setTag(viewHolderComment);
            } else {
                viewHolderComment = (ViewHolderComment) convertView.getTag();
            }

            if (comment.getProfile().getProfilePicture() != null) {
                viewHolderComment.setProfilePicture(comment.getProfile().getProfilePicture());
            }

            if (comment.getCommentText() != null) {
                viewHolderComment.setText(comment.getCommentText());
            }

            if (comment.getPicture() != null) {
                viewHolderComment.setPicture(comment.getPicture());
            }

            if (comment.getPosition() != null) {
                viewHolderComment.setPosition(comment.getPosition());
            }

            return convertView;
        } else {
            ViewHolderAddComment viewHolderAddComment = null;
            if (convertView == null) {
                convertView = groupInflater.inflate(R.layout.comment_child_add_comment, null);
                ImageView photo = (ImageView) convertView.findViewById(R.id.iv_cca_take_picture);
                ImageView image = (ImageView) convertView.findViewById(R.id.iv_cca_add_picture);
                ImageView gps = (ImageView) convertView.findViewById(R.id.iv_cca_add_gps);
                EditText textField = (EditText) convertView.findViewById(R.id.et_cca_comment);
                Button sendButton = (Button) convertView.findViewById(R.id.bt_cca_send_comment);
                viewHolderAddComment = new ViewHolderAddComment(convertView, photo, image, gps, textField, sendButton);
                convertView.setTag(viewHolderAddComment);
            } else {
                viewHolderAddComment = (ViewHolderAddComment) convertView.getTag();
            }
            viewHolderAddComment.setPostId(post.getId());
            return convertView;
        }
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (posts.get(groupPosition).getComments().size() == childPosition) {
            return COMMENT_ADD;
        } else {
            Comment comment = posts.get(groupPosition).getComments().get(childPosition);
            if (comment.getPicture() == null) {
                return COMMENT_NO_PICTURE;
            } else if (comment.getCommentText() == null) {
                return COMMENT_NO_TEXT;
            } else return COMMENT_ALL;
        }
    }

    @Override
    public int getChildTypeCount() {
        return 4;
    }

    @Override
    public int getGroupType(int groupPosition) {
        Post post = posts.get(groupPosition);
        if (post.getPicture() == null) {
            return POST_NO_PICTURE;
        } else if (post.getText() == null) {
            return POST_NO_TEXT;
        } else return POST_ALL;
    }

    @Override
    public int getGroupTypeCount() {
        return 3;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ViewHolderAddComment implements View.OnClickListener {
        View layout;
        ImageView photo;
        ImageView image;
        ImageView gps;
        EditText textField;
        Button sendButton;
        long postId;

        public static final int TAKE_PICTURE_ID = R.id.iv_cca_take_picture;
        public static final int ADD_PICTURE_ID = R.id.iv_cca_add_picture;
        public static final int ADD_GPS_ID = R.id.iv_cca_add_gps;
        public static final int SEND_COMMENT_ID = R.id.bt_cca_send_comment;


        public ViewHolderAddComment(View layout, ImageView photo, ImageView image, ImageView gps, EditText textField, Button sendButton) {
            this.layout = layout;
            this.photo = photo;
            this.image = image;
            this.gps = gps;
            this.textField = textField;
            this.sendButton = sendButton;
            setOnClickListeners();
        }

        public void setPostId(long postId) {
            this.postId = postId;
        }

        public void setOnClickListeners() {
            photo.setOnClickListener(this);
            image.setOnClickListener(this);
            gps.setOnClickListener(this);
            sendButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case TAKE_PICTURE_ID:
                    presenter.cameraIconClicked(this.postId);
                    break;
                case ADD_PICTURE_ID:
                    presenter.imageIconClicked(this.postId);
                    break;
                case ADD_GPS_ID:
                    presenter.gpsIconClicked(this.postId);
                    break;
                case SEND_COMMENT_ID:
                    // TODO add picture
                    String pictureBase64 = null;
                    String commentText = this.textField.getText().toString();
                    // TODO gpsholder
                    Long latitude = null;
                    Long longitude = null;
                    presenter.sendCommentButtonClicked(this.postId, commentText, pictureBase64, latitude, longitude);
                    break;
            }
        }
    }

    private class ViewHolderPost {
        View layout;
        ImageView postPicture;
        TextView postText;
        TextView postPosition;

        public ViewHolderPost(View layout, ImageView picture, TextView text, TextView position) {
            this.layout = layout;
            this.postPicture = picture;
            this.postText = text;
            this.postPosition = position;
        }

        public void setPicture(String base64) {
            byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            postPicture.setImageBitmap(decodedByte);
        }

        public void setText(String text) {
            postText.setText(text);
        }

        public void setPosition(String positionText) {
            postPosition.setText(positionText);
        }
    }

    private class ViewHolderComment {
        View layout;
        ImageView profilePicture;
        ImageView commentPicture;
        TextView commentText;
        TextView commentPosition;

        public ViewHolderComment(View layout, ImageView profilePicture, ImageView commentPicture, TextView commentText, TextView commentPosition) {
            this.layout = layout;
            this.profilePicture = profilePicture;
            this.commentPicture = commentPicture;
            this.commentText = commentText;
            this.commentPosition = commentPosition;
        }

        public void setProfilePicture(String base64) {
            byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profilePicture.setImageBitmap(decodedByte);
        }

        public void setPicture(String base64) {
            byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            commentPicture.setImageBitmap(decodedByte);
        }

        public void setText(String text) {
            commentText.setText(text);
        }

        public void setPosition(String positionText) {
            commentPosition.setText(positionText);
        }

    }
}

package de.ur.wi2_intern.ur_place.urplace.Profile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.AboutActivity;
import de.ur.wi2_intern.ur_place.urplace.FavouritesActivity;
import de.ur.wi2_intern.ur_place.urplace.FriendlistActivity;
import de.ur.wi2_intern.ur_place.urplace.Login.LoginActivity;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.ArticleOptions;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.MarketplaceMainActivity;
import de.ur.wi2_intern.ur_place.urplace.MessagesActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.Gender;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.Post;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.ProfileWithPosts;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.SearchActivity;
import de.ur.wi2_intern.ur_place.urplace.Settings.SettingsActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.models.Profile;

import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.decodeBase64;
import static de.ur.wi2_intern.ur_place.urplace.Marketplace.ArticleOptions.ARTICLEID_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.FIRSTNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ID_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.LOGIN_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PASSWORD_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PROFILEMEGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.REGISTER_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.UPDATE_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.USERNAME_KEY;

public class MainActivity extends AppCompatActivity implements IMAView, NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "MainActivity";
    public static final String RECEIVER_INTENT_NAME = "de.ur.wi2_intern.ur_place.urplace.Profile.MABCR";
    private IMAPresenter presenter;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private ConstraintLayout clMARoot;
    private Guideline glMainVertical;
    private ImageView ivMainProfilePicture;
    private TextView tvMainStudentName;
    private TextView tvMainAge;
    private TextView tvMainGender;
    private TextView tvMainSubject;
    private ExpandableListView elvMainPosts;

    private ActionBar actionBar;

    PostAdapter postAdapter;

    private IntentFilter receiverIntentFilter;
    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_white_24);

        //ULT
        //renewLoginToken();

        DB db = DB.getAppDatabase(getApplicationContext());
        IMAModel imaModel = new MADummyModel();
        presenter = new MAPresenter(imaModel, this);
        // insert dummy data
        ProfileWithPosts profile = imaModel.obtainProfile(1l);
        imaModel.saveProfile(profile);

        createBroadcastReceiverAndIntentFilter();

        navigationView.setNavigationItemSelectedListener(this);

        createListeners();

        //TODO PROFILEPICTURE in shared preferences speichern! und updaten wenn es geändert wird
        //TODO use cropToSquare() to make it square
        /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("picture", piInput);
                editor.commit();*/

        Intent profileIntent = new Intent(getApplicationContext(), NetworkService.class);
        profileIntent.setAction(PROFILEMEGET_ACTION);
        startService(profileIntent);
    }

    //TODO: broadCastReceiver für successful login erstellen
    private void createBroadcastReceiverAndIntentFilter() {
        broadcastReceiver = new MainActivityBroadcastReceiver();

        receiverIntentFilter = new IntentFilter(RECEIVER_INTENT_NAME);
    }

    public static class MainActivityBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: RECEIVE BROADCAST!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //presenter.unbindView();
        //unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.updateView();
    }

    private void createListeners() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                updateHeader();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    public void updateHeader() {
        ImageView img = (ImageView) findViewById(R.id.iv_menu_profilpicture);
        TextView text = (TextView) findViewById(R.id.te_menu_profilname);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String newText = preferences.getString("username", "");
        text.setText(newText);

        /* Alternative version(needs more memory and cpu!)
        DB databaseX = DB.getAppDatabase(this);
        List<Profile> profileList = databaseX.dbdao().getSingleProfile(preferences.getInt("nutzerID", -1));
        Profile profileX = profileList.get(0);
        if(profileX.getProfilePicture() != null){
            if(!profileX.getProfilePicture().equals("")){
                Bitmap newBitmapPicture = decodeBase64(profileX.getProfilePicture());
                img.setImageBitmap((newBitmapPicture));
            }
        }
        */

        if (preferences.getString("picture", null) != null) {
            String newTextPicture = preferences.getString("picture", "");
            Bitmap newBitmapPicture = decodeBase64(newTextPicture);
            img.setImageBitmap((newBitmapPicture));
        }
    }

    private void initializeViews() {
        getResources().getDrawable(R.drawable.suits_cast_harvey);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        this.navigationView = (NavigationView) findViewById(R.id.nv_ma_navigation_drawer);
        this.clMARoot = (ConstraintLayout) findViewById(R.id.cl_ma_root);
        this.glMainVertical = (Guideline) findViewById(R.id.gl_main_vertical);
        this.ivMainProfilePicture = (ImageView) findViewById(R.id.iv_main_profile_picture);
        this.tvMainStudentName = (TextView) findViewById(R.id.tv_main_student_name);
        this.tvMainAge = (TextView) findViewById(R.id.tv_main_age);
        this.tvMainGender = (TextView) findViewById(R.id.tv_main_gender);
        this.tvMainSubject = (TextView) findViewById(R.id.tv_main_subject);
        this.elvMainPosts = (ExpandableListView) findViewById(R.id.elv_main_posts);

        setSupportActionBar(toolbar);
        this.actionBar = getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                presenter.navigationDrawerMenuItemClicked();
                return true;

            case R.id.main_options:
                Intent implicitIntent = new Intent(this, OptionsActivity.class);
                startActivity(implicitIntent);
                return true;
        }

//        Intent implicitIntent = new Intent(this, ProfileSearch.class);
//        startActivity(implicitIntent);

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setProfileData(String name, int age, String subject, Gender gender) {
        String genderText = null;
        switch (gender) {
            case MALE:
                genderText = getString(R.string.male);
                break;
            case FEMALE:
                genderText = getString(R.string.female);
                break;
            case UNKNOWN:
                genderText = getString(R.string.not_specified);
                break;
        }

        this.tvMainStudentName.setText(name);
        this.tvMainAge.setText(age + " " + getString(R.string.years));
        this.tvMainSubject.setText(subject);
        this.tvMainGender.setText(genderText);
    }

    @Override
    public void setPostData(List<Post> posts) {
        postAdapter = new PostAdapter(this, presenter, posts);
        elvMainPosts.setAdapter(postAdapter);
    }

    @Override
    public void showCommentSendToast() {
        Toast.makeText(this, R.string.comment_succesfull, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCommentSendErrorToast() {
        Toast.makeText(this, R.string.comment_unsuccesfull, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProfileSearchActivity() {
        Intent implicitIntent = new Intent(this, SearchActivity.class);
        implicitIntent.putExtra("friendlist", false);
        startActivity(implicitIntent);
    }

    @Override
    public void startFriendListActivity() {
        Intent implicitIntent = new Intent(this, SearchActivity.class);
        implicitIntent.putExtra("friendlist", true);
        startActivity(implicitIntent);
    }

    /* ULT
    public void renewLoginToken(){
        Intent loginIntent = new Intent(this, NetworkService.class);
        loginIntent.setAction(UPDATE_ACTION);
        startService(loginIntent);
    }
    */

    public void startLogoutActivity() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login", "false");
        editor.commit();

        Intent implicitIntent = new Intent(this, LoginActivity.class);
        startActivity(implicitIntent);
    }

    @Override
    public void openNavigationDrawerMenu() {
        Log.d(TAG, "NAVIGAITON DRAWER OPENED");
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        drawerLayout.closeDrawers();
        Intent implicitIntent;
        switch (id) {
            case R.id.nav_messages:
                implicitIntent = new Intent(this, MessagesActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.nav_favourites:
                implicitIntent = new Intent(this, FavouritesActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.nav_friendlist:
                implicitIntent = new Intent(this, FriendlistActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.nav_search:
                implicitIntent = new Intent(this, SearchActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.nav_marketplace:
                implicitIntent = new Intent(this, MarketplaceMainActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.nav_settings:
                implicitIntent = new Intent(this, SettingsActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.nav_about:
                implicitIntent = new Intent(this, AboutActivity.class);
                startActivity(implicitIntent);
                break;
            case R.id.nav_logout:
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setTitle(getApplicationContext().getResources().getString(R.string.lo_logouttitle));
                dlgAlert.setMessage(getApplicationContext().getResources().getString(R.string.lo_logouttext));
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startLogoutActivity();
                            }
                        });
                dlgAlert.setNegativeButton("Abbrechen",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                break;
        }

        return true;
    }

    private static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight, boolean isNecessaryToKeepOrig) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        if (!isNecessaryToKeepOrig) {
            bm.recycle();
        }
        return resizedBitmap;
    }
}

package de.ur.wi2_intern.ur_place.urplace.Profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.AboutActivity;
import de.ur.wi2_intern.ur_place.urplace.FavouritesActivity;
import de.ur.wi2_intern.ur_place.urplace.FriendlistActivity;
import de.ur.wi2_intern.ur_place.urplace.Login.LoginActivity;
import de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.MarketplaceMainActivity;
import de.ur.wi2_intern.ur_place.urplace.MessagesActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.models.Gender;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.SearchActivity;
import de.ur.wi2_intern.ur_place.urplace.Settings.SettingsActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Hobby;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.models.Profile;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.cropToSquare;
import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.decodeBase64;
import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.getEncoded64ImageStringFromBitmap;
import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.getResizedBitmap;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEPOST_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLESEARCHGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLETYPE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.BIRTHDATE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.DESCRIPTION_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.EMAIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.FIRSTNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.GENDERD_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ID_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.INSHOPUNTIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.LASTNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.NAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.NICKNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PASSWORD_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PHONE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PICTURE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PRICE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PROFILEMEGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PROFILEMEPATCH_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PROFILEMEPUT_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.REGISTER_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.STREET_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.SUBJECT_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.TITLE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.USERNAME_KEY;

public class OptionsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "OptionsMain";


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private EditText textNickname;
    private EditText textGender;
    private EditText textSubject;
    private ImageView imPicture;
    private String textPicture;

    private Button saveButton;
    private Button cancelButton;

    final static int GALLERY = 1;
    final static int CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        this.navigationView = (NavigationView) findViewById(R.id.nv_ma_navigation_drawer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.te_menu_options));
        navigationView.setNavigationItemSelectedListener(this);

        textNickname = (EditText) findViewById(R.id.nickname_options);
        textGender = (EditText) findViewById(R.id.gender_options);
        textSubject = (EditText) findViewById(R.id.subject_options);
        imPicture = (ImageView) findViewById(R.id.picture_options);
        textPicture = null;

        Intent profileIntent = new Intent(getApplicationContext(), NetworkService.class);
        profileIntent.setAction(PROFILEMEGET_ACTION);
        startService(profileIntent);

        textGender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseGender();
            }
        });
        textGender.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    chooseGender();
                } else {

                }
            }
        });
        imPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addPictureAsString();
            }
        });
        imPicture.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    addPictureAsString();
                } else {

                }
            }
        });

        saveButton = (Button) findViewById(R.id.bu_options_save);
        cancelButton = (Button) findViewById(R.id.bu_options_cancel);

        saveButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                updateProfile();
            }
        });
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                home();
            }
        });

        createListeners();

    }

    public void updateProfile() {
        String nnInput = textNickname.getText().toString();
        String geInput = textGender.getText().toString();
        String suInput = textSubject.getText().toString();
        String piInput = textPicture;
        if (isNetworkAvailable()) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            Intent profileUpdateIntent = new Intent(this, NetworkService.class);
            profileUpdateIntent.setAction(PROFILEMEPATCH_ACTION);


            int idO = preferences.getInt("nutzerID", -1);
            if (idO == -1) {
                Log.d(TAG, "Problem with ProfileId in SP");
            } else {
                DB database = DB.getAppDatabase(this);
                List<Profile> profileList = database.dbdao().getSingleProfile(idO);
                Profile profile = profileList.get(0);
                String genderO = getStringFromGender(profile.getGender());
                String subjectO = profile.getSubject();
                String nicknameO = profile.getNickname();

                String lastnameO = profile.getLastname();
                String firstnameO = profile.getPrename();
                ArrayList<Hobby> hobbiesO = new ArrayList<Hobby>();

                String birthdateO = preferences.getString("nutzerBD", "");
                String pictureO = profile.getProfilePicture();

                if (nnInput != null) {
                    if (!nnInput.equals("")) {
                        nicknameO = nnInput;
                        Log.d(TAG, " new Nickname");

                    }
                }
                if (geInput != null) {
                    if (!geInput.equals("")) {
                        genderO = geInput;
                        Log.d(TAG, " new Gender");

                    }
                }
                if (suInput != null) {
                    if (!suInput.equals("")) {
                        subjectO = suInput;
                        Log.d(TAG, " new Subjekt");

                    }
                }

                if (piInput != null) {
                    if (!piInput.equals("")) {
                        pictureO = piInput;
                        Log.d(TAG, " new Picture");

                    }
                }

                editor.putString("picture", pictureO);
                editor.commit();

                profileUpdateIntent.putExtra(ID_KEY, idO);
                profileUpdateIntent.putExtra(GENDERD_KEY, genderO);
                profileUpdateIntent.putExtra(SUBJECT_KEY, subjectO);
                profileUpdateIntent.putExtra(NICKNAME_KEY, nicknameO);
                profileUpdateIntent.putExtra(FIRSTNAME_KEY, firstnameO);
                profileUpdateIntent.putExtra(LASTNAME_KEY, lastnameO);
                profileUpdateIntent.putExtra(BIRTHDATE_KEY, birthdateO);

                Toast.makeText(OptionsActivity.this, "PROFILE UPDATED", Toast.LENGTH_SHORT).show();
                startService(profileUpdateIntent);
                home();
            }


        } else {
            Toast.makeText(OptionsActivity.this, "NETWORK FAILURE", Toast.LENGTH_SHORT).show();
        }
    }

    public void home() {
        Intent implicitIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(implicitIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
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

    //popup erscheint mit der Geschlechtsauswahl
    public void chooseGender() {
        CharSequence choose[] = new CharSequence[]{"weiblich", "männlich", "unbekannt"};

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle(getApplicationContext().getResources().getString(R.string.re_ge_title));
        dlgAlert.setItems(choose, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        textGender.setText("weiblich");
                        break;
                    case 1:
                        textGender.setText("männlich");
                        break;
                    case 2:
                        textGender.setText("unbekannt");
                        break;
                    default:
                        textGender.setText("");
                        break;
                }
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    //below: Adding picture
    public void addPictureAsString() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("" +
                "Bildquelle auswählen:");
        String[] pictureDialogItems = {
                "Gallerie",
                "Kamera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();

        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY) {
                if (data != null) {
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        bitmap = cropToSquare(bitmap);
                        bitmap = getResizedBitmap(bitmap, 250, 250, false);
                        saveBitmapAsString(bitmap);
                        imPicture.setImageBitmap(bitmap);

                        Toast.makeText(OptionsActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();


                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(OptionsActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (requestCode == CAMERA) {
                Bitmap thumbnail = (Bitmap) extras.get("data");
                thumbnail = cropToSquare(thumbnail);
                thumbnail = getResizedBitmap(thumbnail, 250, 250, false);
                saveBitmapAsString(thumbnail);
                imPicture.setImageBitmap(thumbnail);
                Toast.makeText(OptionsActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void saveBitmapAsString(Bitmap myBitmap) {
        textPicture = getEncoded64ImageStringFromBitmap(myBitmap);
    }

    public void updateHeader() {
        ImageView img = (ImageView) findViewById(R.id.iv_menu_profilpicture);
        TextView text = (TextView) findViewById(R.id.te_menu_profilname);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String newText = preferences.getString("username", "");
        text.setText(newText);
        if (preferences.getString("picture", null) != null) {
            String newTextPicture = preferences.getString("picture", "");
            Bitmap newBitmapPicture = decodeBase64(newTextPicture);
            //newBitmapPicture = getResizedBitmap(newBitmapPicture, 250, 250, false);
            img.setImageBitmap((newBitmapPicture));
        }
    }

    public void startLogoutActivity() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login", "false");
        editor.commit();
        Intent implicitIntent = new Intent(this, LoginActivity.class);
        startActivity(implicitIntent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        drawerLayout.closeDrawers();
        Intent implicitIntent;
        switch (id) {
            case R.id.nav_profile:
                implicitIntent = new Intent(this, MainActivity.class);
                startActivity(implicitIntent);
                break;
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public String getStringFromGender(de.ur.wi2_intern.ur_place.urplace.room.models.Gender gender) {
        String stringGender;
        switch (gender) {
            case FEMALE:
                stringGender = "weiblich";
                break;
            case MALE:
                stringGender = "männlich";
                break;
            case UNKNOWN:
                stringGender = "unbekannt";
                break;
            default:
                stringGender = "unbekannt";
                break;
        }
        return stringGender;
    }
}


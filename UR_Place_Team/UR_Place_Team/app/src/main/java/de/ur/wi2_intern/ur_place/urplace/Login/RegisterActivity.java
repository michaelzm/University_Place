package de.ur.wi2_intern.ur_place.urplace.Login;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.ur.wi2_intern.ur_place.urplace.Marketplace.ArticleOptions;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;


import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.BIRTHDATE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.FIRSTNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.GENDERD_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.LASTNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.NICKNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PASSWORD_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PICTURE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.REGISTER_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.USERNAME_KEY;


public class RegisterActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private Button registerbutton;
    private Button backbutton;

    private EditText textUsernameR;
    private EditText textPasswordR;
    private EditText textPasswordRR;
    private EditText textNickname;
    private EditText textFirstname;
    private EditText textLastname;
    private EditText textBirthdate;
    private EditText textGender;
    private ImageView imPicture;
    private String textPicture;

    final static int GALLERY = 1;
    final static int CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.te_menu_register));
        textUsernameR = (EditText) findViewById(R.id.usernameR);
        textPasswordR = (EditText) findViewById(R.id.passwordR);
        textPasswordRR = (EditText) findViewById(R.id.passwordRR);
        textNickname = (EditText) findViewById(R.id.nickname);
        textFirstname = (EditText) findViewById(R.id.firstname);
        textLastname = (EditText) findViewById(R.id.lastname);
        textBirthdate = (EditText) findViewById(R.id.birthdate);
        textGender = (EditText) findViewById(R.id.gender);
        imPicture = (ImageView) findViewById(R.id.picture);
        textPicture = null;


        textBirthdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment dialog = new DatePickerFragment(v);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });
        textBirthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DatePickerFragment dialog = new DatePickerFragment(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                } else {

                }
            }
        });
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

        registerbutton = (Button) findViewById(R.id.bu_register_registerbutton);
        backbutton = (Button) findViewById(R.id.bu_register_tologinbutton);

        registerbutton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                tryRegister();
            }
        });
        backbutton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    private void tryRegister() {
        String unInput = textUsernameR.getText().toString();
        String nnInput = textNickname.getText().toString();
        String pwInput = textPasswordR.getText().toString();
        String pwRInput = textPasswordRR.getText().toString();
        String fnInput = textFirstname.getText().toString();
        String lnInput = textLastname.getText().toString();
        String bdInput = textBirthdate.getText().toString();
        String geInput = textGender.getText().toString();
        String piInput = textPicture;

        if(isNetworkAvailable()){
            if (verifyInputs(unInput, nnInput, pwInput, pwRInput, fnInput, lnInput, bdInput, geInput)) {
                Intent regsiterIntent = new Intent(this, NetworkService.class);
                regsiterIntent.setAction(REGISTER_ACTION);
                regsiterIntent.putExtra(USERNAME_KEY, unInput);
                regsiterIntent.putExtra(NICKNAME_KEY, nnInput);
                regsiterIntent.putExtra(PASSWORD_KEY, pwInput);
                regsiterIntent.putExtra(FIRSTNAME_KEY, fnInput);
                regsiterIntent.putExtra(LASTNAME_KEY, lnInput);
                regsiterIntent.putExtra(BIRTHDATE_KEY, bdInput);
                regsiterIntent.putExtra(GENDERD_KEY, geInput);
                //regsiterIntent.putExtra(PICTURE_KEY, piInput); picture too big for extra
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("picture", piInput);
                editor.commit();
                startService(regsiterIntent);
            }
        }
        else {
            Toast.makeText(RegisterActivity.this, "NETWORK FAILURE", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean verifyInputs(String username, String nickname, String password, String passwordr, String firstname, String lastname, String birthdate, String gender) {
        boolean acceptableInputs = false;
        int failID = 0;

        //verify mandatory fields
        if (!username.equals("") && !password.equals("") && !firstname.equals("") && !lastname.equals("") && !birthdate.equals("") && !gender.equals("")) {
            //verify same passwords
            if (password.equals(passwordr)) {
                //verify username > 3 Chars
                if (username.length() > 3) {
                    //verify password > 5
                    if (password.length() > 5) {
                        //no error detected
                        acceptableInputs = true;
                    } else {
                        failID = 4;
                    }
                } else {
                    failID = 3;
                }
            } else {
                failID = 2;
            }
        } else {
            failID = 1;
        }
        if(failID > 0) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            switch (failID) {
                case 1:
                    dlgAlert.setTitle(getApplicationContext().getResources().getString(R.string.re_pf_errortitle));
                    dlgAlert.setMessage(getApplicationContext().getResources().getString(R.string.re_pf_errortext));
                    break;

                case 2:
                    dlgAlert.setTitle(getApplicationContext().getResources().getString(R.string.re_pw_errortitle));
                    dlgAlert.setMessage(getApplicationContext().getResources().getString(R.string.re_pw_errortext));
                    break;

                case 3:
                    dlgAlert.setTitle(getApplicationContext().getResources().getString(R.string.re_ul_errortitle));
                    dlgAlert.setMessage(getApplicationContext().getResources().getString(R.string.re_ul_errortext));
                    break;

                case 4:
                    dlgAlert.setTitle(getApplicationContext().getResources().getString(R.string.re_pl_errortitle));
                    dlgAlert.setMessage(getApplicationContext().getResources().getString(R.string.re_pl_errortext));
                    break;

                default:
                    dlgAlert.setTitle(getApplicationContext().getResources().getString(R.string.re_sd_errortitle));
                    dlgAlert.setMessage(getApplicationContext().getResources().getString(R.string.re_sd_errortext));
                    break;
            }
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }

        return acceptableInputs;
    }

    public void login() {
        finish();
        Intent implicitIntent = new Intent(this, LoginActivity.class);
        startActivity(implicitIntent);
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
                        Toast.makeText(RegisterActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();


                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else if (requestCode == CAMERA) {
                Bitmap thumbnail = (Bitmap) extras.get("data");
                thumbnail = cropToSquare(thumbnail);
                thumbnail = getResizedBitmap(thumbnail, 250, 250, false);
                saveBitmapAsString(thumbnail);
                imPicture.setImageBitmap(thumbnail);
                Toast.makeText(RegisterActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public void saveBitmapAsString(Bitmap myBitmap) {
        textPicture = getEncoded64ImageStringFromBitmap(myBitmap);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight, boolean isNecessaryToKeepOrig) {
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

    public static Bitmap cropToSquare(Bitmap bitmap){
        int width  = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = 0;
        int newHeight = 0;
        int cropW = 0;
        int cropH = 0;
        if(width > height){
            newWidth = height;
            newHeight = height;
            cropW = (width - height)/2;
        }
        else{
            newWidth = width;
            newHeight = width;
            cropH = (height - width)/2;
        }
        Bitmap cropImg = Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight);
        return cropImg;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

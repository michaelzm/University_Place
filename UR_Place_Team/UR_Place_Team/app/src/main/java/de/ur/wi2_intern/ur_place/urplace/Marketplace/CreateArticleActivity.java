package de.ur.wi2_intern.ur_place.urplace.Marketplace;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleCategory;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleContactData;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;
import de.ur.wi2_intern.ur_place.urplace.Login.DatePickerFragment;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleCategories;

import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.cropToSquare;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEPOST_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLETYPE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.DESCRIPTION_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.EMAIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ID_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.INSHOPUNTIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.NAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PHONE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PICTURE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PRICE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.STREET_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.TITLE_KEY;


public class CreateArticleActivity extends AppCompatActivity {
    final DB database = DB.getAppDatabase(this);

    private static final String TAG = "CreateArticleActivity";

    private Toolbar toolbar;
    private EditText input_title;
    private EditText input_description;

    private EditText input_name;
    private EditText input_street;
    private EditText input_phone;
    private EditText input_email;
    private EditText input_category; //nicht so wie gebraucht wird
    private EditText input_inShopUntil;
    private EditText input_price;

    private RadioGroup radioGroup;
    private RadioButton rb_fixed;
    private RadioButton rb_auction;
    private RadioButton rb_free;

    private Button bt_publish;
    private ImageView img_articleBild;
    MarketplacePresenter presenter;
    public String textPicture;
    final static int GALLERY = 1;
    final static int CAMERA = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_article);
        input_title=(EditText) findViewById(R.id.text_input_title);
        input_description=(EditText) findViewById(R.id.text_input_description);

        input_name=(EditText) findViewById(R.id.input_name);
        input_street=(EditText) findViewById(R.id.input_street);
        input_phone=(EditText) findViewById(R.id.input_telephone);
        input_email=(EditText) findViewById(R.id.input_email);

        input_category= (EditText) findViewById(R.id.text_input_category);
        input_inShopUntil=(EditText) findViewById(R.id.date_input_until);

        radioGroup = (RadioGroup) findViewById(R.id.rg_co_offer_type);
        rb_auction = (RadioButton) findViewById(R.id.rb_co_auction);
        rb_fixed = (RadioButton) findViewById(R.id.rb_co_fixed_price);
        rb_free = (RadioButton) findViewById(R.id.rb_co_present);

        input_price=(EditText) findViewById(R.id.text_input_price);
        bt_publish=(Button) findViewById(R.id.bt_co_publish_offer);
        textPicture=null;

        img_articleBild = (ImageView) findViewById(R.id.articlePicture);

        input_inShopUntil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment dialog = new DatePickerFragment(v);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft, "DatePicker");
            }
        });
        input_inShopUntil.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        input_category.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseCategory();
            }
        });
        input_category.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    chooseCategory();
                } else {

                }
            }
        });
        img_articleBild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addPictureAsString();
            }
        });
        img_articleBild.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    addPictureAsString();
                } else {

                }
            }
        });
        bt_publish.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String title=input_title.getText().toString();
                String description=input_description.getText().toString();
                String name=input_name.getText().toString();
                String street=input_street.getText().toString();
                String phone=input_phone.getText().toString();
                String email=input_email.getText().toString();
                String categoryName=input_category.getText().toString();
                String inShopUntil=input_inShopUntil.getText().toString(); //datePicker
                String price = input_price.getText().toString();

                int categoryId= database.dbdao().getSpecificID(categoryName);
                String type = checkButton();
                Log.d(TAG, "onClick: pictureString"+textPicture);
                ArticleContactData contact = new ArticleContactData(name, street, phone, email);
                ArticleCategory categoryObject=new ArticleCategory(categoryName);
                if(title!="" && description!="" && name!="" &&street!="" &&email!="" &&categoryName!="" &&inShopUntil!="" &&price!="" &&categoryId!=0){
                    if(type=="BID"){
                        price="1";
                    }
                    double value= Double.parseDouble(price);
                    Intent createArticleIntent = new Intent(CreateArticleActivity.this,NetworkService.class);
                    createArticleIntent.setAction(ARTICLEPOST_ACTION);
                    createArticleIntent.putExtra(TITLE_KEY,title);
                    createArticleIntent.putExtra(DESCRIPTION_KEY, description);
                    createArticleIntent.putExtra(ARTICLECATEGORYNAME_KEY, categoryName);
                    createArticleIntent.putExtra(ID_KEY, categoryId);
                    createArticleIntent.putExtra(ARTICLETYPE_KEY, type);
                    createArticleIntent.putExtra(INSHOPUNTIL_KEY, inShopUntil);
                    createArticleIntent.putExtra(PRICE_KEY, value);
                    createArticleIntent.putExtra(NAME_KEY, name);
                    createArticleIntent.putExtra(STREET_KEY, street);
                    createArticleIntent.putExtra(PHONE_KEY, phone);
                    createArticleIntent.putExtra(EMAIL_KEY, email);

                    Log.d("CREATE ARTICLE", "Kategorie ist: "+categoryName);
                    startService(createArticleIntent);
                    Intent intent = new Intent(CreateArticleActivity.this, MarketplaceMainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CreateArticleActivity.this, "Bitte alle Angaben ausfüllen", Toast.LENGTH_SHORT).show();
                }
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

    public String checkButton () {
        int radioId = radioGroup.getCheckedRadioButtonId();
        if(radioId == rb_auction.getId()){
            return  "BID";
        } else if(radioId == rb_fixed.getId()) {
            return "BUY";
        } else {
            return "PRESENT";
        }
    }

    public void login() {
        finish();
    }


    public void chooseCategory() {


        List<ArticleCategories> categories = database.dbdao().getAllCategories();
        Log.d("CHOOSECATEGORY", "chooseCategory: size "+categories.size());
        final String chooseCategory[] = new String[categories.size()];
        categories.size();
        for (int i = 0; i < categories.size(); i++) {
            chooseCategory[i]=categories.get(i).getTitle();
        }
        //todo use the categorylist the network service class gets - done
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Kategorie auswählen");
        dlgAlert.setItems(chooseCategory, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                input_category.setText(chooseCategory[which]);
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
                "Galerie",
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
                        bitmap = getResizedBitmap(bitmap, 1024, 1024, false);
                        saveBitmapAsString(bitmap);
                        img_articleBild.setImageBitmap(bitmap);
                        Toast.makeText(CreateArticleActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();


                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(CreateArticleActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (requestCode == CAMERA) {
                Bitmap thumbnail = (Bitmap) extras.get("data");
                thumbnail = cropToSquare(thumbnail);
                thumbnail = getResizedBitmap(thumbnail, 1024, 1024, false);
                saveBitmapAsString(thumbnail);
                img_articleBild.setImageBitmap(thumbnail);
                Toast.makeText(CreateArticleActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    public void saveBitmapAsString(Bitmap myBitmap) {
        textPicture = getEncoded64ImageStringFromBitmap(myBitmap);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("articlePicture", textPicture);
        editor.commit();
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

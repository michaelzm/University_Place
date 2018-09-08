package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.FavouritesActivity;
import de.ur.wi2_intern.ur_place.urplace.FriendlistActivity;
import de.ur.wi2_intern.ur_place.urplace.Login.DatePickerFragment;
import de.ur.wi2_intern.ur_place.urplace.Login.LoginActivity;
import de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity;
import de.ur.wi2_intern.ur_place.urplace.MessagesActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.OptionsActivity;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.SearchActivity;
import de.ur.wi2_intern.ur_place.urplace.Settings.SettingsActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Article;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleCategory;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleContactData;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleCategories;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleRoom;

import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.decodeBase64;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEIDDELETE_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEIDGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEIDPATCH_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEPOST_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLETYPE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.DESCRIPTION_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.EMAIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.IDCATEGORY_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ID_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.INSHOPUNTIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.NAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PHONE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PRICE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PROFILEMEPUT_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.STREET_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.TITLE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.room.converter.ArticleTypeConverter.articleTypeToString;
import static de.ur.wi2_intern.ur_place.urplace.room.converter.ArticleTypeConverter.stringToArticleType;

public class ArticleOptions extends AppCompatActivity{
    private Toolbar toolbar;
    private int articleID;

    public final static String ARTICLEID_KEY ="aidkey";

    private EditText textDescription;
    private EditText textCategorie;

    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;

    //TODO in manifest parentActivity für backbutton festlegen!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_options);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.te_article_options));
        Bundle extras = getIntent().getExtras();
        articleID = extras.getInt(ARTICLEID_KEY, -1);
        Toast.makeText(ArticleOptions.this, "articleID: " + articleID, Toast.LENGTH_SHORT).show();
        textDescription = (EditText) findViewById(R.id.text_article_options);
        textCategorie = (EditText) findViewById(R.id.categorie_article_options);
        saveButton = (Button) findViewById(R.id.bu_article_options_save);
        cancelButton = (Button) findViewById(R.id.bu_article_options_cancel);
        deleteButton = (Button) findViewById(R.id.bu_article_options_delete);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               saveArticle();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteArticle();
            }
        });
        textCategorie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseCategory();
            }
        });
        textCategorie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    chooseCategory();
                } else {

                }
            }
        });
        
        Intent iipdateArticleIntent = new Intent(ArticleOptions.this,NetworkService.class);
        iipdateArticleIntent.setAction(ARTICLEGET_ACTION);
        startService(iipdateArticleIntent);
    }

    public void saveArticle(){
        //TODO update in db with network befor getting data from db?
        DB database = DB.getAppDatabase(this);
        ArticleRoom article = database.dbdao().getArtilceById(articleID);

        String titleO = article.getTitle();
        String descriptionO = article.getDescription();
        String categoryNameO = article.getArticleCategoryTitle();
        int categoryIdO = article.getCategoryId();
        String typeO = articleTypeToString(article.getArticleType());
        String inShopUntilO = article.getInShopUntil();
        double valueO = article.getPrice();
        String nameO = article.getName();
        String streetO = article.getStreet();
        String phoneO = article.getTelephone();
        String emailO = article.getEmail();
        String pictureO = article.getPicture();

        if ((textDescription.getText().toString()) != null) {
            if (!(textDescription.getText().toString()).equals("")) {
                descriptionO = (textDescription.getText().toString());
            }
        }
        if ((textCategorie.getText().toString()) != null) {
            if (!(textCategorie.getText().toString()).equals("")) {
                categoryNameO = (textCategorie.getText().toString());
            }
        }

        Intent updateArticleIntent = new Intent(ArticleOptions.this,NetworkService.class);
        updateArticleIntent.setAction(ARTICLEIDPATCH_ACTION);

        updateArticleIntent.putExtra(ID_KEY, articleID);
        updateArticleIntent.putExtra(TITLE_KEY,titleO);
        updateArticleIntent.putExtra(DESCRIPTION_KEY, descriptionO);
        updateArticleIntent.putExtra(ARTICLECATEGORYNAME_KEY, categoryNameO);
        updateArticleIntent.putExtra(IDCATEGORY_KEY, categoryIdO);
        updateArticleIntent.putExtra(ARTICLETYPE_KEY, typeO);
        updateArticleIntent.putExtra(INSHOPUNTIL_KEY, inShopUntilO);
        updateArticleIntent.putExtra(PRICE_KEY, valueO);
        updateArticleIntent.putExtra(NAME_KEY, nameO);
        updateArticleIntent.putExtra(STREET_KEY, streetO);
        updateArticleIntent.putExtra(PHONE_KEY, phoneO);
        updateArticleIntent.putExtra(EMAIL_KEY, emailO);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("articlePicture", pictureO);
        editor.commit();

        startService(updateArticleIntent);
        Toast.makeText(ArticleOptions.this, "ARTICLE UPDATED", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void deleteArticle(){
        //TODO man bekommt nen 500er error !?!
        Intent articleDeleteIntent = new Intent(this, NetworkService.class);
        articleDeleteIntent.setAction(ARTICLEIDDELETE_ACTION);
        articleDeleteIntent.putExtra(ID_KEY, articleID);
        startService(articleDeleteIntent);
        Toast.makeText(ArticleOptions.this, "ARTICLE GELÖSCHT", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void chooseCategory() {
        DB database = DB.getAppDatabase(this);
        List<ArticleCategories> categories = database.dbdao().getAllCategories();
        Log.d("CHOOSECATEGORY", "chooseCategory: size "+categories.size());
        //categories[];
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
                textCategorie.setText(chooseCategory[which]);
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}







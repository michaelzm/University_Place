package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.ur.wi2_intern.ur_place.urplace.ImageUtil;
import de.ur.wi2_intern.ur_place.urplace.MessagingActivity;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.User;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.Article;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;
import de.ur.wi2_intern.ur_place.urplace.retrofit.responses.ProfileMeGetResponse;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleRoom;
import de.ur.wi2_intern.ur_place.urplace.room.models.Profile;

import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEBOUGHT_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEIDDELETE_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEIDPATCH_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEIDPUT_ACTION;
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

public class DetailedArticleActivity extends AppCompatActivity{

    private static final String TAG = "ShowDetailedArticle";

    //todo detailedApp add created and runtime in shop
    TextView tv_until;
    TextView tv_created;
    TextView tv_title;
    TextView tv_price;
    TextView tv_description;
    TextView tv_contact;
    ImageView iv_picture;
    ImageButton favorite;
    Button buy;
    EditText bid_price;


    String title;
    Double price;
    String description;
    String name;
    String street;
    String email;
    String phone;
    String created_date;
    String inShopUntil;
    String picture;
    String categoryName;
    String type;
    int sellerId;
    int categoryId;
    boolean bought;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detailed_article);
        Log.d(TAG, "onCreate: started");
        final DB database = DB.getAppDatabase(this);
        //Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
        int mInt = b.getInt("Integer");
        Log.d(TAG, "onCreate: id " + mInt);
        final ArticleRoom article = database.dbdao().getArtilceById(mInt);
        //final Profile profile = ;
        sellerId=article.getSellerId();
        inShopUntil = article.getInShopUntil();
        created_date = article.getDate();
        title = article.getTitle();
        description = article.getDescription();
        name = article.getName();
        street = article.getStreet();
        email = article.getEmail();
        price = article.getPrice();
        picture = article.getPicture();
        phone = article.getTelephone();
        type = article.getArticleType().toString();
        categoryId = article.getCategoryId();
        categoryName=article.getArticleCategoryTitle();
        bought=article.getBought();
        id= (int) article.getId();

        tv_price = findViewById(R.id.tv_sa_price);
        buy = findViewById(R.id.bt_sa_buy);
        bid_price = findViewById(R.id.et_sa_bid_price);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor editor = preferences.edit();
        //Intent profileUpdateIntent = new Intent(this, NetworkService.class);
        //profileUpdateIntent.setAction(PROFILEMEPUT_ACTION);

        final int idO = preferences.getInt("nutzerID", -1);
        if(idO == -1){
            Log.d(TAG, "Problem with ProfileId in SP");
        }



        if (article.getBought()==false && article.getSellerId()!= idO) {
            if (article.getArticleType() == ArticleType.BUY) {

                buy.setText("Artikel kaufen");
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DateFormat formatter =  new SimpleDateFormat("yyyy-mm-dd");
                        Date date = null;
                        try {
                            date = formatter.parse(inShopUntil);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (date.before(Calendar.getInstance().getTime()) ) {
                            article.setBought(true);
                            bought = true;
                            Log.d(TAG, "on click: article bought");
                            Toast toast = Toast.makeText(getApplicationContext(), "Artikel gekauft", Toast.LENGTH_SHORT);
                            toast.show();
                            updateArticle(price, bought);
                            openChat(sellerId);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Artikel nicht mehr verfügbar", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                tv_price.setText("Preis: " + price.toString() + "€");
                bid_price.setVisibility(View.INVISIBLE);
            }

            else if (article.getArticleType() == ArticleType.BID) {
                //tv_price.setVisibility(View.INVISIBLE);
                tv_price.setText("Aktuelles Gebot (in €): ");
                bid_price.setText(price.toString());

                buy.setText("auf Artikel bieten");
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bidString = bid_price.getText().toString();
                        double bid = Double.parseDouble(bidString);
                        DateFormat formatter =  new SimpleDateFormat("yyyy-mm-dd");
                        Date date = null;
                        try {
                            date = formatter.parse(inShopUntil);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (bid > article.getPrice() &&  date.before(Calendar.getInstance().getTime())) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Gebot abgegeben in Höhe von " + bid + "€", Toast.LENGTH_SHORT);
                            toast.show();
                            price = bid;
                            article.setPrice(price);
                            Log.d(TAG, "on click: bid" + bid + article.getPrice());
                            updateArticle(price, bought);
                        } else if (bid <= article.getPrice() && date.before(Calendar.getInstance().getTime())){
                            Toast toast = Toast.makeText(getApplicationContext(), "Gebot zu niedrig", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Angebot nicht mehr verfügbar", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }


                });

            } else {
                bid_price.setVisibility(View.INVISIBLE);
                tv_price.setVisibility(View.INVISIBLE);
                buy.setText("Artikel gratis erwerben");
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DateFormat formatter =  new SimpleDateFormat("dd/mm/yyyy");
                        Date date = null;
                        try {
                            date = formatter.parse(inShopUntil);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (date.before(Calendar.getInstance().getTime())) {
                            Log.d(TAG, "on click: present");
                            Toast toast = Toast.makeText(getApplicationContext(), "Artikel gratis erworben", Toast.LENGTH_SHORT);
                            toast.show();
                            bought = true;
                            article.setBought(true);
                            updateArticle(price, bought);
                            openChat(sellerId);
                        }
                    }
                });

            }
        } else if (article.getBought()==true && article.getSellerId()!= idO){
            buy.setVisibility(View.INVISIBLE);
            tv_price.setText("Angebot nicht mehr verfügbar");
            bid_price.setVisibility(View.INVISIBLE);
        } else {
            buy.setText("Angebot löschen");
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteArticle((int) article.getId());
                }
            });
            bid_price.setVisibility(View.INVISIBLE);
            tv_price.setText("Preis: " + price.toString() + "€");;
        }


        favorite = findViewById(R.id.favorite_button);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: favorite set");
                Context context = getApplicationContext();

                int duration = Toast.LENGTH_SHORT;
                String text = title + " als Favorit ";
                if (article.getFavorite() == false) {
                    article.setFavorite(true);
                    text += "hinzugefügt";
                } else {
                    text += " entfernt";
                    article.setFavorite(false);
                }



                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                database.dbdao().updateArticle(article);

            }
        });


        tv_until = findViewById(R.id.tv_inshopUntil);
        tv_until.setText("Im Shop bis: " + inShopUntil);
        //todo datum umändern format
        tv_created = findViewById(R.id.tv_created);
        tv_created.setText("Erstellt: " + created_date);
        tv_title = findViewById(R.id.tv_sa_title);
        tv_title.setText("Titel: " + title);

        iv_picture = findViewById(R.id.iv_sa_article);
        Bitmap bitmap;
        try {
            bitmap = ImageUtil.convert(picture);
            iv_picture.setImageBitmap(bitmap);
        } catch (NullPointerException expection){
            Log.d(TAG, "onCreate: nullpointer exception");;
        }

        tv_description = findViewById(R.id.tv_sa_description);
        tv_description.setText("Beschreibung: "+description);

        tv_contact = findViewById(R.id.tv_sa_contact);
        tv_contact.setText("Name des Verkäufers: " +name + "\n"+ "Straße: "+street + "\n"+ "Email: "+email + "\n"+ "Telefonnummer: "+phone );

        }

        public void updateArticle(Double price, boolean bought) {
            Intent createArticleIntent = new Intent(DetailedArticleActivity.this,NetworkService.class);
            createArticleIntent.setAction(ARTICLEIDPATCH_ACTION);
            createArticleIntent.putExtra(ID_KEY, id);
            createArticleIntent.putExtra(TITLE_KEY,title);
            createArticleIntent.putExtra(DESCRIPTION_KEY, description);
            createArticleIntent.putExtra(ARTICLECATEGORYNAME_KEY, categoryName);
            createArticleIntent.putExtra(IDCATEGORY_KEY, categoryId);
            createArticleIntent.putExtra(ARTICLETYPE_KEY, type);
            createArticleIntent.putExtra(INSHOPUNTIL_KEY, inShopUntil);
            createArticleIntent.putExtra(PRICE_KEY, price);
            createArticleIntent.putExtra(NAME_KEY, name);
            createArticleIntent.putExtra(STREET_KEY, street);
            createArticleIntent.putExtra(PHONE_KEY, phone);
            createArticleIntent.putExtra(EMAIL_KEY, email);
            createArticleIntent.putExtra(ARTICLEBOUGHT_KEY, bought);
            //Picturestring too long for intent.extra
            //createArticleIntent.putExtra(PICTURE_KEY, picture);
            Log.d(TAG, "Artikel Preis: "+ price);
            startService(createArticleIntent);
            //Intent intent = new Intent(CreateArticleActivity.this, MarketplaceMainActivity.class);
            //startActivity(intent);
        }

        public void deleteArticle (int id) {
        Intent deleteArticleIntent = new Intent(DetailedArticleActivity.this,NetworkService.class);
        deleteArticleIntent.setAction(ARTICLEIDDELETE_ACTION);
        deleteArticleIntent.putExtra(ID_KEY, id);
        startService(deleteArticleIntent);
        Log.d(TAG, "Artikel gelöscht!");
        Toast toast = Toast.makeText(getApplicationContext(), "Artikel gelöscht!", Toast.LENGTH_SHORT);
        toast.show();
        }

        void openChat(int id0){
        Intent chatintent = new Intent(this, MessagingActivity.class);
        chatintent.putExtra("userID", id0);
        startActivity(chatintent);
        }

}

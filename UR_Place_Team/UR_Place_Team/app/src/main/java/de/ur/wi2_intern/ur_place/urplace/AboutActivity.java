package de.ur.wi2_intern.ur_place.urplace;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.ur.wi2_intern.ur_place.urplace.Login.LoginActivity;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.ArticleOptions;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.MarketplaceMainActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.Settings.SettingsActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;

import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.decodeBase64;
import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.getResizedBitmap;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYNAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEIDGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEPOST_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLESEARCHGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLETYPE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.DESCRIPTION_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.EMAIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ID_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.INSHOPUNTIL_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.NAME_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PHONE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PICTURE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PRICE_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.SEARCH_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.STREET_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.TITLE_KEY;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        this.navigationView = (NavigationView) findViewById(R.id.nv_ma_navigation_drawer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.te_menu_about));
        navigationView.setNavigationItemSelectedListener(this);

        createListeners();

        //test();
    }

    //TODO REMOVE BEFOR FINAL
    public void test(){
        Intent loginIntent = new Intent(this, NetworkService.class);
        loginIntent.setAction(ARTICLEIDGET_ACTION);
        loginIntent.putExtra(ID_KEY, 6);
/*
        loginIntent.putExtra(TITLE_KEY, "deletingIDKEY");
        loginIntent.putExtra(DESCRIPTION_KEY, "red Bike");
        loginIntent.putExtra(ID_KEY, 23);
        loginIntent.putExtra(ARTICLECATEGORYNAME_KEY, "Bikes");
        loginIntent.putExtra(ARTICLETYPE_KEY, "BUY");
        loginIntent.putExtra(INSHOPUNTIL_KEY, "2018-07-23");
        loginIntent.putExtra(PRICE_KEY, 23.56);
        loginIntent.putExtra(PICTURE_KEY, "");
        loginIntent.putExtra(NAME_KEY, "seller1");
        loginIntent.putExtra(STREET_KEY, "straße 23");
        loginIntent.putExtra(PHONE_KEY, "017065464654");
        loginIntent.putExtra(EMAIL_KEY, "sgdfh@sdh.de");
*/
        startService(loginIntent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
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

    public void updateHeader() {
        ImageView img = (ImageView) findViewById(R.id.iv_menu_profilpicture);
        TextView text = (TextView) findViewById(R.id.te_menu_profilname);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String newText = preferences.getString("username", "");
        text.setText(newText);
        if (preferences.getString("picture", null) != null) {
            String newTextPicture = preferences.getString("picture", "");
            Bitmap newBitmapPicture = decodeBase64(newTextPicture);
            img.setImageBitmap((newBitmapPicture));
        }
    }

    public void startLogoutActivity(){
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
}

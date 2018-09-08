package de.ur.wi2_intern.ur_place.urplace.Settings;

import android.app.FragmentTransaction;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import de.ur.wi2_intern.ur_place.urplace.AboutActivity;
import de.ur.wi2_intern.ur_place.urplace.FavouritesActivity;
import de.ur.wi2_intern.ur_place.urplace.FriendlistActivity;
import de.ur.wi2_intern.ur_place.urplace.Login.LoginActivity;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.MarketplaceMainActivity;
import de.ur.wi2_intern.ur_place.urplace.MessagesActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.SearchActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;

import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.decodeBase64;
import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.getResizedBitmap;

public class SettingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        this.navigationView = (NavigationView) findViewById(R.id.nv_ma_navigation_drawer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.te_menu_settings));
        navigationView.setNavigationItemSelectedListener(this);

        SettingsFragment fragment = new SettingsFragment();
        FragmentTransaction tr = getFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_placeholder, fragment);
        tr.commit();

        createListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
}

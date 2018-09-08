package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.ur.wi2_intern.ur_place.urplace.AboutActivity;
import de.ur.wi2_intern.ur_place.urplace.FavouritesActivity;
import de.ur.wi2_intern.ur_place.urplace.FriendlistActivity;
import de.ur.wi2_intern.ur_place.urplace.Login.LoginActivity;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.Interfaces.IMPPresenter;
import de.ur.wi2_intern.ur_place.urplace.MessagesActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.SearchActivity;
import de.ur.wi2_intern.ur_place.urplace.Settings.SettingsActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;

import static de.ur.wi2_intern.ur_place.urplace.Login.RegisterActivity.decodeBase64;
import static de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity.RECEIVER_INTENT_NAME;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEGET_ACTION;

public class MarketplaceMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private Button bt_mp_show_offers;
    private Button bt_mp_favorites;
    private Button bt_mp_search_offers;
    private Button bt_mp_create_offer;


    private ActionBar actionBar;

    private IntentFilter receiverIntentFilter;
    private BroadcastReceiver broadcastReceiver;

    private IMPPresenter presenter;


    private SectionsStatePagerAdapter sectionsStatePagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_main);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        this.navigationView = (NavigationView) findViewById(R.id.nv_ma_navigation_drawer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.te_menu_marketplace));
        navigationView.setNavigationItemSelectedListener(this);
        createBroadcastReceiverAndIntentFilter();
        updateMarketplace();
        refreshCategories();

        sectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        FragmentManager fm = getFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        createListeners();

    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MarketplaceHomeFragment(), "MarketplaceHomeFragment");
        adapter.addFragment(new CreateCategoryFragment(), "CreateCategoryFragment");
        viewPager.setAdapter(adapter);
    }
    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void onBackPressed() {
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_marketplace, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();


    }
    public void updateMarketplace(){
        Toast.makeText(this, "Marketplace wird aktualisiert", Toast.LENGTH_SHORT).show();
        Intent getArticle = new Intent(this, NetworkService.class);
        getArticle.setAction(ARTICLEGET_ACTION);
        Intent intent = new Intent(this, ShowArticles.class);
        this.startService(getArticle);
        // startActivity(intent);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    private void createBroadcastReceiverAndIntentFilter() {
        broadcastReceiver = new MainActivity.MainActivityBroadcastReceiver();

        receiverIntentFilter = new IntentFilter(RECEIVER_INTENT_NAME);
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

    public void refreshCategories(){
        Intent articleCategoryGetIntent = new Intent(this, NetworkService.class);
        articleCategoryGetIntent.setAction(ARTICLECATEGORYGET_ACTION);
        this.startService(articleCategoryGetIntent);
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



}

package de.ur.wi2_intern.ur_place.urplace;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.Login.LoginActivity;
import de.ur.wi2_intern.ur_place.urplace.Marketplace.MarketplaceMainActivity;
import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.Settings.SettingsActivity;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Button searchButton;
    private EditText userInput;
    private ListView searchResults;
    private SearchAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        this.navigationView = (NavigationView) findViewById(R.id.nv_ma_navigation_drawer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.te_menu_search));
        navigationView.setNavigationItemSelectedListener(this);


        setup();
    }



    private void setup(){
        adapter = new SearchAdapter(getApplicationContext(),R.layout.list_item_user_search);
        searchButton = (Button)findViewById(R.id.button_search);
        userInput = (EditText)findViewById(R.id.editText_search);
        searchResults = (ListView)findViewById(R.id.listView_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = userInput.getText().toString();
                getResults(input);
            }
        });
    }

    private void getResults(String input){
        //todo server query


    }

    private void fillResults(List<User> results){
        for(int i = 0;i<results.size();i++){
            adapter.add(results.get(i));
        }

        searchResults.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
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

package de.ur.wi2_intern.ur_place.urplace.Login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.Settings.SettingsActivity;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;
import de.ur.wi2_intern.ur_place.urplace.retrofit.requests.LoginRequest;

import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.LOGIN_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.PASSWORD_KEY;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.USERNAME_KEY;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button loginbutton;
    private Button registerbutton;

    private EditText textUsername;
    private EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.toolbar = (Toolbar) findViewById(R.id.tb_ma_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.te_menu_login));
        textUsername = (EditText) findViewById(R.id.username);
        textPassword = (EditText) findViewById(R.id.password);
        loginbutton = (Button) findViewById(R.id.bu_login_loginbutton);
        registerbutton = (Button) findViewById(R.id.bu_login_toregisterbutton);
        loginbutton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                tryLogin();
            }
        });
        registerbutton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });

        startUP();
        checkLoginOptions();
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

    @Override
    public void onBackPressed() {

    }

    public void startUP() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getString("login", null) == null) {
            editor.putString("login", "false");
        }
        if (preferences.getString("loginSwitch", null) == null) {
            editor.putString("loginSwitch", "true");
        }
        if (preferences.getString("picture", null) == null) {
            editor.putString("picture", null);
        }
        editor.commit();
    }

    private void tryLogin() {
        String idInput = textUsername.getText().toString();
        String pwInput = textPassword.getText().toString();

        if (isNetworkAvailable()) {
            //LoginRequest loginRequest= new LoginRequest(idInput, pwInput);
            Intent loginIntent = new Intent(this, NetworkService.class);
            loginIntent.setAction(LOGIN_ACTION);
            loginIntent.putExtra(USERNAME_KEY, idInput);
            loginIntent.putExtra(PASSWORD_KEY, pwInput);
            startService(loginIntent);
        }
        else{
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            if(idInput.equals(preferences.getString("username", "u")) && pwInput.equals(preferences.getString("password", "p"))){
                Toast.makeText(LoginActivity.this, "OFFLINE MODE", Toast.LENGTH_SHORT).show();
                Intent implicitIntent = new Intent(this, MainActivity.class);
                startActivity(implicitIntent);
            }
            else{
                Toast.makeText(LoginActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void tryAutoLogin() {
        if (isNetworkAvailable()) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

            String idInput = preferences.getString("username", "u");
            String pwInput = preferences.getString("password", "p");

            //LoginRequest loginRequest= new LoginRequest(idInput, pwInput);
            Intent loginIntent = new Intent(this, NetworkService.class);
            loginIntent.setAction(LOGIN_ACTION);
            loginIntent.putExtra(USERNAME_KEY, idInput);
            loginIntent.putExtra(PASSWORD_KEY, pwInput);
            startService(loginIntent);
        } else {
            Toast.makeText(LoginActivity.this, "OFFLINE MODE", Toast.LENGTH_SHORT).show();
            Intent implicitIntent = new Intent(this, MainActivity.class);
            startActivity(implicitIntent);
        }


    }

    private void checkLoginOptions() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString("login", "false").equals("true")) {
            tryAutoLogin();
        }
    }

    public void register() {
        Intent implicitIntent = new Intent(this, RegisterActivity.class);
        startActivity(implicitIntent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

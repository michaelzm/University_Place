package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.Profile.OptionsActivity;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleRoom;

import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEGET_ACTION;


public class ShowArticles extends AppCompatActivity {
    private static final String TAG = "ShowArticles";

    private List<ArticleRoom> mArticles;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB database = DB.getAppDatabase(this);
        setContentView(R.layout.activity_show_articles);
        Log.d(TAG, "onCreate: started");
        Intent intent=getIntent();

        if(intent.getAction()=="ALLARTICLES") {
            mArticles = database.dbdao().getAllArticles();
            initRecyclerView();
        }
        else if(intent.getAction()=="OWNARTICLES") {
            int id;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            id=preferences.getInt("nutzerID", -1);
            if(id==-1){
                Intent intentId = new Intent(this, NetworkService.class);
                intent.setAction("PROFILEMEGET_ACTION");
                startService(intentId);
                id=preferences.getInt("nutzerID", -1);
            }
            Log.d(TAG, "onCreate: userid:"+id);
            mArticles = database.dbdao().getOwnArticles(id);
            initRecyclerView();
        }
        else if(intent.getAction()=="FAVORITEARTICLES") {
            mArticles = database.dbdao().getFavoriteArticles();
            initRecyclerView();
        }
        else if(intent.getAction()=="CATEGORYARTICLES") {
            String category=intent.getStringExtra("category");
            mArticles = database.dbdao().getArticleByCategory(category);
            initRecyclerView();
        }
        else if(intent.getAction()=="PRICEARTICLES") {
            String lowPrice = intent.getStringExtra("lowPrice");
            String highPrice=intent.getStringExtra("highPrice");
            int lowerPrice=Integer.parseInt(lowPrice);
            int higherPrice=Integer.parseInt(highPrice);
            mArticles = database.dbdao().getArticleByPrice(lowerPrice, higherPrice);
            initRecyclerView();
        }
        else if(intent.getAction()=="TITLEARTICLES") {
            String title=intent.getStringExtra("filterTitle");
            mArticles = database.dbdao().getArticleBySearchTitle("%"+title+"%");
            initRecyclerView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_showarticle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.showArticle_options:

                Log.d(TAG, "Started ArticleShowOptions");
                Intent intent=new Intent(this, MarketplaceFilterActivity.class);
                startActivity(intent);

                //Hier intent für die activity des Zahnrades einfügen und in manifest parent activity festlegen für den "simplen" Rückweg

                return true;
        }

//        Intent implicitIntent = new Intent(this, ProfileSearch.class);
//        startActivity(implicitIntent);

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ArticleAdapter adapter = new ArticleAdapter(mArticles, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

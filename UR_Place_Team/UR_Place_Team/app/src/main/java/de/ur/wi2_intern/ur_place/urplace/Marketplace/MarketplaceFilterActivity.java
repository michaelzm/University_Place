package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.room.DB;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleCategories;

public class MarketplaceFilterActivity extends AppCompatActivity{
    final DB database = DB.getAppDatabase(this);
    private TextView input_category;
    private EditText input_lowPrice;
    private EditText input_highPrice;
    private EditText input_searchTitle;
    private Button button_submit;
    private RadioButton checkPrice;
    private RadioButton checkCategory;
    private RadioButton checkTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_filter);
        input_category = (TextView) findViewById(R.id.choose_category);
        input_lowPrice=(EditText) findViewById(R.id.input_low_price);
        input_highPrice=(EditText) findViewById(R.id.input_high_price);
        input_searchTitle=(EditText) findViewById(R.id.searchTitle);
        button_submit=(Button) findViewById(R.id.submit_filter);

        checkPrice=(RadioButton) findViewById(R.id.radio_price);
        checkCategory=(RadioButton) findViewById(R.id.radio_category);
        checkTitle=(RadioButton) findViewById(R.id.radio_title);


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

        //todo radio buttons
        button_submit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(MarketplaceFilterActivity.this,ShowArticles.class);
                if(checkCategory.isChecked()) {
                    filterIntent.putExtra("category", input_category.getText().toString());
                    filterIntent.setAction("CATEGORYARTICLES");
                    startActivity(filterIntent);
                }
                if(checkPrice.isChecked()) {
                    if(input_lowPrice.getText().toString()!="" &&input_highPrice.getText().toString()!=""){
                    filterIntent.putExtra("lowPrice", input_lowPrice.getText().toString());
                    filterIntent.putExtra("highPrice", input_highPrice.getText().toString());
                    filterIntent.setAction("PRICEARTICLES");
                    startActivity(filterIntent);}
                    else{
                        Toast.makeText(MarketplaceFilterActivity.this, "Bitte Preisgrenzen vollständig ausfüllen", Toast.LENGTH_SHORT).show();
                    }
                }
                if(checkTitle.isChecked()) {
                    filterIntent.putExtra("filterTitle", input_searchTitle.getText().toString());
                    filterIntent.setAction("TITLEARTICLES");
                    startActivity(filterIntent);
                }
            }
        });
    }
    public void chooseCategory() {


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
                input_category.setText(chooseCategory[which]);
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}

package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;


import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYGET_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLECATEGORYPOST_ACTION;
import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.CATEGORY_KEY;


public class CreateCategoryFragment extends Fragment {
    private static final String TAG = "MarketplaceHomeFragment";


    private EditText input_category; //nicht so wie gebraucht wird
    private Button bt_publish;
    private Button getCategory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_category_fragment, container, false);
        input_category=(EditText) view.findViewById(R.id.text_input_category);
        bt_publish = (Button) view.findViewById(R.id.bt_co_publish_offer);
        getCategory=(Button) view.findViewById(R.id.bt_co_get_category);

        bt_publish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String category=input_category.getText().toString();

                Intent articleCategoryIntent = new Intent(getActivity(), NetworkService.class);
                articleCategoryIntent.setAction(ARTICLECATEGORYPOST_ACTION);

                articleCategoryIntent.putExtra(CATEGORY_KEY, category);
                getActivity().startService(articleCategoryIntent);
                setEditTextToNull();
                Log.d(TAG, "onClick: Kategorie erfolgreich erstellt");
                updateCategories();

            }
        });

        getCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent articleCategoryGetIntent = new Intent(getActivity(), NetworkService.class);
                articleCategoryGetIntent.setAction(ARTICLECATEGORYGET_ACTION);

                getActivity().startService(articleCategoryGetIntent);

                Log.d(TAG, "onClick: Kategorie erstellt");

            }
        });

        return view;

    }
    public void updateCategories(){

        Intent articleCategoryGetIntent = new Intent(getActivity(), NetworkService.class);
        articleCategoryGetIntent.setAction(ARTICLECATEGORYGET_ACTION);
        getActivity().startService(articleCategoryGetIntent);
        Toast.makeText(getActivity(), "Kategorie erfolgreich erstellt", Toast.LENGTH_SHORT).show();

    }
    public void setEditTextToNull() {
        input_category.setText(null);
    }

    public void chooseOffer(){

    }

}

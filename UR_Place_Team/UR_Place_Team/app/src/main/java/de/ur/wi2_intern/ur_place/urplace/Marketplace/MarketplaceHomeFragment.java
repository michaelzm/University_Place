package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import de.ur.wi2_intern.ur_place.urplace.Profile.MainActivity;
import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService;

import static de.ur.wi2_intern.ur_place.urplace.retrofit.NetworkService.ARTICLEGET_ACTION;

public class MarketplaceHomeFragment extends Fragment {
    private static final String TAG = "Marketplace Home";

    private Button bt_mp_create_offer;
    private Button bt_mp_search_offers;
    private Button bt_mp_favorites;
    private Button bt_mp_show_offer;
    private Button debug1;
    private Button debug2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.marketplace_home_fragment, container, false);

        bt_mp_create_offer=(Button) view.findViewById(R.id.bt_mp_create_offer);
        bt_mp_favorites=(Button) view.findViewById(R.id.bt_mp_favorites);
        bt_mp_search_offers=(Button) view.findViewById(R.id.bt_mp_search_offers);
        bt_mp_show_offer=(Button) view.findViewById(R.id.bt_mp_show_offers);
        debug1=(Button) view.findViewById(R.id.debug1);
        debug2=(Button) view.findViewById(R.id.debug2);



        Log.d(TAG, "onCreateView: started");

        bt_mp_create_offer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startCreate = new Intent(getActivity(), CreateArticleActivity.class);
                startActivity(startCreate);
            }
        });

        bt_mp_favorites.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), ShowArticles.class);
                intent.setAction("FAVORITEARTICLES");
                startActivity(intent);
            }
        });

        bt_mp_search_offers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), ShowArticles.class);
                intent.setAction("OWNARTICLES");
                startActivity(intent);


            }
        });


        bt_mp_show_offer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), ShowArticles.class);
                intent.setAction("ALLARTICLES");
                startActivity(intent);


            }
        });
        debug1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "kategorie erstellen TEST", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ShowArticles.class);
                ((MarketplaceMainActivity)getActivity()).setViewPager(3);


            }
        });

        debug2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Marketplace wird aktualisiert", Toast.LENGTH_SHORT).show();
                Intent getArticle = new Intent(getActivity(), NetworkService.class);
                getArticle.setAction(ARTICLEGET_ACTION);
                Intent intent = new Intent(getActivity(), ShowArticles.class);
                getActivity().startService(getArticle);
                // startActivity(intent);


            }
        });


        return view;

    }

}

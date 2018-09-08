package de.ur.wi2_intern.ur_place.urplace.Marketplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import de.ur.wi2_intern.ur_place.urplace.R;
import de.ur.wi2_intern.ur_place.urplace.retrofit.models.ArticleType;
import de.ur.wi2_intern.ur_place.urplace.room.models.ArticleRoom;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{
    private static final String TAG = "ArticleAdapter";

    private List<ArticleRoom> mArticle;
    private Context mContext;

    public ArticleAdapter(List<ArticleRoom> mArticleNames, Context mContext) {
        this.mArticle = mArticleNames;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:  called");
        holder.articleTitle.setText(mArticle.get(position).getTitle());
        //todo double runden auf 2 nachkommastellen



        holder.articlePrice.setText(String.valueOf(mArticle.get(position).getPrice()));
        String type="";
        if(mArticle.get(position).getArticleType()== ArticleType.BID){
            type="Auktion";
        }
        else if(mArticle.get(position).getArticleType()== ArticleType.BUY){
            type="Sofortkauf";

        }
        else if(mArticle.get(position).getArticleType()== ArticleType.PRESENT){
            type = "zu verschenken";
        }
        holder.articleType.setText(type);

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= (int) mArticle.get(position).getId();
                Log.d(TAG, "onClick: clicked on" + mArticle.get(position));
                Intent intent = new Intent(mContext, DetailedArticleActivity.class);
                Bundle b = new Bundle();
                b.putInt("Integer", id );
                intent.putExtras(b);
                Log.d(TAG, "onClick: id adapter "+id);
                mContext.startActivity(intent);

                Toast.makeText(mContext, mArticle.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mArticle.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView articleType;
        TextView articleTitle;
        TextView articlePrice;
        RelativeLayout parent_layout;


        public ViewHolder(View itemView) {
            super(itemView);
            articleType = itemView.findViewById(R.id.article_type);
            articleTitle = itemView.findViewById(R.id.article_name);
            articlePrice = itemView.findViewById(R.id.article_price);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

}

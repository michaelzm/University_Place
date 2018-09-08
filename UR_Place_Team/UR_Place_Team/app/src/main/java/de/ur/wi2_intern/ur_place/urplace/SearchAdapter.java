package de.ur.wi2_intern.ur_place.urplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<User> {

    private TextView textView;
    private List<User> userList = new ArrayList<User>();
    private Context context;

    @Override
    public void add(User object) {
        userList.add(object);
        super.add(object);
    }

    public SearchAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.userList.size();
    }

    public User getItem(int index) {
        return this.userList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final User userObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(R.layout.list_item_user_search, parent, false);

        textView = (TextView) row.findViewById(R.id.list_item_search_textView);
        textView.setText(userObj.name);

        Button button = (Button)convertView.findViewById(R.id.button_friend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFriendRequest(userObj.userID);
            }
        });

        return row;
    }

    public void sendFriendRequest(int id){
        //todo server request
    }

}


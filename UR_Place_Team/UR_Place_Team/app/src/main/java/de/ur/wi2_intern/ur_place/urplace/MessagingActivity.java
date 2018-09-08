package de.ur.wi2_intern.ur_place.urplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MessagingActivity extends AppCompatActivity {

    ChatAdapter chatAdapter;
    ListView listView;
    Integer userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        userID = i.getIntExtra("userID",0);


        fillChat();




    }


    //setup for adapter, etc.
    private void fillChat(){

        chatAdapter = new ChatAdapter(getApplicationContext(), R.layout.list_item_message_right);
        loadMessages();
        listView = (ListView)findViewById(R.id.listView_messages);
        listView.setAdapter(chatAdapter);
    }

    //gets messages from server
    private void loadMessages(){
        //todo server query
    }


    //gets Message from texView
    public void getInput(View v){
        EditText editText = (EditText)findViewById(R.id.editText_message);
        String message = editText.getText().toString();
        editText.setText("");
        sendMessage(message);

    }

    //sends message to server
    public void sendMessage(String message){


        //todo server query


        fillChat();
    }

}

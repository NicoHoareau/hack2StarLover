package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseListAdapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TchatRoomActivity extends AppCompatActivity {

    private Button btn_send_message;
    private EditText message;
    private TextView chat_conversation;
    private String user_name, room_name;
    private DatabaseReference root;
    private String temp_key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tchat_room);

        btn_send_message = findViewById(R.id.btn_send_message);
        message = findViewById(R.id.et_msg_person);
        chat_conversation = findViewById(R.id.tv_conversation);

        user_name = getIntent().getExtras().get("username").toString();
        room_name = getIntent().getExtras().get("room_name").toString();
        ImageView ivPlanet = findViewById(R.id.iv_planet);
        if (room_name.equals("Tatooine") ){
            Drawable drawable = getResources().getDrawable(R.drawable.tatooine_back);
            ivPlanet.setImageDrawable(drawable);
        }
        if (room_name.equals("Naboo") ){
            Drawable drawable = getResources().getDrawable(R.drawable.naboo_back);
            ivPlanet.setImageDrawable(drawable);
        }
        if (room_name.equals("Alderaan") ){
            Drawable drawable = getResources().getDrawable(R.drawable.alderaan_back);
            ivPlanet.setImageDrawable(drawable);
        }
        if (room_name.equals("Kashyyyk") ){
            Drawable drawable = getResources().getDrawable(R.drawable.kashyyyk_back);
            ivPlanet.setImageDrawable(drawable);
        }
        if (room_name.equals("Coruscant") ){
            Drawable drawable = getResources().getDrawable(R.drawable.coruscant_back);
            ivPlanet.setImageDrawable(drawable);
        }
        if (room_name.equals("Kamino") ){
            Drawable drawable = getResources().getDrawable(R.drawable.kamino_back);
            ivPlanet.setImageDrawable(drawable);
        }
        if (room_name.equals("Jakku") ){
            Drawable drawable = getResources().getDrawable(R.drawable.jakku_back);
            ivPlanet.setImageDrawable(drawable);
        }if (room_name.equals("Dagobah") ){
            Drawable drawable = getResources().getDrawable(R.drawable.dagobah_back);
            ivPlanet.setImageDrawable(drawable);
        }

        root = FirebaseDatabase.getInstance().getReference("Match").child(room_name);

        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root = root.child(temp_key);

                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", user_name);
                map2.put("msg", message.getText().toString());

                message_root.updateChildren(map2);
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private String chat_msg, chat_user_name;

    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()){
            chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();
            chat_conversation.append("\n" + chat_user_name + " : " + "\n" + chat_msg + "\n");
        }
    }

}

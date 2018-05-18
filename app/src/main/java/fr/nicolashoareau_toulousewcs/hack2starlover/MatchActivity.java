package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatchActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    private String mUid;
    private ImageView avatar1;
    private ImageView avatar2;
    private TextView username1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        mDatabase = FirebaseDatabase.getInstance();
        mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRef = mDatabase.getReference("User").child(mUid);
        mRef.child("Profil").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String username = dataSnapshot.child("pseudo").getValue(String.class);
                Button btnMeet = findViewById(R.id.btn_meet_lover);
                btnMeet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goSelectPlanet = new Intent(MatchActivity.this, TchatActivity.class);
                        goSelectPlanet.putExtra("username",username);
                        startActivity(goSelectPlanet);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance();
        mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        avatar1 = findViewById(R.id.iv_picture_person1);
        avatar2 = findViewById(R.id.iv_picture_person2);
        username1 = findViewById(R.id.tv_usenme_match_person1);

        DatabaseReference pathID = mDatabase.getReference("User").child(mUid).child("Profil");
        pathID.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //For avatar
                if ((dataSnapshot.child("profilPic").getValue() != null)){
                    String url = dataSnapshot.child("profilPic").getValue(String.class);
                    Glide.with(getApplicationContext()).load(url).apply(RequestOptions.circleCropTransform()).into(avatar1);
                }
                //For username
                //For Username
                if ((dataSnapshot.child("pseudo").getValue() != null)){
                    String username = dataSnapshot.child("pseudo").getValue(String.class);
                    username1.setText(username);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });










        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        ImageView imgDeco = findViewById(R.id.img_deco);
        imgDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MatchActivity.this, LoginActivity.class));
                finish();
            }
        });



        ImageView imgAbandon = findViewById(R.id.iv_abandon2);
        imgAbandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToSelectLovers = new Intent(MatchActivity.this, SeeLoversActivity.class);
                startActivity(returnToSelectLovers);
            }
        });

    }
}

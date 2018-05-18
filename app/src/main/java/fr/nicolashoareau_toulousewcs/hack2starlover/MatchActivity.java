package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

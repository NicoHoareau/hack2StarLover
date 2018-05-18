package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeeLoversActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_lovers);

        String urlAvatar = getIntent().getExtras().get("urlAvatar").toString();
        String username = getIntent().getExtras().get("username2").toString();

        ImageView imgAvatar = findViewById(R.id.iv_avatar2);
        Glide.with(getApplicationContext()).load(urlAvatar).apply(RequestOptions.centerCropTransform()).into(imgAvatar);
        TextView tvUsername = findViewById(R.id.tv_username);
        tvUsername.setText(username);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("User");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        ImageView imgMatchLover = findViewById(R.id.iv_match_lover);
        imgMatchLover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMatch = new Intent(SeeLoversActivity.this, MatchActivity.class);
                startActivity(goToMatch);
            }
        });
        ImageView imgAbandon = findViewById(R.id.iv_abandon);
        imgAbandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChooseLover = new Intent(SeeLoversActivity.this, ChooseLoverActivity.class);
                startActivity(goToChooseLover);
            }
        });
        ImageView imgProfile = findViewById(R.id.iv_profile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToProfile = new Intent(SeeLoversActivity.this, ProfileActivity.class);
                startActivity(goToProfile);
            }
        });
    }
}

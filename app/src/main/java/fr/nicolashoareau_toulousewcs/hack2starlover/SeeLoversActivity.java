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

        final String urlAvatar = getIntent().getExtras().get("urlAvatar").toString();
        final String username = getIntent().getExtras().get("username2").toString();

        ImageView imgAvatar = findViewById(R.id.iv_avatar2);
        Glide.with(getApplicationContext()).load(urlAvatar).apply(RequestOptions.centerCropTransform()).into(imgAvatar);
        TextView tvUsername = findViewById(R.id.tv_username);
        tvUsername.setText(username);

        final ImageView item1 = findViewById(R.id.iv_item1);
        final ImageView item2 = findViewById(R.id.iv_item2);
        final ImageView item3 = findViewById(R.id.iv_item3);
        final ImageView item4 = findViewById(R.id.iv_item4);
        final TextView tvGender = findViewById(R.id.tv_gender);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("User");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot charaDataSnapshot : dataSnapshot.getChildren()) {
                    if (charaDataSnapshot.child("Profil").child("pseudo").getValue(String.class).equals(username) ) {
                        String uid = charaDataSnapshot.getKey().toString();
                        String genre = charaDataSnapshot.child("Profil").child("genre").getValue(String.class);
                        tvGender.setText(genre);
                        String url1 = charaDataSnapshot.child("Profil").child("charaChoose1").getValue(String.class);
                        Glide.with(getApplicationContext()).load(url1).apply(RequestOptions.circleCropTransform()).into(item1);
                        String url2 = charaDataSnapshot.child("Profil").child("charaChoose2").getValue(String.class);
                        Glide.with(getApplicationContext()).load(url2).apply(RequestOptions.circleCropTransform()).into(item2);
                        String url3 = charaDataSnapshot.child("Profil").child("charaChoose3").getValue(String.class);
                        Glide.with(getApplicationContext()).load(url3).apply(RequestOptions.circleCropTransform()).into(item3);
                        String url4 = charaDataSnapshot.child("Profil").child("charaChoose4").getValue(String.class);
                        Glide.with(getApplicationContext()).load(url4).apply(RequestOptions.circleCropTransform()).into(item4);
                    }
                }

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
                goToMatch.putExtra("pseudo", username);
                goToMatch.putExtra("profilPic", urlAvatar);
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

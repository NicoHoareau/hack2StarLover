package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseReference mUserDatabaase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView tvname = findViewById(R.id.tv_username);
        final TextView tvgenre = findViewById(R.id.tv_gender);
        final TextView tvside = findViewById(R.id.tv_side);
        final ImageView ivAvatar = findViewById(R.id.iv_avatar);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();

        mUserDatabaase = FirebaseDatabase.getInstance().getReference().child("User")
                .child(current_uid).child("Profil");
        mUserDatabaase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("pseudo").getValue().toString();
                tvname.setText(name);

                String genre = dataSnapshot.child("genre").getValue().toString();
                tvgenre.setText(genre);

                String side = dataSnapshot.child("side").getValue().toString();
                tvside.setText(side);

                String image = (String) dataSnapshot.child("profilPic").getValue();
                Glide.with(ProfileActivity.this)
                        .load(image)
                        .into(ivAvatar);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

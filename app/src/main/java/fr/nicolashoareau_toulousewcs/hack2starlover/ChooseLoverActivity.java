package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChooseLoverActivity extends AppCompatActivity {
    private GridView mLoverGrid;
    private DatabaseReference mDatabaseReference;

    private FirebaseUser mUser;
    private UserModel mModelUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lover);





        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("User" +mUser+ "/profilPic");

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        final ArrayList arrayList = new ArrayList<>();
        mLoverGrid = findViewById(R.id.grid_lovers);

        final LoverGridAdapter lover = new LoverGridAdapter(this, arrayList);


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userPic : dataSnapshot.getChildren()){
                    mModelUser = userPic.getValue(UserModel.class);
                    arrayList.add(mModelUser);
                    mLoverGrid.setAdapter(lover);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        mLoverGrid = findViewById(R.id.grid_lovers);







    }
}


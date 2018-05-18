package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ChoosePersoActivity extends AppCompatActivity {

    CharacterModel currentCharacter;
    String charaChoose1;
    String charaChoose2;
    String charaChoose3;
    String charaChoose4;

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_perso);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        final GridView listCharacter = findViewById(R.id.gv_character);
        final ArrayList<CharacterModel> results = new ArrayList<>();

        final CharacterAdapter adapter = new CharacterAdapter(ChoosePersoActivity.this, results);
        listCharacter.setAdapter(adapter);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().orderByChild("image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                results.clear();
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    CharacterModel characterModel = userDataSnapshot.getValue(CharacterModel.class);
                    results.add(new CharacterModel(characterModel.getImage()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listCharacter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currentCharacter = results.get(position);

                ImageView imgCharacter = findViewById(R.id.iv_character_choose);
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(imgCharacter);

                database.getReference().orderByChild("image").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot charaDataSnapshot : dataSnapshot.getChildren()) {
                            if (charaDataSnapshot.child("image").getValue() == currentCharacter.getImage()) {
                                String sentence = charaDataSnapshot.child("desc").getValue(String.class);
                                TextView seeSentence = findViewById(R.id.tv_sentence);
                                seeSentence.setText(sentence);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        final ImageView choose1 = findViewById(R.id.iv_choose1);
        choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(choose1);
                charaChoose1 = currentCharacter.getImage();
            }
        });

        final ImageView choose2 = findViewById(R.id.iv_choose2);
        choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(choose2);
                charaChoose2 = currentCharacter.getImage();
            }
        });

        final ImageView choose3 = findViewById(R.id.iv_choose3);
        choose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(choose3);
                charaChoose3 = currentCharacter.getImage();
            }
        });

        final ImageView choose4 = findViewById(R.id.iv_choose4);
        choose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(choose4);
                charaChoose4 = currentCharacter.getImage();
            }
        });

        Button validChara = findViewById(R.id.button_valid_chara);
        validChara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.child(user.getUid()).child("Profil").child("charaChoose1").setValue(charaChoose1);
                mDatabaseReference.child(user.getUid()).child("Profil").child("charaChoose2").setValue(charaChoose2);
                mDatabaseReference.child(user.getUid()).child("Profil").child("charaChoose3").setValue(charaChoose3);
                mDatabaseReference.child(user.getUid()).child("Profil").child("charaChoose4").setValue(charaChoose4);

                Intent intent = new Intent(ChoosePersoActivity.this, ChooseLoverActivity.class);
                startActivity(intent);

            }
        });




    }
}

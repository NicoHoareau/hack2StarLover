package fr.nicolashoareau_toulousewcs.hack2starlover;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChoosePersoActivity extends AppCompatActivity {

    CharacterModel currentCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_perso);

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
            }
        });

        final ImageView choose2 = findViewById(R.id.iv_choose2);
        choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(choose2);
            }
        });

        final ImageView choose3 = findViewById(R.id.iv_choose3);
        choose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(choose3);
            }
        });

        final ImageView choose4 = findViewById(R.id.iv_choose4);
        choose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(choose4);
            }
        });

        Button validChara = findViewById(R.id.button_valid_chara);
        validChara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}

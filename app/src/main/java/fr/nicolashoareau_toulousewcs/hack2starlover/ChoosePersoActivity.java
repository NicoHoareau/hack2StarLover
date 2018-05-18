package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ChoosePersoActivity extends AppCompatActivity {

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

                final CharacterModel currentCharacter = results.get(position);
                ImageView imgCharacter = findViewById(R.id.iv_character);
                Glide.with(ChoosePersoActivity.this).load(currentCharacter.getImage()).into(imgCharacter);
                TextView sentence = findViewById(R.id.tv_sentence);
                sentence.setText(currentCharacter.getDesc());




            }
        });

    }
}

package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseLoverActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lover);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        final GridView loverGrid = findViewById(R.id.grid_lovers);
        final ArrayList<LoverModel> loverList = new ArrayList<>();
        final LoverGridAdapter adapter = new LoverGridAdapter(this,loverList);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loverList.clear();
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    LoverModel loverModel = userDataSnapshot.child("Profil").getValue(LoverModel.class);
                    loverList.add(new LoverModel(loverModel.getProfilPic(), loverModel.getPseudo()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        loverGrid.setAdapter(adapter);

        loverGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoverModel loverModel = (LoverModel)loverGrid.getItemAtPosition(position);
                Intent intent = new Intent(ChooseLoverActivity.this, SeeLoversActivity.class);
                intent.putExtra("urlAvatar", loverModel.getProfilPic().toString());
                intent.putExtra("username2", loverModel.getPseudo().toString());
                startActivity(intent);
            }
        });

        ImageView buttonDeco = findViewById(R.id.iv_deco);
        buttonDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(ChooseLoverActivity.this, LoginActivity.class));
                finish();
            }
        });

        ImageView imgProfile = findViewById(R.id.iv_profile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToProfile = new Intent(ChooseLoverActivity.this, ProfileActivity.class);
                startActivity(goToProfile);
            }
        });



        final Spinner spinnerGender = findViewById(R.id.spin_gender);
        //Utiliser un Adapter pour rentrer les données du spinner_array
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.spinner_gender, android.R.layout.simple_spinner_item);
        //Spécifier le layout à utiliser pour afficher les données
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Appliquer l'adapter au spinner
        spinnerGender.setAdapter(adapter2);

        final Spinner spinnerSpecies = findViewById(R.id.spin_species);
        //Utiliser un Adapter pour rentrer les données du spinner_array
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.spinner_species, android.R.layout.simple_spinner_item);
        //Spécifier le layout à utiliser pour afficher les données
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Appliquer l'adapter au spinner
        spinnerSpecies.setAdapter(adapter3);


        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = spinnerGender.getItemAtPosition(position).toString();
                if (selection.matches("Homme")) {
                    database.getReference("User").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            loverList.clear();
                            for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                                if (userDataSnapshot.child("Profil").child("genre").getValue(String.class).equals("masculin")) {
                                    LoverModel loverModel = userDataSnapshot.child("Profil").getValue(LoverModel.class);
                                    loverList.add(new LoverModel(loverModel.getProfilPic(), loverModel.getPseudo()));
                                }

                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    loverGrid.setAdapter(adapter);
                }
                if (selection.matches("Femme")) {
                    database.getReference("User").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            loverList.clear();
                            for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                                if (userDataSnapshot.child("Profil").child("genre").getValue(String.class).equals("feminin")) {
                                    LoverModel loverModel = userDataSnapshot.child("Profil").getValue(LoverModel.class);
                                    loverList.add(new LoverModel(loverModel.getProfilPic(), loverModel.getPseudo()));
                                }

                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    loverGrid.setAdapter(adapter);

                }
                if (selection.matches("Sélectionner un genre :")) {
                    database.getReference("User").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            loverList.clear();
                            for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                                LoverModel loverModel = userDataSnapshot.child("Profil").getValue(LoverModel.class);
                                loverList.add(new LoverModel(loverModel.getProfilPic(), loverModel.getPseudo()));
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    loverGrid.setAdapter(adapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







    }
}


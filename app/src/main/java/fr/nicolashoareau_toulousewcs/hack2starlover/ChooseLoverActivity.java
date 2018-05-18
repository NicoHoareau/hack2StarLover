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



        final Spinner spinnerGender = findViewById(R.id.spin_gender);
        //Utiliser un Adapter pour rentrer les données du spinner_array
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.spinner_gender, android.R.layout.simple_spinner_item);
        //Spécifier le layout à utiliser pour afficher les données
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Appliquer l'adapter au spinner
        spinnerGender.setAdapter(adapter2);






    }
}


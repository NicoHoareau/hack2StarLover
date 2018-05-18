package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TchatActivity extends AppCompatActivity {

    private String name;
    FirebaseDatabase mDatabase;
    private String mUid;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Planet");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchat);

        final ListView listMessage = findViewById(R.id.list_message);
        //final EditText etMessage = findViewById(R.id.et_message);
        //Button btnSend = findViewById(R.id.btn_send);

        final ArrayList<PlanetModel> planetModelArrayList = new ArrayList<>();
        final PlanetAdapter planetAdapter = new PlanetAdapter(this, planetModelArrayList);
        final ListView listPlanet = findViewById(R.id.list_message);
        listPlanet.setAdapter(planetAdapter);
        try {
            planetModelArrayList.add(new PlanetModel(R.drawable.tatooine_back, "Tatooine"));
            planetModelArrayList.add(new PlanetModel(R.drawable.naboo_back, "Naboo"));
            planetModelArrayList.add(new PlanetModel(R.drawable.alderaan_back, "Alderaan"));
            planetModelArrayList.add(new PlanetModel(R.drawable.kashyyyk_back, "Kashyyyk"));
            planetModelArrayList.add(new PlanetModel(R.drawable.coruscant_back, "Coruscant"));
            planetModelArrayList.add(new PlanetModel(R.drawable.kamino_back, "Kamino"));
            planetModelArrayList.add(new PlanetModel(R.drawable.jakku_back, "Jakku"));
            planetModelArrayList.add(new PlanetModel(R.drawable.dagobah_back, "Dagoba"));
        } catch (Exception e) {
        }

        //request_user_name();


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                planetAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //TODO : à mettre une fois un profil créé
        /*mDatabase = FirebaseDatabase.getInstance();
        mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference planetRef = mDatabase.getReference().child(mUid);
        planetRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String username = dataSnapshot.child("pseudo").getValue(String.class);
                listMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PlanetModel item = (PlanetModel) listPlanet.getItemAtPosition(position);
                        Intent intent = new Intent(TchatActivity.this, TchatRoomActivity.class);
                        String namePlanete = item.getNamePlanete();
                        intent.putExtra("room_name", namePlanete);
                        intent.putExtra("username", username);

                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/

        final String username = getIntent().getExtras().get("username").toString();


        listMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanetModel item = (PlanetModel) listPlanet.getItemAtPosition(position);
                Intent intent = new Intent(TchatActivity.this, TchatRoomActivity.class);
                String namePlanete = item.getNamePlanete();
                intent.putExtra("room_name",namePlanete);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name :");
        final EditText input_field = new EditText(this);
        builder.setView(input_field);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input_field.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                request_user_name();
            }
        });
        builder.show();

    }
}

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

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Planet");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchat);

        final ListView listMessage = findViewById(R.id.list_message);
        final EditText etMessage = findViewById(R.id.et_message);
        Button btnSend = findViewById(R.id.btn_send);

        final ArrayList<PlanetModel> planetModelArrayList = new ArrayList<>();
        final PlanetAdapter planetAdapter = new PlanetAdapter(this, planetModelArrayList);
        final ListView listPlanet = findViewById(R.id.list_message);
        listPlanet.setAdapter(planetAdapter);
        try {
            planetModelArrayList.add(new PlanetModel(R.drawable.ic_launcher_background, "Tatooine"));
            planetModelArrayList.add(new PlanetModel(R.drawable.ic_launcher_background, "Naboo"));
            planetModelArrayList.add(new PlanetModel(R.drawable.ic_launcher_background, "Alderaan"));
            planetModelArrayList.add(new PlanetModel(R.drawable.ic_launcher_background, "Kashyyyk"));
            planetModelArrayList.add(new PlanetModel(R.drawable.ic_launcher_background, "Kashyyyk"));
            planetModelArrayList.add(new PlanetModel(R.drawable.ic_launcher_background, "Kashyyyk"));
        } catch (Exception e) {
        }


        /*arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_grid_planet, arrayList);
        listMessage.setAdapter(arrayAdapter);*/

        request_user_name();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(etMessage.getText().toString(),"");
                root.updateChildren(map);
            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                planetAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanetModel item = (PlanetModel) listPlanet.getItemAtPosition(position);
                Intent intent = new Intent(TchatActivity.this, TchatRoomActivity.class);
                String namePlanete = item.getNamePlanete();
                intent.putExtra("room_name",namePlanete);
                intent.putExtra("username",name);
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

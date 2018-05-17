package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChoosePlanetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_planet);

        GridView planetGridView = findViewById(R.id.gridview_planet);
        final ArrayList<PlanetModel> planetList = new ArrayList<>();
        final PlanetAdapter adapter = new PlanetAdapter(this,planetList);
        planetList.add(new PlanetModel(R.drawable.ic_launcher_background, "tatooine"));
        planetList.add(new PlanetModel(R.drawable.ic_launcher_background, "naboo"));
        planetList.add(new PlanetModel(R.drawable.ic_launcher_background, "alderaan"));
        planetList.add(new PlanetModel(R.drawable.ic_launcher_background, "kashyyyk"));
        planetList.add(new PlanetModel(R.drawable.ic_launcher_background, "kashyyyk"));
        planetList.add(new PlanetModel(R.drawable.ic_launcher_background, "kashyyyk"));
        planetGridView.setAdapter(adapter);

        planetGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanetModel planetModel = new PlanetModel();
                if (planetModel.getNamePlanete().equals("tatooine")){

                    Intent goToTchatRoom = new Intent(ChoosePlanetActivity.this, TchatRoomActivity.class);
                    goToTchatRoom.putExtra("name_planet","tatooine");
                    startActivity(goToTchatRoom);
                }

            }
        });



    }
}

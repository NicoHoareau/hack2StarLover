package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

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

    }
}

package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ChooseLoverActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lover);

        GridView loverGrid = findViewById(R.id.grid_lovers);
        final ArrayList<LoverModel> loverList = new ArrayList<>();
        final LoverGridAdapter adapter = new LoverGridAdapter(this,loverList);
        loverList.add(new LoverModel(R.drawable.ic_launcher_background, "perso1"));
        loverList.add(new LoverModel(R.drawable.ic_launcher_background, "perso2"));
        loverList.add(new LoverModel(R.drawable.ic_launcher_background, "perso3"));
        loverList.add(new LoverModel(R.drawable.ic_launcher_background, "perso4"));
        loverList.add(new LoverModel(R.drawable.ic_launcher_background, "perso5"));
        loverList.add(new LoverModel(R.drawable.ic_launcher_background, "perso6"));
        loverGrid.setAdapter(adapter);

        loverGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChooseLoverActivity.this, SeeLoversActivity.class);
                startActivity(intent);
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





    }
}


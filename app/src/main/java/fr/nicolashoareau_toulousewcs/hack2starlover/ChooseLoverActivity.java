package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

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

        ImageButton buttonDeco = findViewById(R.id.iv_guide);
        buttonDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(ChooseLoverActivity.this, LoginActivity.class);
                startActivity(log);
            }
        });

                //iv_guide

        ImageButton imageView = findViewById(R.id.iv_backdialog_guide);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoverActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });




    }
}


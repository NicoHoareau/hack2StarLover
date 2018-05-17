package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseSideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_side);


        ImageView lightSide = findViewById(R.id.iv_lightside);
        ImageView darkSide = findViewById(R.id.iv_darkside);

        lightSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lightIntent = new Intent(ChooseSideActivity.this, NewProfilActivity.class);
                String light = "Light";
                lightIntent.putExtra("Extra", light);
                startActivity(lightIntent);
            }
        });

        darkSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent darkIntent = new Intent(ChooseSideActivity.this, NewProfilActivity.class);
                String dark = "Dark";
                darkIntent.putExtra("Extra", dark);
                startActivity(darkIntent);
            }
        });
    }
}

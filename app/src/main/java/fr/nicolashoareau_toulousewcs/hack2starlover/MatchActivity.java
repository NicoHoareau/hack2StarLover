package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Button btnMeet = findViewById(R.id.btn_meet_lover);
        btnMeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSelectPlanet = new Intent(MatchActivity.this, TchatActivity.class);
                startActivity(goSelectPlanet);
            }
        });
        ImageView imgAbandon = findViewById(R.id.iv_abandon2);
        imgAbandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToSelectLovers = new Intent(MatchActivity.this, SeeLoversActivity.class);
                startActivity(returnToSelectLovers);
            }
        });

    }
}

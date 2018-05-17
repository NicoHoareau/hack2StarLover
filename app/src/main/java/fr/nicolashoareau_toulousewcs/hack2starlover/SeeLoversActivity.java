package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SeeLoversActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_lovers);

        ImageView imgMatchLover = findViewById(R.id.iv_match_lover);
        imgMatchLover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMatch = new Intent(SeeLoversActivity.this, MatchActivity.class);
                startActivity(goToMatch);
            }
        });
        ImageView imgAbandon = findViewById(R.id.iv_abandon);
        imgAbandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChooseLover = new Intent(SeeLoversActivity.this, ChooseLoverActivity.class);
                startActivity(goToChooseLover);
            }
        });
        ImageView imgProfile = findViewById(R.id.iv_profile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToProfile = new Intent(SeeLoversActivity.this, ProfileActivity.class);
                startActivity(goToProfile);
            }
        });
    }
}

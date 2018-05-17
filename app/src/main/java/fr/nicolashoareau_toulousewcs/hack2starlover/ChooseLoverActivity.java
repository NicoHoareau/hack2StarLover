package fr.nicolashoareau_toulousewcs.hack2starlover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class ChooseLoverActivity extends AppCompatActivity {
    private GridView mLoverGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lover);

        mLoverGrid = findViewById(R.id.grid_lovers);


    }
}


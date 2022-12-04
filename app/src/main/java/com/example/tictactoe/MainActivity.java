package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startMatchBtn = findViewById(R.id.start_match_btn);

        startMatchBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, Game.class);
            startActivity(intent);
        });
    }
}
package com.example.design.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Design.R;
import com.example.design.View.Utils;

public class MainScreen extends AppCompatActivity {
    Button play, settings, statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        play = findViewById(R.id.btn_play);
        //settings = findViewById(R.id.settings);
        statistics = findViewById(R.id.btn_statistics);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                startActivity(new Intent(this, Registration.class));
                break;

            case R.id.btn_rules:
                startActivity(new Intent(this, Rules.class));
                break;

            case R.id.btn_statistics:
                startActivity(new Intent(this, Statistics.class));
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Utils.handleMenu(item, this))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }




}


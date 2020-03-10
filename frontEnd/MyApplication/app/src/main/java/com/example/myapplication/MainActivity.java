package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onRun();
    }

    private void onRun() {
        this.getSupportFragmentManager().beginTransaction().add(R.id.frame1,new firstFragment())
        .commit();
    }
}

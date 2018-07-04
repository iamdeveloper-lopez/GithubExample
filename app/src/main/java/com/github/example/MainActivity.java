package com.github.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.example.mvp.MvpActivity;
import com.github.example.mvvm.MvvmActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonMvp).setOnClickListener(view -> startActivity(new Intent(this, MvpActivity.class)));

        findViewById(R.id.buttonMvvm).setOnClickListener(view -> startActivity(new Intent(this, MvvmActivity.class)));

    }
}

package com.example.movementor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View cadastreSe = findViewById(R.id.txt_cadastreSe);
        Intent it = new Intent(this, CadastroActivity.class);

        cadastreSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                startActivity(it);
            }
        });
    }
}
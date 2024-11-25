package com.example.movementor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Recuperando os dados do Intent
        Intent intent = getIntent();
        String descricao = intent.getStringExtra("descricao");
        String avaliacao = intent.getStringExtra("avaliacao");
        String nomeUsuario = intent.getStringExtra("nomeUsuario");
        String categoria = intent.getStringExtra("categoria");

        TextView txtDescricao = findViewById(R.id.descricao_user);
        TextView txtCategoria = findViewById(R.id.categoria_servico);
        TextView txtAvailiacao = findViewById(R.id.avaliacao_user);
        TextView txtNome = findViewById(R.id.nome_prestador_servico);
        txtDescricao.setText(descricao);
        txtCategoria.setText(categoria);
        txtNome.setText(nomeUsuario);
        txtAvailiacao.setText(avaliacao);

        txtDescricao.setText(descricao);
        ImageView imageView = findViewById(R.id.imagem);

        if(categoria.equals("Mudança")){
            imageView.setImageResource(R.drawable.mudanca);
        } if(categoria.equals("Elétrica")){
            imageView.setImageResource(R.drawable.eletrica);
        } if(categoria.equals("Pintura")){
            imageView.setImageResource(R.drawable.pintura);
        }


    }
}
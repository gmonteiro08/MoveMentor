package com.example.movementor;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ServicoAdapter extends ArrayAdapter<Servico> {

    public ServicoAdapter(@NonNull Context context, @NonNull List<Servico> servicos) {
        super(context, 0, servicos); // 0 significa que o ArrayAdapter não usará um layout padrão
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Se a view já foi criada, reutilize
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lista_servicos, parent, false);
        }

        // Obtenha o item que está sendo exibido
        Servico servico = getItem(position);

        // Preencher os dados na view
        TextView nomeTextView = convertView.findViewById(R.id.textView4);
        nomeTextView.setText(servico.getNome());

        TextView descricaoTextView = convertView.findViewById(R.id.textView10);
        descricaoTextView.setText(servico.getDescricao());

        ImageView imagemImageView = convertView.findViewById(R.id.imageView8);
        imagemImageView.setImageResource(servico.getImagemResId());

        // Retorne a view configurada
        return convertView;
    }
}

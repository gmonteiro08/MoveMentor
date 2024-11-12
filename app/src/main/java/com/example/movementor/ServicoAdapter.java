package com.example.movementor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder> {
    private ArrayList<Servico> servicoList;

    public ServicoAdapter(ArrayList<Servico> servicoList) {
        this.servicoList = servicoList;
    }

    @NonNull
    @Override
    public ServicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ServicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoViewHolder holder, int position) {
        Servico servico = servicoList.get(position);
        holder.descricao.setText(servico.getDescricao());
        holder.avaliacao.setText(String.valueOf(servico.getAvaliacao()));
        holder.nomeUsuario.setText(servico.getNomeUsuario());
    }

    @Override
    public int getItemCount() {
        return servicoList.size();
    }

    public static class ServicoViewHolder extends RecyclerView.ViewHolder {
        TextView descricao, avaliacao, nomeUsuario;

        public ServicoViewHolder(View itemView) {
            super(itemView);
            descricao = itemView.findViewById(R.id.textView_descricao);
            avaliacao = itemView.findViewById(R.id.textView19);
            nomeUsuario = itemView.findViewById(R.id.textView_nomeUsuario);
        }
    }
}



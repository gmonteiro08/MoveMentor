package com.example.movementor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder> {
    private ArrayList<Servico> servicoList;
    private ArrayList<Servico> servicoListFull;
    private OnItemClickListener listener;

    public ServicoAdapter(ArrayList<Servico> servicoList) {
        this.servicoList = servicoList;
    }

    @NonNull
    @Override
    public ServicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ServicoViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoViewHolder holder, int position) {
        Servico servico = servicoList.get(position);
        holder.descricao.setText(servico.getDescricao());
        holder.avaliacao.setText(String.valueOf(servico.getAvaliacao()));
        holder.nomeUsuario.setText(servico.getNomeUsuario());
        if(servico.getCategoria().equals("Mudança")){
            holder.imageView.setImageResource(R.drawable.truck);
        }
        if(servico.getCategoria().equals("Encanamento")){
            holder.imageView.setImageResource(R.drawable.plumber);
        }
        if(servico.getCategoria().equals("Elétrica")){
            holder.imageView.setImageResource(R.drawable.quick_mode_on);
        }
        if(servico.getCategoria().equals("Diarista")){
            holder.imageView.setImageResource(R.drawable.housekeeper);
        }
        if(servico.getCategoria().equals("Pintura")){
            holder.imageView.setImageResource(R.drawable.paint_roller);
        }
    }

    @Override
    public int getItemCount() {
        return servicoList.size();
    }

    public void updateList(ArrayList<Servico> newServicoList) {
        servicoList = newServicoList;
        notifyDataSetChanged();  // Notifica o RecyclerView para atualizar a UI
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }


    public static class ServicoViewHolder extends RecyclerView.ViewHolder {
        TextView descricao, avaliacao, nomeUsuario;
        ImageView imageView;

        public ServicoViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            descricao = itemView.findViewById(R.id.textView_descricao);
            avaliacao = itemView.findViewById(R.id.textView19);
            nomeUsuario = itemView.findViewById(R.id.textView_nomeUsuario);
            imageView = itemView.findViewById(R.id.imageView_categoria);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    private int getImageResourceForCategory(String categoria) {
        switch (categoria) {
            case "Mudança": return R.drawable.truck;
            case "Encanamento": return R.drawable.plumber;
            case "Elétrica": return R.drawable.quick_mode_on;
            case "Diarista": return R.drawable.housekeeper;
            case "Pintura": return R.drawable.paint_roller;
            default: return R.drawable.truck; // Um ícone padrão caso a categoria não seja encontrada
        }
    }

}
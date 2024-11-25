package com.example.movementor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movementor.Perfil;
import com.example.movementor.R;
import com.example.movementor.Servico;
import com.example.movementor.ServicoAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PesquisarFragment extends Fragment {
    private RecyclerView recyclerView;
    private ServicoAdapter servicoAdapter;
    private ArrayList<Servico> servicoList = new ArrayList<>();
    private FirebaseFirestore db;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesquisar, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));  // Use requireContext() para evitar null
        servicoAdapter = new ServicoAdapter(servicoList);
        recyclerView.setAdapter(servicoAdapter);

        db = FirebaseFirestore.getInstance();
        loadDataFromFirestore();

        for(Servico s : servicoList){
            Log.i("LISTA", "onCreateView: " + s.toString());
        }

        searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    performSearch(query);  // Realiza a pesquisa
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    loadDataFromFirestore();
                }
                return false;
            }
        });


      servicoAdapter.setOnItemClickListener(new ServicoAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(int position) {
              String descricao = servicoList.get(position).getDescricao();
              String nomeUser = servicoList.get(position).getNomeUsuario();
              String categoria = servicoList.get(position).getCategoria();
              String avaliacao = servicoList.get(position).getAvaliacao();
              Intent it = new Intent(getContext(), Perfil.class);
              it.putExtra("descricao", descricao);
              it.putExtra("nomeUsuario", nomeUser);
              it.putExtra("categoria", categoria);
              it.putExtra("avaliacao", avaliacao);
              startActivity(it);
          }
      });



        return view;
    }

    private void loadDataFromFirestore() {
        db.collection("Servico")  // Substitua "servicos" pelo nome da sua coleção
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        servicoList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Servico servico = document.toObject(Servico.class);
                            String idUsuario = String.valueOf(servico.getId());
                            loadUserName(idUsuario, servico);
                        }
                    } else {
                        // Log ou tratamento de erro
                    }
                });
    }

    private void loadUserName(String idUsuario, Servico servico) {
        db.collection("Usuarios")  // Substitua "usuarios" pelo nome da sua coleção
                .document(idUsuario)  // Id do usuário
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Supondo que o nome do usuário esteja armazenado em "nome"
                            String nomeUsuario = document.getString("nome");
                            servico.setNomeUsuario(nomeUsuario);  // Defina o nome do usuário no serviço
                            servicoList.add(servico);
                            servicoAdapter.notifyDataSetChanged();
                        }
                    } else {
                        // Log ou tratamento de erro
                    }
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    public void performSearch(String query) {


        ArrayList<Servico> filteredList = new ArrayList<>();

        for (Servico servico : servicoList) {
            // Verifica se qualquer palavra-chave no array de palavrasChaves contém a consulta
            if (servico.getCategoria().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(servico);
                break;  // Encerra o loop interno se uma correspondência for encontrada
            }
        }

        // Atualiza o RecyclerView com os resultados filtrados
        servicoAdapter.updateList(filteredList);
        servicoList = filteredList;
    }



}

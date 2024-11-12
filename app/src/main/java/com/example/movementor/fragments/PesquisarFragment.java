package com.example.movementor.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movementor.R;
import com.example.movementor.Servico;
import com.example.movementor.ServicoAdapter;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class PesquisarFragment extends Fragment {
    private RecyclerView recyclerView;
    private ServicoAdapter servicoAdapter;
    private ArrayList<Servico> servicoList = new ArrayList<>();
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
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

        return view;
    }

    private void loadDataFromFirestore() {
        db.collection("servicos")  // Substitua "servicos" pelo nome da sua coleção
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        servicoList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Servico servico = document.toObject(Servico.class);
                            // Obter o nome do usuário com base no id_usuário
                            String idUsuario = servico.getId_usuario();
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
}

package com.example.movementor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movementor.R;
import com.example.movementor.Servico;
import com.example.movementor.ServicoAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InicialFragment extends Fragment {

    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicial, container, false);
        listView = view.findViewById(R.id.listView);

        // Criar dados para a lista
        List<Servico> servicos = new ArrayList<>();
        servicos.add(new Servico("Jorges Fagundes", "Encanador profissional com expertise em serviços hidráulicos para residências e empresas", R.drawable.imagem1));
        servicos.add(new Servico("Ana Souza", "Especialista em pintura e reparos de paredes", R.drawable.pintor));
        servicos.add(new Servico("Carlos Oliveira", "Eletricista qualificado para serviços residenciais e comerciais", R.drawable.eletricista));
        servicos.add(new Servico("Fernanda Lima", "Pintora especializada em restauração e pintura decorativa", R.drawable.pintor));
        servicos.add(new Servico("Paulo Costa", "Encanador experiente em consertos rápidos e eficientes", R.drawable.imagem1));
        servicos.add(new Servico("Juliana Santos", "Eletricista qualificada para instalações e manutenção elétrica", R.drawable.eletricista));

        ServicoAdapter adapter = new ServicoAdapter(getContext(), servicos);
        listView.setAdapter(adapter);
        return view;
    }
}

package com.example.movementor;

import java.util.ArrayList;
import java.util.Date;

public class Servico {
    private int id;
    private String id_usuário;
    private String categoria;
    private String descricao;
    private String endereco;
    private Date data;
    private ArrayList<String> palavrasChave;
    private double avaliacao;
    private String nomeUsuario;


    public Servico(int id, String id_usuário, String categoria, String descricao, String endereco, Date data, ArrayList<String> palavrasChave, double avaliacao) {
        this.id = id;
        this.id_usuário = id_usuário;
        this.categoria = categoria;
        this.descricao = descricao;
        this.endereco = endereco;
        this.data = data;
        this.palavrasChave = palavrasChave;
        this.avaliacao = avaliacao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuário;
    }

    public void setId_usuário(String id_usuário) {
        this.id_usuário = id_usuário;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public  ArrayList<String> getPalavrasChave(){
        return palavrasChave;
    }

    public void setPalavrasChave(ArrayList<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    public  double getAvaliacao(){
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}

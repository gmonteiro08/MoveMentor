package com.example.movementor;

public class Servico {
    private String nome;
    private String descricao;
    private int imagemResId; // Para a imagem

    public Servico(String nome, String descricao, int imagemResId) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagemResId = imagemResId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getImagemResId() {
        return imagemResId;
    }
}

package br.softhouse.model;

import java.time.LocalDateTime;

public class Eleitor {

    private String nome;
    private int tituloEleitor;
    private int secao;
    private int zona;
    private LocalDateTime dataHoraVotacao;

    public Eleitor(String nome, int tituloEleitor, int zona, int secao) {
        this.nome = nome;
        this.tituloEleitor = tituloEleitor;
        this.zona = zona;
        this.secao = secao;
    }

    public boolean isJaVotou() {
        return dataHoraVotacao != null;
    }

    // Getters (Setters omitidos - propriedades setadas via construtor somente)


    public String getNome() {
        return nome;
    }

    public int getTituloEleitor() {
        return tituloEleitor;
    }

    public int getSecao() {
        return secao;
    }

    public int getZona() {
        return zona;
    }

    public LocalDateTime getDataHoraVotacao() {
        return dataHoraVotacao;
    }

}

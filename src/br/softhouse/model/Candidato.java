package br.softhouse.model;

public class Candidato {

    public static final int EM_BRANCO = 0;
    public static final int NULO = 99999;
    private static final String STR_NULO = "Nulo";
    private static final String STR_EM_BRANCO = "Em Branco";

    private int inscricao;
    private String nome;
    private int votos = 0;
    private boolean doSistema;

    public Candidato(String nome, int inscricao) {
        this.inscricao = inscricao;
        this.nome = nome;
    }

    public Candidato(int inscricao) {
        this.nome = inscricao == EM_BRANCO ? STR_EM_BRANCO : STR_NULO;
        this.inscricao = inscricao;
        this.doSistema = true;
    }

    public void registrarVoto() {
        votos++;
    }

    // Getters (Setters omitidos - propriedades setadas via construtor somente)

    public String getNome() {
        return nome;
    }

    public int getInscricao() {
        return inscricao;
    }

    public int getVotos() {
        return votos;
    }

    public boolean isDoSistema() {
        return doSistema;
    }

}

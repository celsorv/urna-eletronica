package br.softhouse.model;

import br.softhouse.infraestrutura.TipoEvento;

public class Evento {

    private TipoEvento tipo;
    private Candidato candidato;

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}

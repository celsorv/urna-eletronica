package br.softhouse.infraestrutura;

import br.softhouse.model.Candidato;
import br.softhouse.model.Evento;

import java.util.ArrayList;

public class Urna  {

    private static Urna uniqueInstance;

    private ArrayList<Feedback> feedbacks = new ArrayList<>();

    private DataBase DB = DataBase.getInstance();
    private Evento evento = new Evento();
    private boolean bloqueada = true;
    private int secao = 0;
    private int zona = 0;

    private Urna() {
    }

    public static synchronized Urna getInstance() {

        if (uniqueInstance == null) {
            uniqueInstance = new Urna();
        }

        return uniqueInstance;

    }

    public void registrarVoto(int inscricao) {

        Candidato candidato = DB.getCandidato(inscricao);
        if (candidato == null) candidato = DB.getCandidato(Candidato.NULO);

        candidato.registrarVoto();
        notificarEvento(TipoEvento.VOTO, candidato);
        bloquear();

    }

    public void bloquear() {
        notificarEvento(TipoEvento.BLOQUEIO);
        bloqueada = true;
    }

    public void desbloquear() {
        notificarEvento(TipoEvento.DESBLOQUEIO);
        bloqueada = false;
    }

    public void implantarFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public void listarCandidatos() {
        System.out.println("\nCandidatos:");
        System.out.println("-".repeat(25));
        DB.getCandidatos().stream().filter(e -> !e.isDoSistema())
                .forEach(e -> System.out.println(String.format("%-15.15s Nr. %5d", e.getNome(), e.getInscricao())));
    }

    public void listarEleitores() {
        System.out.println("\nEleitores:");
        System.out.println("-".repeat(25));
        DB.getEleitores().stream()
                .filter(e -> e.getZona() == zona && e.getSecao() == secao)
                .forEach(e -> System.out.println(String.format("%-15.15s Nr. %5d", e.getNome(), e.getTituloEleitor())));
    }

    public void listarApuracao() {
        System.out.println("\nApuração:");
        System.out.println("-".repeat(30));
        DB.getCandidatos().stream()
                .forEach(e -> System.out.println(String.format("%-15.15s  %5d voto(s)", e.getNome(), e.getVotos())));
    }

    private void notificarEvento(TipoEvento tipo) {
        notificarEvento(tipo, null);
    }

    private void notificarEvento(TipoEvento tipo, Candidato candidato) {
        evento.setTipo(tipo);
        evento.setCandidato(candidato);
        feedbacks.forEach(e -> e.imprimir(evento));
    }

    // Getters and Setters

    public int getSecao() {
        return secao;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

}

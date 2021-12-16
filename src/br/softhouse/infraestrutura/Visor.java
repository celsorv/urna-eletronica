package br.softhouse.infraestrutura;

import br.softhouse.model.Evento;

public class Visor implements Feedback {

    @Override
    public void imprimir(Evento evento) {

        switch (evento.getTipo()) {
            case DESBLOQUEIO:
                send("*** urna liberada");
                break;

            case BLOQUEIO:
                send("*** urna bloqueada");
                break;

            case VOTO:
                send(String.format("         VOTO EM %s !!!", evento.getCandidato().getNome().toUpperCase()));

        }

    }

    private void send(String mensagem) {
        System.out.print("\u001B[36m");
        System.out.println("::: DISPLAY :::  " + mensagem);
        System.out.print("\u001B[39m");
    }

}

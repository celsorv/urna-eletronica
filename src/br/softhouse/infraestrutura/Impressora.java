package br.softhouse.infraestrutura;

import br.softhouse.model.Evento;

public class Impressora implements Feedback {

    @Override
    public void imprimir(Evento evento) {
        if (TipoEvento.VOTO.equals(evento.getTipo()))
            send(String.format("         VOTO EM %s !!!", evento.getCandidato().getNome().toUpperCase()));
    }

    private void send(String mensagem) {
        System.out.print("\u001B[32m");
        System.out.println("### PRINTER ###  " + mensagem);
        System.out.print("\u001B[39m");
    }

}

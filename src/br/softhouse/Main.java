package br.softhouse;

import br.softhouse.infraestrutura.Impressora;
import br.softhouse.infraestrutura.Urna;
import br.softhouse.infraestrutura.Visor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n");

        Urna urna = Urna.getInstance();
        urna.implantarFeedback(new Visor());
        urna.implantarFeedback(new Impressora());    // voto impresso

        Scanner sc = new Scanner(System.in);

        while (true) {

            urna.listarCandidatos();

            String opcao = menu(sc);
            if ("X".equals(opcao)) break;

            int inscricao = obterNumeros(sc, "\nInscrição do candidato?            [0 = voto em branco]");

            urna.desbloquear();
            urna.registrarVoto(inscricao);

        }

        sc.close();
        urna.listarApuracao();
        System.out.println("\n");

    }

    private static void alerta(String mensagem) {
        System.out.println("\u001B[33m" + mensagem + "\u001B[39m");
    }

    private static String menu(Scanner sc) {

        System.out.println("\n" + "-".repeat(55));
        System.out.println("Tecle: L = Liberar urna   X = Finalizar");

        String opcao = "";

        while (true) {

            System.out.print("?: ");
            opcao = sc.next().toUpperCase();
            if ("LX".contains(opcao)) break;

            alerta("::: opção inválida!\n");
        }

        return opcao;

    }

    private static int obterNumeros(Scanner sc, String msg) {

        int inscricao;

        //System.out.println("\nInscrição do candidato?            [0 = voto em branco]");
        System.out.println(msg);

        while (true) {

            try {

                System.out.print("?: ");
                inscricao = sc.nextInt();
                System.out.println(); // salta linha

                return inscricao;

            } catch (InputMismatchException e) {
            }

            alerta("::: somente números!\n");
            sc.nextLine();

        }

    }

}

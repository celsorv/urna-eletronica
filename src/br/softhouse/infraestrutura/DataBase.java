package br.softhouse.infraestrutura;

import br.softhouse.model.Candidato;
import br.softhouse.model.Eleitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    // formato arquivo: nome, numeroTitulo, zona, secao, inscricaoCandidato

    private static final byte I_NOME = 0;
    private static final byte I_TITULO = 1;
    private static final byte I_ZONA = 2;
    private static final byte I_SECAO = 3;
    private static final byte I_IS_CANDIDATO = 4;
    private static final byte I_NR_CANDIDATO = 5;

    private Map<Integer, Eleitor> eleitores;
    private Map<Integer, Candidato> candidatos;

    private static DataBase uniqueInstance;

    private DataBase() {
    }

    public static synchronized DataBase getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DataBase();
            uniqueInstance.carregarCandidatos();
        }
        return uniqueInstance;
    }

    public Candidato getCandidato(int inscricao) {
        return candidatos.get(inscricao);
    }

    public Eleitor getEleitor(int tituloEleitor) {
        return eleitores.get(tituloEleitor);
    }

    public Collection<Candidato> getCandidatos() {
        return candidatos.values();
    }

    public Collection<Eleitor> getEleitores() {
        return eleitores.values();
    }

    private void carregarCandidatos() {

        eleitores = new HashMap<>();
        candidatos = new HashMap<>();

        candidatos.put(Candidato.EM_BRANCO, new Candidato(Candidato.EM_BRANCO));
        candidatos.put(Candidato.NULO, new Candidato(Candidato.NULO));


        try (BufferedReader br = Files.newBufferedReader(
                Paths.get(ClassLoader.getSystemResource("cidadaos.csv").toURI())
        )) {

            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.charAt(0) != '#') {
                    incluirDados(linha.split(","));
                }
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private String[] incluirDados(String[] col) {

        int inscricaoTitulo = Integer.parseInt(col[I_TITULO]);

        eleitores.put(
                inscricaoTitulo,
                new Eleitor(
                        col[I_NOME],
                        inscricaoTitulo,
                        Integer.parseInt(col[I_ZONA]),
                        Integer.parseInt(col[I_SECAO])
                )
        );

        if ("Tt".indexOf(col[I_IS_CANDIDATO]) == -1) {   // não é candidato
            return col;
        }

        try {
            int inscricaoCandidato = Integer.parseInt(col[I_NR_CANDIDATO]);
            candidatos.put(inscricaoCandidato, new Candidato(col[I_NOME], inscricaoCandidato));

        } catch (NumberFormatException e) {     // ignora inscrição não numérica

        }

        return col;

    }

}

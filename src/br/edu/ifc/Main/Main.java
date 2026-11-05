package br.edu.ifc.Main;

import br.edu.ifc.experimento.ExecutadorExperimento;
import br.edu.ifc.experimento.TesteEstruturas;

public class Main {

    public static void main(String[] args) {
        TesteEstruturas.executar();

        ExecutadorExperimento executador =
                new ExecutadorExperimento();

        executador.executar();

        System.out.println();
        System.out.println("Experimentos concluídos.");
    }
}

package br.edu.ifc.Main;

import br.edu.ifc.experimento.*;

import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Verificando as estruturas...");
        TesteEstruturas.executar();

        System.out.println();
        System.out.println("Iniciando os experimentos...");

        ExecutadorExperimento executador =
                new ExecutadorExperimento();

        List<ResultadoBruto> resultados =
                executador.executar();

        GravadorCSV.gravar(
                resultados,
                Path.of("resultados")
        );

        System.out.println();
        System.out.println("Experimentos concluídos.");
        System.out.println(
                "Arquivos criados na pasta resultados."
        );
    }
}
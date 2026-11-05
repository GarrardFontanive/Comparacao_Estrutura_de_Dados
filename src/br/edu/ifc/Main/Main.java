package br.edu.ifc.Main;

import br.edu.ifc.experimento.ExecutadorExperimento;
import br.edu.ifc.experimento.GravadorCSV;
import br.edu.ifc.experimento.ResultadoBruto;
import br.edu.ifc.experimento.TesteEstruturas;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args)
            throws Exception {

        TesteEstruturas.executar();

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
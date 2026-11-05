package br.edu.ifc.experimento;

import br.edu.ifc.estruturas.EstruturaDados;
import br.edu.ifc.estruturas.FabricaEstruturas;
import br.edu.ifc.estruturas.TipoEstrutura;

public class TesteEstruturas {

    private TesteEstruturas() {
    }

    public static void executar() {
        for (TipoEstrutura tipo
                : TipoEstrutura.values()) {

            EstruturaDados estrutura =
                    FabricaEstruturas.criar(tipo, 10);

            exigir(estrutura.inserir(8), tipo);
            exigir(estrutura.inserir(2), tipo);
            exigir(estrutura.inserir(10), tipo);
            exigir(estrutura.inserir(4), tipo);
            exigir(estrutura.inserir(6), tipo);

            exigir(!estrutura.inserir(4), tipo);
            exigir(estrutura.tam() == 5, tipo);
            exigir(estrutura.buscar(6), tipo);
            exigir(!estrutura.buscar(7), tipo);

            exigir(
                    estrutura.contarIntervalo(4, 8) == 3,
                    tipo
            );

            exigir(estrutura.excluir(6), tipo);
            exigir(!estrutura.excluir(6), tipo);
            exigir(estrutura.tam() == 4, tipo);
            exigir(!estrutura.buscar(6), tipo);

            System.out.println(
                    tipo.getNome() + ": teste aprovado"
            );
        }
    }

    private static void exigir(
            boolean condicao,
            TipoEstrutura tipo
    ) {
        if (!condicao) {
            throw new IllegalStateException(
                    "Falha no teste da estrutura: "
                            + tipo.getNome()
            );
        }
    }
}
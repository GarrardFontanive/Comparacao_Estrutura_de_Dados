package br.edu.ifc.estruturas;

public class FabricaEstruturas {

    private FabricaEstruturas() {
    }

    public static EstruturaDados criar(
            TipoEstrutura tipo,
            int quantidadeInicial
    ) {
        return switch (tipo) {
            case ARRAY_LINEAR ->
                    new ArrayLinear(
                            quantidadeInicial
                                    + quantidadeInicial / 10
                    );

            case ARRAY_ORDENADO ->
                    new ArrayOrdenado(
                            quantidadeInicial
                                    + quantidadeInicial / 10
                    );

            case LISTA_DUPLAMENTE_ENCADEADA ->
                    new ListaDuplamenteEncadeada();

            case TABELA_HASH ->
                    new TabelaHash(quantidadeInicial);

            case ARVORE_BINARIA_BUSCA ->
                    new ArvoreBinariaDeBusca();

            case ARVORE_AVL ->
                    new ArvoreAVL();

            case ARVORE_RUBRO_NEGRA ->
                    new ArvoreRubroNegra();
        };
    }
}
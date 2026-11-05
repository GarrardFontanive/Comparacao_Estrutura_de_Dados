package br.edu.ifc.estruturas;

public class FabricaEstruturas {

    private FabricaEstruturas() {
    }

    public static EstruturaDados criar(
            TipoEstrutura tipo,
            int quantidadeInicial
    ) {
        return switch (tipo) {
            case ARRAY_ORDENADO ->
                    new ArrayOrdenado(
                            quantidadeInicial
                                    + quantidadeInicial / 10
                    );

            case LISTA_DUPLAMENTE_ENCADEADA ->
                    new ListaDuplamenteEncadeada();

            case TABELA_HASH ->
                    new TabelaHash(quantidadeInicial);

            case ARVORE_AVL ->
                    new ArvoreAVL();
        };
    }
}
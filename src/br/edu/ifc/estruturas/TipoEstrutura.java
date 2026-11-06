package br.edu.ifc.estruturas;

public enum TipoEstrutura {
    ARRAY_LINEAR("Array linear"),
    ARRAY_ORDENADO("Array ordenado"),
    LISTA_DUPLAMENTE_ENCADEADA("Lista duplamente encadeada"),
    TABELA_HASH("Tabela hash"),
    ARVORE_BINARIA_BUSCA("Árvore binária de busca"),
    ARVORE_AVL("Árvore AVL"),
    ARVORE_RUBRO_NEGRA("Árvore rubro-negra");

    private final String nome;

    TipoEstrutura(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
package br.edu.ifc.estruturas;

public enum TipoEstrutura {
    ARRAY_ORDENADO("Array ordenado"),
    LISTA_DUPLAMENTE_ENCADEADA("Lista duplamente encadeada"),
    TABELA_HASH("Tabela hash"),
    ARVORE_AVL("Árvore AVL");

    private final String nome;

    TipoEstrutura(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
package br.edu.ifc.experimento;

public enum OrdemEntrada {
    CRESCENTE("crescente"),
    DECRESCENTE("decrescente"),
    ALEATORIA("aleatoria");

    private final String nome;

    OrdemEntrada(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
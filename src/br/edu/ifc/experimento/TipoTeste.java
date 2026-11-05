package br.edu.ifc.experimento;

public enum TipoTeste {
    CONSTRUCAO("construcao"),
    BUSCA_PRESENTE("busca_presente"),
    BUSCA_AUSENTE("busca_ausente"),
    INSERCAO("insercao"),
    REMOCAO("remocao"),
    CONSULTA_INTERVALO("consulta_intervalo");

    private final String nome;

    TipoTeste(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
package br.edu.ifc.experimento;

public record ResultadoBruto(
        int tamanho,
        String ordem,
        String estrutura,
        String teste,
        int repeticao,
        int quantidadeOperacoes,
        long tempoTotalNs,
        long conferencia
) {
}
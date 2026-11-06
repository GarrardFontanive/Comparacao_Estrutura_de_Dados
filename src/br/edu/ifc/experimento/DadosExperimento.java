package br.edu.ifc.experimento;

import java.util.Random;

public class DadosExperimento {

    private static final int SEMENTE_ORDEM = 42;
    private static final int SEMENTE_BUSCA_PRESENTE = 43;
    private static final int SEMENTE_BUSCA_AUSENTE = 44;
    private static final int SEMENTE_INSERCAO = 45;
    private static final int SEMENTE_REMOCAO = 46;
    private static final int SEMENTE_INTERVALO = 47;

    private static final int QUANTIDADE_BUSCAS = 2000;
    private static final int QUANTIDADE_INTERVALOS = 200;

    private final int tamanho;
    private final int[] valoresIniciais;
    private final int[] buscasPresentes;
    private final int[] buscasAusentes;
    private final int[] insercoes;
    private final int[] remocoes;
    private final int[] intervalosMinimos;

    public DadosExperimento(
            int tamanho,
            OrdemEntrada ordem
    ) {
        this.tamanho = tamanho;

        valoresIniciais =
                gerarValoresIniciais(tamanho, ordem);

        buscasPresentes =
                gerarBuscasPresentes(tamanho);

        buscasAusentes =
                gerarBuscasAusentes(tamanho);

        insercoes = gerarImparesDistintos(
                tamanho,
                tamanho / 10
        );

        remocoes = gerarRemocoes(
                tamanho,
                tamanho / 10
        );

        intervalosMinimos =
                gerarIntervalos(tamanho);
    }

    private int[] gerarValoresIniciais(
            int tamanho,
            OrdemEntrada ordem
    ) {
        int[] valores = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            valores[i] = 2 * (i + 1);
        }

        if (ordem == OrdemEntrada.DECRESCENTE) {
            inverter(valores);
        }

        if (ordem == OrdemEntrada.ALEATORIA) {
            embaralhar(
                    valores,
                    new Random(SEMENTE_ORDEM)
            );
        }

        return valores;
    }

    private int[] gerarBuscasPresentes(
            int tamanho
    ) {
        int[] valores =
                new int[QUANTIDADE_BUSCAS];

        Random random =
                new Random(SEMENTE_BUSCA_PRESENTE);

        for (int i = 0; i < valores.length; i++) {
            int posicao = random.nextInt(tamanho);
            valores[i] = 2 * (posicao + 1);
        }

        return valores;
    }

    private int[] gerarBuscasAusentes(
            int tamanho
    ) {
        int[] valores =
                new int[QUANTIDADE_BUSCAS];

        Random random =
                new Random(SEMENTE_BUSCA_AUSENTE);

        for (int i = 0; i < valores.length; i++) {
            valores[i] =
                    2 * random.nextInt(tamanho) + 1;
        }

        return valores;
    }

    private int[] gerarImparesDistintos(
            int tamanho,
            int quantidade
    ) {
        int[] valores = new int[quantidade];
        boolean[] utilizados = new boolean[tamanho];

        Random random =
                new Random(SEMENTE_INSERCAO);

        int preenchidos = 0;

        while (preenchidos < quantidade) {
            int posicao = random.nextInt(tamanho);

            if (!utilizados[posicao]) {
                utilizados[posicao] = true;
                valores[preenchidos] =
                        2 * posicao + 1;

                preenchidos++;
            }
        }

        return valores;
    }

    private int[] gerarRemocoes(
            int tamanho,
            int quantidade
    ) {
        int[] valores = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            valores[i] = 2 * (i + 1);
        }

        embaralhar(
                valores,
                new Random(SEMENTE_REMOCAO)
        );

        int[] removidos = new int[quantidade];

        System.arraycopy(
                valores,
                0,
                removidos,
                0,
                quantidade
        );

        return removidos;
    }

    private int[] gerarIntervalos(
            int tamanho
    ) {
        int[] minimos =
                new int[QUANTIDADE_INTERVALOS];

        Random random =
                new Random(SEMENTE_INTERVALO);

        int limite = 2 * tamanho - 100;

        for (int i = 0; i < minimos.length; i++) {
            minimos[i] =
                    random.nextInt(limite) + 1;
        }

        return minimos;
    }

    private void embaralhar(
            int[] valores,
            Random random
    ) {
        for (int i = valores.length - 1;
             i > 0;
             i--) {

            int posicao = random.nextInt(i + 1);

            int temporario = valores[i];
            valores[i] = valores[posicao];
            valores[posicao] = temporario;
        }
    }

    private void inverter(int[] valores) {
        int inicio = 0;
        int fim = valores.length - 1;

        while (inicio < fim) {
            int temporario = valores[inicio];
            valores[inicio] = valores[fim];
            valores[fim] = temporario;

            inicio++;
            fim--;
        }
    }

    public long conferenciaEsperada(
            TipoTeste teste
    ) {
        return switch (teste) {
            case CONSTRUCAO ->
                    tamanho;

            case BUSCA_PRESENTE ->
                    buscasPresentes.length;

            case BUSCA_AUSENTE ->
                    0;

            case INSERCAO ->
                    insercoes.length;

            case REMOCAO ->
                    remocoes.length;

            case CONSULTA_INTERVALO ->
                    calcularTotalEsperadoIntervalos();
        };
    }

    private long calcularTotalEsperadoIntervalos() {
        long total = 0;

        for (int minimo : intervalosMinimos) {
            int maximo = minimo + 100;

            int quantidade =
                    maximo / 2
                            - (minimo - 1) / 2;

            total += quantidade;
        }

        return total;
    }

    public int quantidadeOperacoes(
            TipoTeste teste
    ) {
        return switch (teste) {
            case CONSTRUCAO ->
                    valoresIniciais.length;

            case BUSCA_PRESENTE ->
                    buscasPresentes.length;

            case BUSCA_AUSENTE ->
                    buscasAusentes.length;

            case INSERCAO ->
                    insercoes.length;

            case REMOCAO ->
                    remocoes.length;

            case CONSULTA_INTERVALO ->
                    intervalosMinimos.length;
        };
    }

    public int[] getValoresIniciais() {
        return valoresIniciais;
    }

    public int[] getBuscasPresentes() {
        return buscasPresentes;
    }

    public int[] getBuscasAusentes() {
        return buscasAusentes;
    }

    public int[] getInsercoes() {
        return insercoes;
    }

    public int[] getRemocoes() {
        return remocoes;
    }

    public int[] getIntervalosMinimos() {
        return intervalosMinimos;
    }
}
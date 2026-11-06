package br.edu.ifc.estruturas;

public class ArrayLinear implements EstruturaDados {

    private final int[] elementos;
    private int tamanho;

    public ArrayLinear(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade deve ser positiva"
            );
        }

        elementos = new int[capacidade];
    }

    @Override
    public boolean inserir(int valor) {
        if (buscar(valor)) {
            return false;
        }

        if (tamanho == elementos.length) {
            throw new IllegalStateException(
                    "Capacidade do array esgotada"
            );
        }

        elementos[tamanho] = valor;
        tamanho++;

        return true;
    }

    @Override
    public boolean buscar(int valor) {
        return localizar(valor) >= 0;
    }

    @Override
    public boolean excluir(int valor) {
        int posicao = localizar(valor);

        if (posicao < 0) {
            return false;
        }

        int quantidadeDeslocada =
                tamanho - posicao - 1;

        if (quantidadeDeslocada > 0) {
            System.arraycopy(
                    elementos,
                    posicao + 1,
                    elementos,
                    posicao,
                    quantidadeDeslocada
            );
        }

        tamanho--;

        return true;
    }

    @Override
    public int contarIntervalo(
            int minimo,
            int maximo
    ) {
        if (minimo > maximo) {
            return 0;
        }

        int quantidade = 0;

        for (int i = 0; i < tamanho; i++) {
            if (elementos[i] >= minimo
                    && elementos[i] <= maximo) {

                quantidade++;
            }
        }

        return quantidade;
    }

    @Override
    public int tam() {
        return tamanho;
    }

    @Override
    public String nome() {
        return "Array linear";
    }

    private int localizar(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (elementos[i] == valor) {
                return i;
            }
        }

        return -1;
    }
}
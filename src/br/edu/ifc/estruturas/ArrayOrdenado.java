package br.edu.ifc.estruturas;

public class ArrayOrdenado implements EstruturaDados {
    private final int[] elementos;
    private int tamanho;

    public ArrayOrdenado(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva");
        }

        elementos = new int[capacidade];
    }

    @Override
    public boolean inserir(int valor) {
        int posicao = localizar(valor);

        if (posicao >= 0) {
            return false;
        }

        if (tamanho == elementos.length) {
            throw new IllegalStateException("Capacidade do array esgotada");
        }

        posicao = -posicao - 1;

        System.arraycopy(
                elementos,
                posicao,
                elementos,
                posicao + 1,
                tamanho - posicao
        );

        elementos[posicao] = valor;
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

        int quantidade = tamanho - posicao - 1;

        if (quantidade > 0) {
            System.arraycopy(
                    elementos,
                    posicao + 1,
                    elementos,
                    posicao,
                    quantidade
            );
        }

        tamanho--;

        return true;
    }

    @Override
    public int contarIntervalo(int minimo, int maximo) {
        if (minimo > maximo) {
            return 0;
        }

        return primeiroMaiorQue(maximo)
                - primeiroMaiorOuIgual(minimo);
    }

    @Override
    public int tam() {
        return tamanho;
    }

    @Override
    public String nome() {
        return "Array ordenado";
    }

    private int localizar(int valor) {
        int inicio = 0;
        int fim = tamanho - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) >>> 1;

            if (elementos[meio] < valor) {
                inicio = meio + 1;
            } else if (elementos[meio] > valor) {
                fim = meio - 1;
            } else {
                return meio;
            }
        }

        return -inicio - 1;
    }

    private int primeiroMaiorOuIgual(int valor) {
        int inicio = 0;
        int fim = tamanho;

        while (inicio < fim) {
            int meio = (inicio + fim) >>> 1;

            if (elementos[meio] < valor) {
                inicio = meio + 1;
            } else {
                fim = meio;
            }
        }

        return inicio;
    }

    private int primeiroMaiorQue(int valor) {
        int inicio = 0;
        int fim = tamanho;

        while (inicio < fim) {
            int meio = (inicio + fim) >>> 1;

            if (elementos[meio] <= valor) {
                inicio = meio + 1;
            } else {
                fim = meio;
            }
        }

        return inicio;
    }
}
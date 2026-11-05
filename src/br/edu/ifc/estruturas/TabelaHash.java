package br.edu.ifc.estruturas;

public class TabelaHash implements EstruturaDados {
    private static final double FATOR_CARGA_MAXIMO = 0.75;

    private No[] tabela;
    private int tamanho;

    private static class No {
        private final int valor;
        private No proximo;

        private No(int valor, No proximo) {
            this.valor = valor;
            this.proximo = proximo;
        }
    }

    public TabelaHash(int quantidadeEsperada) {
        if (quantidadeEsperada < 0) {
            throw new IllegalArgumentException(
                    "A quantidade esperada não pode ser negativa"
            );
        }

        int capacidade = (int) Math.ceil(
                Math.max(11, quantidadeEsperada)
                        / FATOR_CARGA_MAXIMO
        );

        tabela = new No[proximoPrimo(capacidade)];
    }

    @Override
    public boolean inserir(int valor) {
        int indice = indice(valor, tabela.length);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.valor == valor) {
                return false;
            }

            atual = atual.proximo;
        }

        if (tamanho + 1 > tabela.length * FATOR_CARGA_MAXIMO) {
            redimensionar();
            indice = indice(valor, tabela.length);
        }

        tabela[indice] = new No(valor, tabela[indice]);
        tamanho++;

        return true;
    }

    @Override
    public boolean buscar(int valor) {
        int indice = indice(valor, tabela.length);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.valor == valor) {
                return true;
            }

            atual = atual.proximo;
        }

        return false;
    }

    @Override
    public boolean excluir(int valor) {
        int indice = indice(valor, tabela.length);
        No atual = tabela[indice];
        No anterior = null;

        while (atual != null) {
            if (atual.valor == valor) {
                if (anterior == null) {
                    tabela[indice] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }

                tamanho--;

                return true;
            }

            anterior = atual;
            atual = atual.proximo;
        }

        return false;
    }

    @Override
    public int contarIntervalo(int minimo, int maximo) {
        if (minimo > maximo) {
            return 0;
        }

        int quantidade = 0;

        for (No inicio : tabela) {
            No atual = inicio;

            while (atual != null) {
                if (atual.valor >= minimo && atual.valor <= maximo) {
                    quantidade++;
                }

                atual = atual.proximo;
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
        return "Tabela hash";
    }

    private int indice(int valor, int capacidade) {
        return Math.floorMod(valor, capacidade);
    }

    private void redimensionar() {
        No[] tabelaAntiga = tabela;

        tabela = new No[
                proximoPrimo(tabelaAntiga.length * 2)
                ];

        for (No inicio : tabelaAntiga) {
            No atual = inicio;

            while (atual != null) {
                No proximo = atual.proximo;
                int indice = indice(atual.valor, tabela.length);

                atual.proximo = tabela[indice];
                tabela[indice] = atual;
                atual = proximo;
            }
        }
    }

    private int proximoPrimo(int numero) {
        int candidato = Math.max(2, numero);

        while (!ehPrimo(candidato)) {
            candidato++;
        }

        return candidato;
    }

    private boolean ehPrimo(int numero) {
        if (numero < 2) {
            return false;
        }

        if (numero % 2 == 0) {
            return numero == 2;
        }

        for (int divisor = 3;
             divisor <= numero / divisor;
             divisor += 2) {

            if (numero % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}
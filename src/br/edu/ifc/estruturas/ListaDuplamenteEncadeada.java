package br.edu.ifc.estruturas;

public class ListaDuplamenteEncadeada implements EstruturaDados {
    private No primeiro;
    private No ultimo;
    private int tamanho;

    private static class No {
        private final int valor;
        private No anterior;
        private No proximo;

        private No(int valor) {
            this.valor = valor;
        }
    }

    @Override
    public boolean inserir(int valor) {
        if (buscar(valor)) {
            return false;
        }

        No novo = new No(valor);

        if (primeiro == null) {
            primeiro = novo;
            ultimo = novo;
        } else {
            novo.anterior = ultimo;
            ultimo.proximo = novo;
            ultimo = novo;
        }

        tamanho++;

        return true;
    }

    @Override
    public boolean buscar(int valor) {
        return encontrar(valor) != null;
    }

    @Override
    public boolean excluir(int valor) {
        No atual = encontrar(valor);

        if (atual == null) {
            return false;
        }

        if (atual.anterior == null) {
            primeiro = atual.proximo;
        } else {
            atual.anterior.proximo = atual.proximo;
        }

        if (atual.proximo == null) {
            ultimo = atual.anterior;
        } else {
            atual.proximo.anterior = atual.anterior;
        }

        tamanho--;

        return true;
    }

    @Override
    public int contarIntervalo(int minimo, int maximo) {
        if (minimo > maximo) {
            return 0;
        }

        int quantidade = 0;
        No atual = primeiro;

        while (atual != null) {
            if (atual.valor >= minimo && atual.valor <= maximo) {
                quantidade++;
            }

            atual = atual.proximo;
        }

        return quantidade;
    }

    @Override
    public int tam() {
        return tamanho;
    }

    @Override
    public String nome() {
        return "Lista duplamente encadeada";
    }

    private No encontrar(int valor) {
        No atual = primeiro;

        while (atual != null) {
            if (atual.valor == valor) {
                return atual;
            }

            atual = atual.proximo;
        }

        return null;
    }
}
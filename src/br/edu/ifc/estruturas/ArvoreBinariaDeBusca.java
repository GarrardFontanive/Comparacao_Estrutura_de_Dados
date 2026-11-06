package br.edu.ifc.estruturas;

public class ArvoreBinariaDeBusca
        implements EstruturaDados {

    private No raiz;
    private int tamanho;

    private static class No {
        private int valor;
        private No esquerda;
        private No direita;

        private No(int valor) {
            this.valor = valor;
        }
    }

    @Override
    public boolean inserir(int valor) {
        if (raiz == null) {
            raiz = new No(valor);
            tamanho++;
            return true;
        }

        No atual = raiz;
        No anterior = null;

        while (atual != null) {
            anterior = atual;

            if (valor < atual.valor) {
                atual = atual.esquerda;
            } else if (valor > atual.valor) {
                atual = atual.direita;
            } else {
                return false;
            }
        }

        if (valor < anterior.valor) {
            anterior.esquerda = new No(valor);
        } else {
            anterior.direita = new No(valor);
        }

        tamanho++;
        return true;
    }

    @Override
    public boolean buscar(int valor) {
        No atual = raiz;

        while (atual != null) {
            if (valor < atual.valor) {
                atual = atual.esquerda;
            } else if (valor > atual.valor) {
                atual = atual.direita;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean excluir(int valor) {
        No atual = raiz;
        No pai = null;

        while (atual != null
                && atual.valor != valor) {

            pai = atual;

            if (valor < atual.valor) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }

        if (atual == null) {
            return false;
        }

        if (atual.esquerda != null
                && atual.direita != null) {

            No paiSucessor = atual;
            No sucessor = atual.direita;

            while (sucessor.esquerda != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.esquerda;
            }

            atual.valor = sucessor.valor;
            pai = paiSucessor;
            atual = sucessor;
        }

        No filho;

        if (atual.esquerda != null) {
            filho = atual.esquerda;
        } else {
            filho = atual.direita;
        }

        if (pai == null) {
            raiz = filho;
        } else if (pai.esquerda == atual) {
            pai.esquerda = filho;
        } else {
            pai.direita = filho;
        }

        tamanho--;
        return true;
    }

    @Override
    public int contarIntervalo(
            int minimo,
            int maximo
    ) {
        if (raiz == null || minimo > maximo) {
            return 0;
        }

        No[] pilha = new No[tamanho];
        int topo = 0;
        int quantidade = 0;

        pilha[topo++] = raiz;

        while (topo > 0) {
            No atual = pilha[--topo];

            if (atual.valor < minimo) {
                if (atual.direita != null) {
                    pilha[topo++] = atual.direita;
                }
            } else if (atual.valor > maximo) {
                if (atual.esquerda != null) {
                    pilha[topo++] = atual.esquerda;
                }
            } else {
                quantidade++;

                if (atual.esquerda != null) {
                    pilha[topo++] = atual.esquerda;
                }

                if (atual.direita != null) {
                    pilha[topo++] = atual.direita;
                }
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
        return "Árvore Binária de Busca";
    }
}
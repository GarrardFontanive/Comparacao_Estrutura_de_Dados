package br.edu.ifc.estruturas;

public class ArvoreBinariaDeBusca implements EstruturaDados {
    private No raiz;
    private int tamanho;
    private boolean inseriu;
    private boolean removeu;

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
        inseriu = false;
        raiz = inserir(raiz, valor);

        if (inseriu) {
            tamanho++;
        }

        return inseriu;
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
        removeu = false;
        raiz = remover(raiz, valor);

        if (removeu) {
            tamanho--;
        }

        return removeu;
    }

    @Override
    public int contarIntervalo(int minimo, int maximo) {
        if (minimo > maximo) {
            return 0;
        }

        return contarIntervalo(raiz, minimo, maximo);
    }

    @Override
    public int tam() {
        return tamanho;
    }

    @Override
    public String nome() {
        return "Árvore Binária de Busca";
    }

    private No inserir(No no, int valor) {
        if (no == null) {
            inseriu = true;
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        }

        return no;
    }

    private No remover(No no, int valor) {
        if (no == null) {
            return null;
        }

        if (valor < no.valor) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = remover(no.direita, valor);
        } else {
            removeu = true;

            if (no.esquerda == null) {
                return no.direita;
            }

            if (no.direita == null) {
                return no.esquerda;
            }

            No sucessor = menor(no.direita);

            no.valor = sucessor.valor;
            no.direita = remover(
                    no.direita,
                    sucessor.valor
            );
        }

        return no;
    }

    private int contarIntervalo(
            No no,
            int minimo,
            int maximo
    ) {
        if (no == null) {
            return 0;
        }

        if (no.valor < minimo) {
            return contarIntervalo(
                    no.direita,
                    minimo,
                    maximo
            );
        }

        if (no.valor > maximo) {
            return contarIntervalo(
                    no.esquerda,
                    minimo,
                    maximo
            );
        }

        return 1
                + contarIntervalo(
                no.esquerda,
                minimo,
                maximo
        )
                + contarIntervalo(
                no.direita,
                minimo,
                maximo
        );
    }

    private No menor(No no) {
        No atual = no;

        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }

        return atual;
    }
}
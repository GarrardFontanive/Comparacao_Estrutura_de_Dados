package br.edu.ifc.estruturas;

public class ArvoreAVL implements EstruturaDados {
    private No raiz;
    private int tamanho;
    private boolean inseriu;
    private boolean removeu;

    private static class No {
        private int valor;
        private int altura = 1;
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
        return "Árvore AVL";
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
        } else {
            return no;
        }

        atualizarAltura(no);

        return balancear(no);
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

        atualizarAltura(no);

        return balancear(no);
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

    private No balancear(No no) {
        int fator = fatorBalanceamento(no);

        if (fator > 1) {
            if (fatorBalanceamento(no.esquerda) < 0) {
                no.esquerda = rotacaoEsquerda(
                        no.esquerda
                );
            }

            return rotacaoDireita(no);
        }

        if (fator < -1) {
            if (fatorBalanceamento(no.direita) > 0) {
                no.direita = rotacaoDireita(
                        no.direita
                );
            }

            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No rotacaoDireita(No no) {
        No novaRaiz = no.esquerda;
        No transferido = novaRaiz.direita;

        novaRaiz.direita = no;
        no.esquerda = transferido;

        atualizarAltura(no);
        atualizarAltura(novaRaiz);

        return novaRaiz;
    }

    private No rotacaoEsquerda(No no) {
        No novaRaiz = no.direita;
        No transferido = novaRaiz.esquerda;

        novaRaiz.esquerda = no;
        no.direita = transferido;

        atualizarAltura(no);
        atualizarAltura(novaRaiz);

        return novaRaiz;
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(
                altura(no.esquerda),
                altura(no.direita)
        );
    }

    private int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    private int fatorBalanceamento(No no) {
        if (no == null) {
            return 0;
        }

        return altura(no.esquerda)
                - altura(no.direita);
    }
}
package br.edu.ifc.estruturas;

public class ArvoreRubroNegra implements EstruturaDados {
    private No raiz;
    private int tamanho;
    private boolean inseriu;

    private static class No {
        private int valor;
        private boolean vermelho = true;
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
        raiz.vermelho = false;

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
        if (!buscar(valor)) {
            return false;
        }

        if (!vermelho(raiz.esquerda) && !vermelho(raiz.direita)) {
            raiz.vermelho = true;
        }

        raiz = remover(raiz, valor);

        if (raiz != null) {
            raiz.vermelho = false;
        }

        tamanho--;

        return true;
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
        return "Árvore Rubro Negra";
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

        return balancear(no);
    }

    private No remover(No no, int valor) {
        if (valor < no.valor) {
            if (!vermelho(no.esquerda) && !vermelho(no.esquerda.esquerda)) {
                no = moverVermelhoEsquerda(no);
            }

            no.esquerda = remover(no.esquerda, valor);
        } else {
            if (vermelho(no.esquerda)) {
                no = rotacaoDireita(no);
            }

            if (valor == no.valor && no.direita == null) {
                return null;
            }

            if (!vermelho(no.direita) && !vermelho(no.direita.esquerda)) {
                no = moverVermelhoDireita(no);
            }

            if (valor == no.valor) {
                no.valor = menor(no.direita).valor;
                no.direita = removerMenor(no.direita);
            } else {
                no.direita = remover(no.direita, valor);
            }
        }

        return balancear(no);
    }

    private No removerMenor(No no) {
        if (no.esquerda == null) {
            return null;
        }

        if (!vermelho(no.esquerda) && !vermelho(no.esquerda.esquerda)) {
            no = moverVermelhoEsquerda(no);
        }

        no.esquerda = removerMenor(no.esquerda);

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
        if (vermelho(no.direita) && !vermelho(no.esquerda)) {
            no = rotacaoEsquerda(no);
        }

        if (vermelho(no.esquerda) && vermelho(no.esquerda.esquerda)) {
            no = rotacaoDireita(no);
        }

        if (vermelho(no.esquerda) && vermelho(no.direita)) {
            inverterCores(no);
        }

        return no;
    }

    private No moverVermelhoEsquerda(No no) {
        inverterCores(no);

        if (vermelho(no.direita.esquerda)) {
            no.direita = rotacaoDireita(no.direita);
            no = rotacaoEsquerda(no);
            inverterCores(no);
        }

        return no;
    }

    private No moverVermelhoDireita(No no) {
        inverterCores(no);

        if (vermelho(no.esquerda.esquerda)) {
            no = rotacaoDireita(no);
            inverterCores(no);
        }

        return no;
    }

    private No rotacaoDireita(No no) {
        No novaRaiz = no.esquerda;

        no.esquerda = novaRaiz.direita;
        novaRaiz.direita = no;
        novaRaiz.vermelho = no.vermelho;
        no.vermelho = true;

        return novaRaiz;
    }

    private No rotacaoEsquerda(No no) {
        No novaRaiz = no.direita;

        no.direita = novaRaiz.esquerda;
        novaRaiz.esquerda = no;
        novaRaiz.vermelho = no.vermelho;
        no.vermelho = true;

        return novaRaiz;
    }

    private void inverterCores(No no) {
        no.vermelho = !no.vermelho;
        no.esquerda.vermelho = !no.esquerda.vermelho;
        no.direita.vermelho = !no.direita.vermelho;
    }

    private boolean vermelho(No no) {
        return no != null && no.vermelho;
    }
}
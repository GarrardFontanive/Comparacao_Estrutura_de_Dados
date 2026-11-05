package br.edu.ifc.estruturas;

public interface EstruturaDados {
    boolean inserir(int valor);
    boolean buscar(int valor);
    boolean excluir(int valor);
    int contarIntervalo(int min, int max);
    int tam();
    String nome();
}

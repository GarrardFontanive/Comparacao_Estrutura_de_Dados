package br.edu.ifc.experimento;

import br.edu.ifc.estruturas.EstruturaDados;
import br.edu.ifc.estruturas.FabricaEstruturas;
import br.edu.ifc.estruturas.TipoEstrutura;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExecutadorExperimento {
    private static final int[] TAMANHOS = {
            5000, 10000, 100000
    };

    private static final int REPETICOES = 5;

    public List<ResultadoBruto> executar() {
        List<ResultadoBruto> resultados =
                new ArrayList<>();

        for (int tamanho : TAMANHOS) {
            for (OrdemEntrada ordem
                    : OrdemEntrada.values()) {

                DadosExperimento dados =
                        new DadosExperimento(
                                tamanho,
                                ordem
                        );

                for (TipoEstrutura tipo
                        : TipoEstrutura.values()) {

                    for (TipoTeste teste
                            : TipoTeste.values()) {

                        executarUmaVez(
                                tipo,
                                teste,
                                dados
                        );

                        for (int repeticao = 1;
                             repeticao <= REPETICOES;
                             repeticao++) {

                            ResultadoMedicao medicao =
                                    executarUmaVez(
                                            tipo,
                                            teste,
                                            dados
                                    );

                            verificarConferencia(
                                    tipo,
                                    teste,
                                    dados,
                                    medicao.conferencia()
                            );

                            ResultadoBruto resultado =
                                    new ResultadoBruto(
                                            tamanho,
                                            ordem.getNome(),
                                            tipo.getNome(),
                                            teste.getNome(),
                                            repeticao,
                                            dados.quantidadeOperacoes(
                                                    teste
                                            ),
                                            medicao.tempoNs(),
                                            medicao.conferencia()
                                    );

                            resultados.add(resultado);
                            mostrarResultado(resultado);
                        }
                    }
                }
            }
        }

        return resultados;
    }

    private ResultadoMedicao executarUmaVez(
            TipoEstrutura tipo,
            TipoTeste teste,
            DadosExperimento dados
    ) {
        if (teste == TipoTeste.CONSTRUCAO) {
            return medirConstrucao(tipo, dados);
        }

        EstruturaDados estrutura =
                FabricaEstruturas.criar(
                        tipo,
                        dados.getValoresIniciais().length
                );

        preencherEstrutura(
                estrutura,
                dados.getValoresIniciais()
        );

        return switch (teste) {
            case BUSCA_PRESENTE ->
                    medirBusca(
                            estrutura,
                            dados.getBuscasPresentes()
                    );

            case BUSCA_AUSENTE ->
                    medirBusca(
                            estrutura,
                            dados.getBuscasAusentes()
                    );

            case INSERCAO ->
                    medirInsercao(
                            estrutura,
                            dados.getInsercoes()
                    );

            case REMOCAO ->
                    medirRemocao(
                            estrutura,
                            dados.getRemocoes()
                    );

            case CONSULTA_INTERVALO ->
                    medirIntervalos(
                            estrutura,
                            dados.getIntervalosMinimos()
                    );

            case CONSTRUCAO ->
                    throw new IllegalStateException();
        };
    }

    private ResultadoMedicao medirConstrucao(
            TipoEstrutura tipo,
            DadosExperimento dados
    ) {
        long inicio = System.nanoTime();

        EstruturaDados estrutura =
                FabricaEstruturas.criar(
                        tipo,
                        dados.getValoresIniciais().length
                );

        for (int valor : dados.getValoresIniciais()) {
            estrutura.inserir(valor);
        }

        long fim = System.nanoTime();

        return new ResultadoMedicao(
                fim - inicio,
                estrutura.tam()
        );
    }

    private ResultadoMedicao medirBusca(
            EstruturaDados estrutura,
            int[] valores
    ) {
        long conferencia = 0;
        long inicio = System.nanoTime();

        for (int valor : valores) {
            if (estrutura.buscar(valor)) {
                conferencia++;
            }
        }

        long fim = System.nanoTime();

        return new ResultadoMedicao(
                fim - inicio,
                conferencia
        );
    }

    private ResultadoMedicao medirInsercao(
            EstruturaDados estrutura,
            int[] valores
    ) {
        long conferencia = 0;
        long inicio = System.nanoTime();

        for (int valor : valores) {
            if (estrutura.inserir(valor)) {
                conferencia++;
            }
        }

        long fim = System.nanoTime();

        return new ResultadoMedicao(
                fim - inicio,
                conferencia
        );
    }

    private ResultadoMedicao medirRemocao(
            EstruturaDados estrutura,
            int[] valores
    ) {
        long conferencia = 0;
        long inicio = System.nanoTime();

        for (int valor : valores) {
            if (estrutura.excluir(valor)) {
                conferencia++;
            }
        }

        long fim = System.nanoTime();

        return new ResultadoMedicao(
                fim - inicio,
                conferencia
        );
    }

    private ResultadoMedicao medirIntervalos(
            EstruturaDados estrutura,
            int[] minimos
    ) {
        long conferencia = 0;
        long inicio = System.nanoTime();

        for (int minimo : minimos) {
            conferencia += estrutura.contarIntervalo(
                    minimo,
                    minimo + 100
            );
        }

        long fim = System.nanoTime();

        return new ResultadoMedicao(
                fim - inicio,
                conferencia
        );
    }

    private void preencherEstrutura(
            EstruturaDados estrutura,
            int[] valores
    ) {
        for (int valor : valores) {
            estrutura.inserir(valor);
        }

        if (estrutura.tam() != valores.length) {
            throw new IllegalStateException(
                    "Quantidade inicial incorreta em "
                            + estrutura.nome()
            );
        }
    }

    private void verificarConferencia(
            TipoEstrutura tipo,
            TipoTeste teste,
            DadosExperimento dados,
            long conferencia
    ) {
        long esperado =
                dados.conferenciaEsperada(teste);

        if (conferencia != esperado) {
            throw new IllegalStateException(
                    "Resultado incorreto em "
                            + tipo.getNome()
                            + ", teste "
                            + teste.getNome()
                            + ". Esperado: "
                            + esperado
                            + ", obtido: "
                            + conferencia
            );
        }
    }

    private void mostrarResultado(
            ResultadoBruto resultado
    ) {
        System.out.printf(
                Locale.US,
                "%d | %s | %s | %s | repetição %d"
                        + " | %d ns | conferência %d%n",
                resultado.tamanho(),
                resultado.ordem(),
                resultado.estrutura(),
                resultado.teste(),
                resultado.repeticao(),
                resultado.tempoTotalNs(),
                resultado.conferencia()
        );
    }

    private record ResultadoMedicao(
            long tempoNs,
            long conferencia
    ) {
    }
}
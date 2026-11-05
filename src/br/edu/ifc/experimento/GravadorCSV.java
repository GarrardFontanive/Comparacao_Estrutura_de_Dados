package br.edu.ifc.experimento;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GravadorCSV {

    private GravadorCSV() {
    }

    public static void gravar(
            List<ResultadoBruto> resultados,
            Path pasta
    ) throws IOException {
        Files.createDirectories(pasta);

        gravarResultadosBrutos(
                resultados,
                pasta.resolve("resultados_brutos.csv")
        );

        gravarResumo(
                resultados,
                pasta.resolve("resumo.csv")
        );
    }

    private static void gravarResultadosBrutos(
            List<ResultadoBruto> resultados,
            Path arquivo
    ) throws IOException {
        List<String> linhas = new ArrayList<>();

        linhas.add(
                "tamanho;ordem;estrutura;teste;"
                        + "repeticao;quantidade_operacoes;"
                        + "tempo_total_ns"
        );

        for (ResultadoBruto resultado : resultados) {
            linhas.add(
                    resultado.tamanho() + ";"
                            + resultado.ordem() + ";"
                            + resultado.estrutura() + ";"
                            + resultado.teste() + ";"
                            + resultado.repeticao() + ";"
                            + resultado.quantidadeOperacoes() + ";"
                            + resultado.tempoTotalNs()
            );
        }

        Files.write(
                arquivo,
                linhas,
                StandardCharsets.UTF_8
        );
    }

    private static void gravarResumo(
            List<ResultadoBruto> resultados,
            Path arquivo
    ) throws IOException {
        Map<ChaveResumo, List<ResultadoBruto>> grupos =
                new LinkedHashMap<>();

        for (ResultadoBruto resultado : resultados) {
            ChaveResumo chave = new ChaveResumo(
                    resultado.tamanho(),
                    resultado.ordem(),
                    resultado.estrutura(),
                    resultado.teste()
            );

            grupos.computeIfAbsent(
                    chave,
                    valor -> new ArrayList<>()
            ).add(resultado);
        }

        List<String> linhas = new ArrayList<>();

        linhas.add(
                "tamanho;ordem;estrutura;teste;"
                        + "mediana_tempo_total_ms;"
                        + "mediana_tempo_operacao_ns"
        );

        for (Map.Entry<
                ChaveResumo,
                List<ResultadoBruto>> entrada
                : grupos.entrySet()) {

            List<ResultadoBruto> grupo =
                    entrada.getValue();

            grupo.sort(
                    Comparator.comparingLong(
                            ResultadoBruto::tempoTotalNs
                    )
            );

            ResultadoBruto mediano = grupo.get(2);
            double tempoTotalMs =
                    mediano.tempoTotalNs()
                            / 1_000_000.0;

            double tempoOperacaoNs =
                    mediano.tempoTotalNs()
                            / (double) mediano.quantidadeOperacoes();

            ChaveResumo chave = entrada.getKey();

            linhas.add(String.format(
                    Locale.US,
                    "%d;%s;%s;%s;%.6f;%.6f",
                    chave.tamanho(),
                    chave.ordem(),
                    chave.estrutura(),
                    chave.teste(),
                    tempoTotalMs,
                    tempoOperacaoNs
            ));
        }

        Files.write(
                arquivo,
                linhas,
                StandardCharsets.UTF_8
        );
    }

    private record ChaveResumo(
            int tamanho,
            String ordem,
            String estrutura,
            String teste
    ) {
    }
}
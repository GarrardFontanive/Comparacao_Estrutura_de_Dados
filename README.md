# Comparação de Estruturas de Dados

Projeto acadêmico da disciplina de **Estrutura de Dados II**, desenvolvido em **Java**, para comparar o desempenho de sete estruturas em diferentes operações e cenários de entrada.

## Estruturas implementadas

| Estrutura | Implementação |
| --- | --- |
| Array linear | Inserção ao final e busca sequencial, sem manter os valores ordenados. |
| Array ordenado | Busca binária e manutenção da ordenação nas inserções. |
| Lista duplamente encadeada | Nós com referências para o elemento anterior e o próximo. |
| Tabela hash | Tratamento de colisões por encadeamento e redimensionamento automático. |
| Árvore binária de busca | Inserção, busca e remoção iterativas, sem balanceamento. |
| Árvore AVL | Árvore de busca com balanceamento por rotações. |
| Árvore rubro-negra | Árvore de busca balanceada com cores e rotações. |

Todas seguem a interface `EstruturaDados`, com operações de inserção, busca, remoção e contagem de valores em um intervalo.

## Experimentos

São avaliadas a construção das estruturas, as buscas por valores presentes e ausentes, a inserção, a remoção e a consulta por intervalo.

- **Tamanhos de entrada:** 5.000, 10.000 e 100.000 elementos.
- **Ordens de entrada:** crescente, decrescente e aleatória.
- **Repetições:** cinco medições por cenário, precedidas de uma execução de aquecimento.
- **Reprodutibilidade:** sementes fixas e independentes para a ordem de entrada (`42`), buscas presentes (`43`), buscas ausentes (`44`), inserções (`45`), remoções (`46`) e intervalos (`47`).

Antes dos experimentos, o programa executa testes funcionais nas sete estruturas. Durante as medições, confere os resultados das operações e exibe os tempos em nanossegundos no terminal.

## Resultados em CSV

Ao final da execução, o programa grava dois arquivos na pasta `resultados/`, usando ponto e vírgula como separador:

- [`resultados_brutos.csv`](resultados/resultados_brutos.csv): tempos em nanossegundos e quantidade de operações de cada repetição.
- [`resumo.csv`](resultados/resumo.csv): mediana do tempo total em milissegundos e do tempo por operação em nanossegundos, para cada cenário.

Uma execução completa gera **1.890 medições** e **378 linhas de resumo**, além dos cabeçalhos. Novas execuções substituem esses arquivos; os tempos variam conforme o ambiente de execução.

## Como executar

É necessário ter o **JDK 26 ou superior** instalado. O projeto não utiliza bibliotecas externas.

Na pasta do projeto, compile e execute:

```bash
javac -encoding UTF-8 -sourcepath src -d out src/br/edu/ifc/Main/Main.java
java -cp out br.edu.ifc.Main.Main
```

No IntelliJ IDEA, abra o projeto, configure o JDK e execute a classe `br.edu.ifc.Main.Main`.

## Organização

```text
src/br/edu/ifc/
├── estruturas/   # Interface, implementações e fábrica das estruturas
├── experimento/  # Geração de dados, testes e medições
└── Main/         # Ponto de entrada do programa
```

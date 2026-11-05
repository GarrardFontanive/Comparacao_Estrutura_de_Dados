# Comparação de Estruturas de Dados

Projeto acadêmico da disciplina de **Estrutura de Dados II**, desenvolvido em **Java**, para comparar o desempenho de quatro estruturas em diferentes operações e cenários de entrada.

## Estruturas implementadas

| Estrutura | Implementação |
| --- | --- |
| Array ordenado | Busca binária e manutenção da ordenação nas inserções. |
| Lista duplamente encadeada | Nós com referências para o elemento anterior e o próximo. |
| Tabela hash | Tratamento de colisões por encadeamento e redimensionamento automático. |
| Árvore AVL | Árvore de busca com balanceamento por rotações. |

Todas seguem a interface `EstruturaDados`, com operações de inserção, busca, remoção e contagem de valores em um intervalo.

## Experimentos

São avaliadas a construção das estruturas, as buscas por valores presentes e ausentes, a inserção, a remoção e a consulta por intervalo.

- **Tamanhos de entrada:** 5.000, 10.000 e 100.000 elementos.
- **Ordens de entrada:** crescente, decrescente e aleatória.
- **Repetições:** cinco medições por cenário, precedidas de uma execução de aquecimento.
- **Reprodutibilidade:** geração de dados com semente fixa (`42`).

Antes dos experimentos, o programa executa testes funcionais nas quatro estruturas. Durante as medições, confere os resultados das operações e exibe os tempos em nanossegundos no terminal.

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

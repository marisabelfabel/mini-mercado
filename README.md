# MiniMercado

Aplicação de console em Java para gerenciamento de clientes, produtos e compras.

## Pré-requisitos

- Java JDK 11+ instalado
- Terminal PowerShell (Windows)

## Como rodar no VSCode

O projeto já tem tarefas configuradas em `.vscode/tasks.json`.

1. Abra a pasta do projeto no VSCode.
2. Abra a Paleta de Comandos (`Ctrl + Shift + P`).
3. Execute `Tasks: Run Task`.
4. Selecione a tarefa `Rodar`.

Essa tarefa compila o projeto e executa a classe principal.

## Como compilar e rodar pelo terminal

Na raiz do projeto:

```shell
javac -encoding UTF-8 -d target/classes *.java
java -cp target/classes Main
```

## Estrutura de saída da compilação

- Os arquivos `.class` são gerados em `target/classes`.
- Os arquivos CSV (dados) ficam na raiz do projeto:
  - `clientes.csv`
  - `produtos.csv`
  - `compras.csv`
  - `compras_produtos.csv`
  - `sequencias.csv`

## Observações

- A aplicação é interativa no terminal.
- Para encerrar, escolha a opção `0` nos menus.

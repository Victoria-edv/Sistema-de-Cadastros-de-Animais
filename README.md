# Sistema de Cadastro e Gerenciamento de Pets 🚀🐾

Este projeto é um sistema de linha de comando (CLI) para gerenciar o cadastro e as informações de pets. Ele foi desenvolvido em Java como um desafio para aplicar e consolidar conceitos de Orientação a Objetos, manipulação de arquivos e lógica de programação.

## Funcionalidades do Sistema ✅

O programa permite que o usuário interaja com um sistema completo de gerenciamento, incluindo:

* **Cadastro de Novos Pets:** Coleta e valida dados como nome, tipo, sexo, idade, peso e raça.
* **Manipulação de Arquivos:** Salva cada pet cadastrado em um arquivo de texto com o nome e data formatados (`YYYYMMDDTHHMM-NOME.txt`).
* **Busca de Pets:** Permite buscar pets por diversos critérios (nome, tipo, idade, etc.) de forma flexível e case-insensitive.
* **Listagem Completa:** Exibe os detalhes de todos os pets cadastrados no sistema.
* **Alteração de Dados:** Possibilita alterar as informações de um pet existente.
* **Exclusão de Pets:** Permite deletar permanentemente o registro de um pet do sistema.
* **Passo Extra - Gerenciamento de Formulário:** Adiciona uma funcionalidade extra para criar, alterar ou excluir perguntas do arquivo `formulario.txt`, tornando o sistema mais dinâmico.

## Tecnologias e Conceitos Aplicados 💻

* **Java 11+:** A base do desenvolvimento do projeto.
* **Programação Orientada a Objetos (POO):** Utilização de classes (`Pet`, `PetSystem`), atributos privados e `enums` para modelar o domínio da aplicação de forma organizada.
* **Manipulação de Arquivos (File I/O):** Uso das classes `File`, `FileReader`, `BufferedReader`, `FileWriter` e `BufferedWriter` para ler e escrever dados de forma segura e eficiente.
* **Tratamento de Exceções:** Implementação de blocos `try-catch` para lidar com erros de entrada do usuário (`InputMismatchException`, `NumberFormatException`) e falhas de arquivo (`IOException`).
* **Estruturas de Dados:** Utilização de `List` e `ArrayList` para armazenar e manipular dados na memória.
* **API de Data e Hora:** Uso das classes `LocalDateTime` e `DateTimeFormatter` para gerar e formatar nomes de arquivos com base na data e hora.

## Como Executar o Projeto ▶️

Para rodar o projeto em sua máquina local, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/victori-edv/Sistema-de-Cadastros-de-Animais](https://github.com/victori-edv/Sistema-de-Cadastros-de-Animais)
    ```

2.  **Abra o projeto:**
    Abra a pasta do projeto em sua IDE Java preferida (como IntelliJ IDEA, Eclipse ou VS Code).

3.  **Execute a classe `Main`:**
    Navegue até o arquivo `Main.java` e execute o método `main`. O programa será iniciado no terminal da sua IDE.

### Estrutura do Projeto  📁

A estrutura de pastas e arquivos do projeto é a seguinte:
```
├── .idea/
├── petsCadastrados/           # Pasta criada pelo programa para salvar os pets
├── src/
│   ├── resources/
│   │   └── formulario.txt     # Arquivo de perguntas lido e manipulado pelo sistema
│   ├── Main.java              # Ponto de entrada da aplicação
│   ├── Pet.java               # Classe que modela o objeto Pet
│   └── PetSystem.java         # Classe principal com toda a lógica de negócio
└── README.md
```

## Autor 🧑‍💻

**Victória Eduarda Bomfim Carvalho**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/victoria-carvalho11/)
[![GitHub](https://github.com/Victoria-edv)

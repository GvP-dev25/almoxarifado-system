# Almoxarifado System

Projeto desenvolvido com o objetivo de praticar conceitos de Java e simular o funcionamento básico de um sistema de almoxarifado.

A ideia surgiu a partir de situações e processos observados no ambiente de trabalho, adaptados para fins de estudo e desenvolvimento.

## Objetivo

O sistema busca simular o controle de materiais de um almoxarifado, permitindo futuramente realizar operações como entrada e saída de produtos e acompanhar as movimentações realizadas.

## Funcionalidades planejadas

- Cadastro de produtos
- Cadastro de filiais
- Controle de produtos por filial
- Entrada de materiais
- Saída de materiais por requisição
- Controle de movimentações
- Validações de estoque
- Controle de fechamento mensal

## Estrutura inicial

Atualmente o projeto possui as seguintes classes:

- `Product` — representa um product cadastrado no sistema.
- `Branch` — representa uma filial e seus produtos armazenados.
- `BranchProduct` — representa a relação entre um product e uma filial, incluindo quantidade e localização.

## Tecnologias

Atualmente o projeto está sendo desenvolvido com:

- Java
- Programação Orientada a Objetos

## Próximos passos

O projeto será desenvolvido gradualmente conforme o avanço nos estudos. Algumas funcionalidades e tecnologias poderão ser adicionadas futuramente, como persistência de dados, banco de dados, Spring Boot, autenticação e Docker.

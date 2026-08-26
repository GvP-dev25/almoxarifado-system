Almoxarifado System

Projeto desenvolvido para praticar Java, Programação Orientada a Objetos e conceitos de desenvolvimento backend,
simulando o funcionamento básico de um sistema de almoxarifado.

A ideia surgiu a partir de situações e processos observados no ambiente de trabalho, que foram adaptados para fins de
estudo e desenvolvimento.

Além da prática de programação, este projeto também está sendo utilizado como uma forma de praticar inglês aplicado à
programação. Por isso, classes, métodos, atributos, enums e outros elementos do código são escritos em inglês. Alguns
termos ou nomes podem ser ajustados futuramente conforme meu vocabulário e conhecimento da linguagem evoluírem.

Objetivo

O sistema busca simular o controle de materiais de um almoxarifado, permitindo registrar produtos, controlar seus
estoques por filial, realizar entradas e saídas de materiais e registrar o histórico das movimentações.

O projeto está sendo desenvolvido de forma gradual, acompanhando meu aprendizado em Java e, posteriormente, em Spring
Boot e desenvolvimento de APIs REST.

MVP

O MVP atual contempla:

Cadastro de produtos Cadastro de filiais Controle de produtos por filial Controle de quantidade em estoque Entrada de
materiais através de Invoice Saída de materiais através de Request Atendimento de produtos solicitados Estorno de
produtos atendidos Registro de movimentações Registro da origem de cada movimentação Controle de diferentes destinos de
uma Invoice Validações de quantidade e estoque Prevenção de processamento duplicado de Invoice Prevenção de atendimento
e estorno duplicados Tratamento de regras de negócio através de Exceptions Principais conceitos praticados

Durante o desenvolvimento do MVP, foram praticados conceitos como:

Classes e objetos Encapsulamento Associação entre objetos ArrayList HashMap List Enum Métodos e construtores equals () e
hashCode ()
Controle de estado dos objetos Validação de regras de negócio Exceptions Separação de responsabilidades Registro de
movimentações Imutabilidade das listas expostas pelas classes Principais classes Product — representa um produto
cadastrado no sistema. Branch — representa uma filial e mantém os produtos associados a ela. BranchProduct — representa
um produto dentro de uma filial, controlando quantidade, localização e movimentações. Invoice — representa uma nota
fiscal utilizada para registrar entradas de materiais. ProductInvoice — representa um produto e sua quantidade dentro de
uma Invoice. Request — representa uma requisição de materiais. ProductRequest — representa um produto solicitado em uma
Request, incluindo quantidade solicitada, quantidade atendida e possibilidade de estorno. Movement — representa uma
movimentação realizada no estoque. Exceptions

As regras de negócio que impedem uma operação de ser realizada são representadas através de Exceptions específicas.

Alguns exemplos:

InvalidQuantityException InsufficientStockException CodeNotFoundException RequestedQuantityExceededException
ProductRequestFulfilledException ProductRequestAlreadyRevertedException InvoiceAlreadyProcessedException

A utilização de Exceptions permite diferenciar os motivos pelos quais uma operação não pode ser realizada, em vez de
utilizar apenas valores booleanos como true ou false.

Tecnologias

Atualmente o projeto está sendo desenvolvido com:

Java Programação Orientada a Objetos IntelliJ IDEA Git e GitHub Aprendizado de inglês

Uma das propostas pessoais deste projeto é utilizar o desenvolvimento como uma forma de praticar inglês técnico.

Por isso, estou gradualmente utilizando inglês em:

Nomes de classes Variáveis Métodos Enums Exceptions Comentários Mensagens Commits

O inglês utilizado no projeto ainda faz parte do meu processo de aprendizado. Portanto, alguns nomes ou expressões podem
não ser os mais naturais inicialmente e poderão ser revisados conforme meu conhecimento evoluir.

Próximos passos

Após a conclusão do MVP, o projeto continuará evoluindo de acordo com meu aprendizado.

Entre os próximos objetivos estão:

Implementar testes automatizados com JUnit. Revisar e aprimorar as regras de negócio. Introduzir persistência de dados.
Trabalhar com banco de dados. Estudar JDBC e integração com banco de dados. Migrar o projeto para Spring Boot. Criar uma
API REST. Implementar tratamento adequado de Exceptions na camada da API. Implementar autenticação e autorização.
Estudar Docker e containerização da aplicação.

O projeto será desenvolvido gradualmente, priorizando o aprendizado e a compreensão dos conceitos em vez de simplesmente
adicionar funcionalidades.
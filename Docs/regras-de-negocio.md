# Regras de Negócio

## Estoque

- Um produto não pode ter uma saída de estoque maior que a quantidade disponível.
- As quantidades de entrada e saída de estoque devem ser maiores que zero.
- Toda movimentação de estoque deve ser registrada.
- Cada BranchProduct possui sua própria quantidade em estoque.
- Cada BranchProduct possui seu próprio histórico de movimentações.
- Um BranchProduct criado com uma quantidade inicial maior que zero deve registrar uma movimentação ENTRY com a
  descrição "Initial stock".

## Movimentações

- Uma movimentação deve possuir um identificador único.
- Uma movimentação deve registrar automaticamente sua data e hora de criação.
- Os tipos de movimentação são:
    - ENTRY
    - OUTPUT
    - REVERSAL
- As quantidades das movimentações são sempre positivas.
- O tipo da movimentação determina se a quantidade entra ou sai do estoque.
- As movimentações de estoque não podem ser criadas diretamente fora de BranchProduct.

## Requisições

- Uma requisição pode conter vários produtos.
- Um produto solicitado pode ser atendido parcialmente.
- A quantidade atendida não pode ser maior que a quantidade solicitada.
- Uma requisição pode gerar movimentações de saída de estoque.
- Um futuro estorno deverá estar relacionado à requisição que originou a saída de estoque.
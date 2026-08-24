# Modelo de Domínio

## Product

Representa um produto cadastrado no sistema.

Principais informações:

- código
- descrição

O código do produto é utilizado como sua identidade.

---

## Branch

Representa uma filial da empresa.

Principais informações:

- código
- nome
- produtos

Cada Branch armazena seus BranchProducts utilizando um Map, onde o código do produto é utilizado como chave.

Responsabilidades:

- Adicionar um produto à filial.
- Buscar um BranchProduct pelo código do produto.
- Realizar a entrada de produtos.

---

## BranchProduct

Representa um produto específico dentro de uma filial específica.

Principais informações:

- Product
- Branch
- quantidade
- localização
- histórico de movimentações

Responsabilidades:

- Adicionar quantidade ao estoque.
- Remover quantidade do estoque.
- Registrar movimentações de estoque.
- Impedir operações inválidas no estoque.

---

## Movement

Representa uma movimentação de estoque.

Principais informações:

- UUID
- MovementType
- LocalDateTime
- quantidade
- descrição

As movimentações são criadas automaticamente quando ocorre uma alteração no estoque.

---

## MovementType

Define os tipos disponíveis de movimentação de estoque:

- ENTRY
- OUTPUT
- REVERSAL

---

## Request

Representa uma requisição de materiais.

Informações planejadas atualmente:

- número da requisição
- Branch
- Lista de ProductRequest

Uma Request pode conter vários produtos solicitados.

---

## ProductRequest

Representa um produto dentro de uma Request.

Informações planejadas:

- BranchProduct
- quantidade solicitada
- quantidade atendida

A quantidade atendida não pode ser maior que a quantidade solicitada.
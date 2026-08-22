package br.com.almoxarifado.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Filial {
    private String codigo, nome;
    private List<ProdFilial> produtos;
    private List<ProdFilial> mostrarProd;

    public Filial(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        produtos = new ArrayList<>();
        mostrarProd = Collections.unmodifiableList(produtos);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<ProdFilial> getProdutos() {
        return mostrarProd;
    }

    public void adicionarProduto(ProdFilial produto) {
        produtos.add(produto);
    }

   /* public String mostrarProdutos() {
        String produtosArmazenados = "";
        for (ProdFilial prod : mostrarProd) {

            produtosArmazenados += "Código: " + prod.getProduto().getCodigo() + "\nDescrição: "
                    + prod.getProduto().getDescricao() + "\nFilial: " + prod.getFilial().getCodigo() + "\nNome: "
                    + prod.getFilial().getNome() + "\nQuantidade: " + prod.getQuantidade() + "\nLocalização: "
                    + prod.getLocalizacao() + "\n -----------------------------------------";
        }
        return produtosArmazenados;
    }*/

}

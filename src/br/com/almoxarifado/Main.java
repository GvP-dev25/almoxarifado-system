package br.com.almoxarifado;

import br.com.almoxarifado.model.Filial;
import br.com.almoxarifado.model.ProdFilial;
import br.com.almoxarifado.model.Produto;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Produto novoProd = new Produto("1", "Parafuso 1/2 x 1");

        Produto novoProd2 = new Produto("2", "Parafuso 1/2 x 2");

        Produto novoProd3 = new Produto("3", "Parafuso 1/2 x 3");

        Filial filialSul = new Filial("001", "Sul");

        ProdFilial produto1 = new ProdFilial(novoProd, filialSul, 10, "C003.P023.A.01");
        ProdFilial produto2 = new ProdFilial(novoProd2, filialSul, 300, "C003.P023.B.01");
        ProdFilial produto3 = new ProdFilial(novoProd3, filialSul, 150, "C003.P023.C.01");
        ProdFilial produto4 = new ProdFilial(novoProd3, filialSul, 150, "C003.P023.C.01");

        filialSul.adicionarProduto(produto1);
        filialSul.adicionarProduto(produto2);
        filialSul.adicionarProduto(produto3);
        filialSul.adicionarProduto(produto4);

        String saida = "";
        List<ProdFilial> produtos = filialSul.getProdutos();


        for (ProdFilial prod : produtos) {
            saida += "Código: " + prod.getProduto().getCodigo() + "\nDescrição: "
                    + prod.getProduto().getDescricao() + "\nFilial: " + prod.getFilial().getCodigo() + "\nNome: "
                    + prod.getFilial().getNome() + "\nQuantidade: " + prod.getQuantidade() + "\nLocalização: "
                    + prod.getLocalizacao() + "\n -----------------------------------------";
        }


        System.out.println(saida);
    }

}
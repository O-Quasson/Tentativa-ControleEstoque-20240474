package com.controleestoque.api_estoque.classe;

import java.math.BigDecimal;

public class VendaItens {
    private Long id_produto;
    private int quantidade;
    private BigDecimal preco;

    public VendaItens(Long id_produto, int quantidade, BigDecimal preco){
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}

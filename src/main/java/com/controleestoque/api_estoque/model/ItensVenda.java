package com.controleestoque.api_estoque.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_itensvenda")
public class ItensVenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    //pq caralhos vcs colocaram preço como bigdecimal mano???? só usa float ou double como uma pessoa normal
    private BigDecimal preco;

    public ItensVenda(){}

    public ItensVenda(Long id, Venda venda, Produto produto, int quantidade, BigDecimal preco){
        this.id = id;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }
    
    public Venda getVenda() {
        return venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}

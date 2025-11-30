package com.controleestoque.api_estoque.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_venda")
public class Venda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensVenda> itens;

    public Venda(){}

    public Venda(Long id, Cliente cliente, List<ItensVenda> itens){
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItensVenda> getItens() {
        return itens;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItens(List<ItensVenda> itens) {
        this.itens = itens;
    }


}

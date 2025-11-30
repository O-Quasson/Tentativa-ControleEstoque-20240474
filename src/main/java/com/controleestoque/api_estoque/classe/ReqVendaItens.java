package com.controleestoque.api_estoque.classe;

import java.util.List;

import com.controleestoque.api_estoque.model.ItensVenda;

public class ReqVendaItens {
    private Long id_cliente;
    private List<VendaItens> itens;

    public ReqVendaItens(Long id_cliente, List<VendaItens> itens){
        this.id_cliente = id_cliente;
        this.itens = itens;
    }

    public Long getIdCliente(){
        return id_cliente;
    }

    public List<VendaItens> getItens(){
        return itens;
    }

    public void setIdCliente(Long id_cliente){
        this.id_cliente = id_cliente;
    }

    public void setItens(List<VendaItens> itens){
        this.itens = itens;
    }
}

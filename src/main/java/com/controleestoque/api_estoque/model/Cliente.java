package com.controleestoque.api_estoque.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="tb_cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; 

    private String email;

    private String cpf;

    private int telefone;

    //só adaptei do categoria lmfaooooo
    //como caralhos faz isso eu n sei, mas se tá funcionando eu aceito
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venda> vendas;

    public Cliente() {}

    public Cliente(Long id, String nome, String email, String cpf, int telefone, List<Venda> vendas) {
        this.id = id;
        this.nome = nome; 
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.vendas = vendas;
    }

    public Long getId(){
        return id;  
    }    

    public String getNome(){
        return nome;    
    }
    
    public String getEmail(){
        return email;   
    }

    public String getCpf(){
        return cpf; 
    }

    public int getTelefone(){
        return telefone;    
    }

    public List<Venda> getVendas(){ 
        return vendas; 
    }

    public void setId(Long id){
        this.id = id;   
    }

    public void setNome(String nome){
        this.nome = nome;   
    }

    public void setEmail(String email){
        this.email = email; 
    }

    public void setCpf(String cpf){
        this.cpf = cpf; 
    }

    public void setTelefone(int telefone){
        this.telefone = telefone;   
    }

    public void setVendas(List<Venda> vendas){ 
        this.vendas = vendas; 
    }

}

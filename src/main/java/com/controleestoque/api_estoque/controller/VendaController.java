package com.controleestoque.api_estoque.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.controleestoque.api_estoque.classe.ReqVendaItens;
import com.controleestoque.api_estoque.classe.VendaItens;
import com.controleestoque.api_estoque.model.Cliente;
import com.controleestoque.api_estoque.model.Estoque;
import com.controleestoque.api_estoque.model.ItensVenda;
import com.controleestoque.api_estoque.repository.ClienteRepository;
import com.controleestoque.api_estoque.repository.EstoqueRepository;
import com.controleestoque.api_estoque.repository.VendaRepository;
import com.controleestoque.api_estoque.model.Produto;
import com.controleestoque.api_estoque.model.Venda;
import com.controleestoque.api_estoque.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;
    private final EstoqueRepository estoqueRepository;

    //ok, vou precisar da tabela cliente e itensVenda (pq aí eu já pego essa buceta dessa quantidade e id do produto)
    //ou eu vou tá criando pro itensVenda??
    //sei lá caralho, já tá um problema por si só aprender essa merda
    //talvez eu tenha que na verdade pegar os dados da tabela produto, aí eu requisitar quantidade e como um foda-se item separado

    //tenho que usar essa porcaria de <?> só poder retornar uma string no final em um caso específico
    //é foda
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createVenda(@RequestBody ReqVendaItens dados) {
        //pega o pensamento do pai: eu vou pedir só os ids, pra facilitar minha vida na hora de testar
        //aí depois eu uso um findById e armazeno
        //fiz a exata mesma merda no trabalho da Venturus

        int contador = 0;
        List<String> produtosFaltando = new ArrayList<>();
        
        //ok, n funcionou do jeito q eu queria, ent vou ter q fazer essa putaria
        Long id_cliente = dados.getIdCliente();
        Optional<Cliente> cliente = clienteRepository.findById(id_cliente);

        List<VendaItens> itens = dados.getItens();

        if((cliente==null)||(itens==null)||(cliente.isEmpty())||(itens.isEmpty())){
            return ResponseEntity.badRequest().build();
        }else{
            for (int i = 0; i < itens.size(); i++) {

                VendaItens item = itens.get(i);

                Optional<Produto> produto = produtoRepository.findById(item.getId_produto());

                if (produto.isEmpty()){
                    return ResponseEntity.notFound().build();
                }else{
                    if(item.getQuantidade()>produto.get().getEstoque().getQuantidade()){
                        contador++;
                        produtosFaltando.add(produto.get().getNome());
                    }
                }
            }

            if(contador<1){
                Venda venda = new Venda();
                venda.setCliente(cliente.get());

                List<ItensVenda> itensVenda = new ArrayList<>(); 

                for (int i = 0; i < itens.size(); i++) {

                    VendaItens item = itens.get(i);
                    Produto produto = produtoRepository.findById(item.getId_produto()).get();
                    Estoque estoque = produto.getEstoque();

                    ItensVenda item2 = new ItensVenda();
                    item2.setVenda(venda);
                    item2.setProduto(produto);
                    item2.setQuantidade(item.getQuantidade());
                    item2.setPreco(produto.getPreco()); 

                    itensVenda.add(item2);

                    estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
                    estoqueRepository.save(estoque);
                }

                venda.setItens(itensVenda);
                venda = vendaRepository.save(venda);

                return ResponseEntity.status(HttpStatus.CREATED).body(venda);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estoque indisponível para os seguintes produtos: " + produtosFaltando);
            }
        }
    }
}

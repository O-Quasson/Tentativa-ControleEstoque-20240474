package com.controleestoque.api_estoque.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.controleestoque.api_estoque.model.Cliente;
import com.controleestoque.api_estoque.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    //viado, essa porra tá tão na cara que é um ctrl+c ctrl+v dos controllers do fornecedor que chega a ser cômico
    //foda, eu nem sei como faz essa porra mesmo, vai ser assim mesmo
    //me recuso a pedir ajuda pra ia
    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
      return clienteRepository.findAll();
    }   

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCategoriaById(@PathVariable Long id) {
      return clienteRepository.findById(id) 
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    }   

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody Cliente cliente) {
      return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(
        @PathVariable Long id, @RequestBody Cliente clienteDetails) {
        return clienteRepository.findById(id)
            .map(cliente -> {
                cliente.setNome(clienteDetails.getNome());
                Cliente updatedCliente = clienteRepository.save(cliente);    
                return ResponseEntity.ok(updatedCliente);
                })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

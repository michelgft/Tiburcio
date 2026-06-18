package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editora {
    
    {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }
    
    private String id;
    private String nome;
    private String cnpj;
    
    public Editora(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | CNPJ: " + cnpj;
    }
}
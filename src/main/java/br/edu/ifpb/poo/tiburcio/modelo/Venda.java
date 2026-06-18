package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
public class Venda {
    
    {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }
    
    private final String id;
    private JogoTabuleiro jogo;
    private LocalDate dataVenda;
    private double valorVenda;
    private String comprador;
    
    public Venda(JogoTabuleiro jogo, LocalDate dataVenda, String comprador) {
        this.jogo = jogo;
        this.dataVenda = dataVenda;
        this.valorVenda = jogo.getPreco();
        this.comprador = comprador;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ID: " + id +
               " | Jogo: " + jogo.getNome() +
               " | Data: " + dataVenda.format(fmt) +
               " | Valor: R$ " + String.format("%.2f", valorVenda) +
               " | Comprador: " + comprador;
    }
}
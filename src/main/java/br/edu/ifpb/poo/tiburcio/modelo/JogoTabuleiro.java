package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import java.util.UUID;

@Data
public class JogoTabuleiro {
    
    public static final String TIPO_CARTA = "CARTA";
    public static final String TIPO_TABULEIRO = "TABULEIRO";
    public static final String STATUS_DISPONIVEL = "DISPONIVEL";
    public static final String STATUS_EMPRESTADO = "EMPRESTADO";
    public static final String STATUS_VENDIDO = "VENDIDO";
    
    {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }
    
    private final String id;
    private String nome;
    private String tipo;
    private Int quantidadePecas;
    private double preco;
    private String status = STATUS_DISPONIVEL;
    
    public JogoTabuleiro(String nome, String tipo, int quantidadePecas, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidadePecas = quantidadePecas;
        this.preco = preco;
    }
    
    public boolean isDisponivel() {
        return STATUS_DISPONIVEL.equals(status);
    }
    
    public boolean podeSerEmprestado() {
        return true;
    }
    
    public boolean podeSerVendido() {
        return true;
    }
    
    public String getTipoItem() {
        return "Jogo de Tabuleiro";
    }
    
    @Override
    public String toString() {
        String statusStr = status.equals(STATUS_DISPONIVEL) ? "DISPONIVEL" :
                          status.equals(STATUS_EMPRESTADO) ? "EMPRESTADO" : "VENDIDO";
        
        String tipoStr = tipo.equals(TIPO_CARTA) ? "Carta" : "Tabuleiro";
        
        return "ID: " + id + 
               " | Tipo: Jogo de Tabuleiro" +
               " | Nome: " + nome +
               " | Categoria: " + tipoStr +
               " | Pecas: " + quantidadePecas +
               " | Preco: R$ " + String.format("%.2f", preco) +
               " | Status: " + statusStr;
    }
}
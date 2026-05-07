package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import java.util.UUID;

@Data
public abstract class Item {
    
    // Constantes de status
    public static final String STATUS_DISPONIVEL = "DISPONVEL";
    public static final String STATUS_EMPRESTADO = "EMPRESTADO";
    public static final String STATUS_RESERVADO = "RESERVADO";
    public static final String STATUS_MANUTENCAO = "EM_MANUTENCAO";
    
    // Atributos -Lombok que gera getters
    private final String id = UUID.randomUUID().toString().substring(0, 8);
    private String titulo;
    private String editora;
    private int anoPublicacao;
    private String status = STATUS_DISPONIVEL;
    
    // Construtor -manual
    public Item(String titulo, String editora, int anoPublicacao) {
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }
    
    // Métodos de negócio
    public boolean isDisponivel() {
        return STATUS_DISPONIVEL.equals(status);
    }
    
    // Métodos abstratos 
    public abstract String getCodigo();
    public abstract String getTipoItem();
    
    @Override
    public String toString() {
        String statusStr = status.equals(STATUS_DISPONIVEL) ? "DISPONIVEL" :
                          status.equals(STATUS_EMPRESTADO) ? "EMPRESTADO" :
                          status.equals(STATUS_RESERVADO) ? "RESERVADO" : "EM MANUTENCAO";
        
        return "ID: " + id + " | Tipo: " + getTipoItem() + 
               " | Titulo: " + titulo + " | Editora: " + editora + 
               " | Status: " + statusStr;
    }
}
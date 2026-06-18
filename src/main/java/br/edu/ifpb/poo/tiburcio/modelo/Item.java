package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import java.util.UUID;

@Data
public abstract class Item {
    
    public static final String STATUS_DISPONIVEL = "DISPONIVEL";
    public static final String STATUS_EMPRESTADO = "EMPRESTADO";
    public static final String STATUS_RESERVADO = "RESERVADO";
    public static final String STATUS_MANUTENCAO = "EM_MANUTENCAO";
    
    {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }
    
    protected final String id;
    protected String titulo;
    protected String autor;
    protected int anoPublicacao;
    protected String status = STATUS_DISPONIVEL;
    
    public Item(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }
    
    public boolean isDisponivel() {
        return STATUS_DISPONIVEL.equals(status);
    }
    
    public abstract boolean podeSerEmprestado();
    public abstract String getCodigo();
    public abstract String getTipoItem();
    
    @Override
    public String toString() {
        String statusStr = status.equals(STATUS_DISPONIVEL) ? "DISPONIVEL" :
                          status.equals(STATUS_EMPRESTADO) ? "EMPRESTADO" :
                          status.equals(STATUS_RESERVADO) ? "RESERVADO" : "EM MANUTENCAO";
        
        return "ID: " + id + 
               " | Tipo: " + getTipoItem() + 
               " | Titulo: " + titulo +
               " | Autor: " + autor +
               " | Ano: " + anoPublicacao +
               " | Status: " + statusStr;
    }
}
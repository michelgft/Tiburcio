package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Livro extends Item {
    
    protected String isbn;
    protected Editora editora;
    protected int edicao;
    protected String genero;
    
    public Livro(String isbn, String titulo, String autor, Editora editora,
                 int anoPublicacao, int edicao, String genero) {
        super(titulo, autor, anoPublicacao);
        this.isbn = isbn;
        this.editora = editora;
        this.edicao = edicao;
        this.genero = genero;
    }
    
    @Override
    public String getCodigo() {
        return isbn;
    }
    
    @Override
    public String getTipoItem() {
        return "Livro";
    }
    
    @Override
    public boolean podeSerEmprestado() {
        return true;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               " | ISBN: " + isbn + 
               " | Editora: " + (editora != null ? editora.getNome() : "N/A") +
               " | Edicao: " + edicao +
               " | Genero: " + genero;
    }
}
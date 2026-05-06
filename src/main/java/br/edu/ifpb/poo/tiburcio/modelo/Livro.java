package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Livro extends Item {
    
    private String isbn;
    private String autores;
    private int edicao;
    private String genero;
    private int numPaginas;
    private String sinopse;
    
    public Livro(String isbn, String titulo, String autores, String editora,
                 int anoPublicacao, int edicao, String genero, int numPaginas, String sinopse) {
        super(titulo, editora, anoPublicacao);
        this.isbn = isbn;
        this.autores = autores;
        this.edicao = edicao;
        this.genero = genero;
        this.numPaginas = numPaginas;
        this.sinopse = sinopse;
    }
    
    @Override
    public String getCodigo() {
        return isbn;
    }
    
    @Override
    public String getTipoItem() {
        return "Livro";
    }
}
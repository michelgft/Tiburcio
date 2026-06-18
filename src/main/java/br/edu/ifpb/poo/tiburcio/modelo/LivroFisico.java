package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LivroFisico extends Livro {
    
    private int numPaginas;
    private String sinopse;
    
    public LivroFisico(String isbn, String titulo, String autor, Editora editora,
                       int anoPublicacao, int edicao, String genero,
                       int numPaginas, String sinopse) {
        super(isbn, titulo, autor, editora, anoPublicacao, edicao, genero);
        this.numPaginas = numPaginas;
        this.sinopse = sinopse;
    }
    
    @Override
    public String getTipoItem() {
        return "Livro Fisico";
    }
    
    @Override
    public String toString() {
        String sinopseAbreviada = sinopse.length() > 50 ? sinopse.substring(0, 50) + "..." : sinopse;
        return super.toString() + 
               " | Paginas: " + numPaginas +
               " | Sinopse: " + sinopseAbreviada;
    }
}
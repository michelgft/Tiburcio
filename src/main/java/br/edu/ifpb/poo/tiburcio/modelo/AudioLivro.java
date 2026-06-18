package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AudioLivro extends Livro {
    
    private int duracaoMinutos;
    private String narrador;
    
    public AudioLivro(String isbn, String titulo, String autor, Editora editora,
                      int anoPublicacao, int edicao, String genero,
                      int duracaoMinutos, String narrador) {
        super(isbn, titulo, autor, editora, anoPublicacao, edicao, genero);
        this.duracaoMinutos = duracaoMinutos;
        this.narrador = narrador;
    }
    
    @Override
    public String getTipoItem() {
        return "Audio Livro";
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               " | Duracao: " + duracaoMinutos + " min" +
               " | Narrador: " + narrador;
    }
}
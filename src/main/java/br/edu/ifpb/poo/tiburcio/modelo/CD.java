package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CD extends Item {
    
    private String isbn;
    private Editora editora;
    private int numFaixas;
    private String artista;
    
    public CD(String isbn, String titulo, String artista, Editora editora,
              int anoPublicacao, int numFaixas) {
        super(titulo, artista, anoPublicacao);
        this.isbn = isbn;
        this.artista = artista;
        this.editora = editora;
        this.numFaixas = numFaixas;
    }
    
    @Override
    public String getCodigo() {
        return isbn;
    }
    
    @Override
    public String getTipoItem() {
        return "CD";
    }
    
    @Override
    public boolean podeSerEmprestado() {
        return false;  // CDs nao podem ser emprestados
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               " | ISBN: " + isbn + 
               " | Artista: " + artista +
               " | Editora: " + (editora != null ? editora.getNome() : "N/A") +
               " | Faixas: " + numFaixas;
    }
}
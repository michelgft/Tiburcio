package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Revista extends Item {
    
    private String issn;
    private Editora editora;
    private int volume;
    private int numero;
    private String dataPublicacao;
    
    public Revista(String issn, String titulo, String autor, Editora editora,
                   int volume, int numero, String dataPublicacao) {
        super(titulo, autor, 0);
        this.issn = issn;
        this.editora = editora;
        this.volume = volume;
        this.numero = numero;
        this.dataPublicacao = dataPublicacao;
    }
    
    @Override
    public String getCodigo() {
        return issn;
    }
    
    @Override
    public String getTipoItem() {
        return "Revista";
    }
    
    @Override
    public boolean podeSerEmprestado() {
        return true;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               " | ISSN: " + issn + 
               " | Editora: " + (editora != null ? editora.getNome() : "N/A") +
               " | Volume: " + volume + 
               " | Numero: " + numero + 
               " | Data: " + dataPublicacao;
    }
}
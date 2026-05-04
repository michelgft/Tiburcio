package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Revista extends Item {
    
    private String issn;
    private int volume;
    private int numero;
    private String dataPublicacao;
    
    public Revista(String issn, String titulo, int volume, int numero, String editora, String dataPublicacao) {
        super(titulo, editora, 0);
        this.issn = issn;
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
}
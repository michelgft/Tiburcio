package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;

@Data
public class JogoTabuleiro extends Item {

    public static final String TIPO_CARTA = "CARTA";
    public static final String TIPO_TABULEIRO = "TABULEIRO";
    public static final String STATUS_VENDIDO = "VENDIDO";

    private String tipo;
    private int quantidadePecas;
    private double preco;

    public JogoTabuleiro(String nome, String tipo, int quantidadePecas, double preco) {
        super(nome, "N/A", 0);
        this.tipo = tipo;
        this.quantidadePecas = quantidadePecas;
        this.preco = preco;
    }

    @Override
    public boolean podeSerEmprestado() {
        return true;
    }

    public boolean podeSerVendido() {
        return true;
    }

    @Override
    public String getCodigo() {
        return getId();
    }

    @Override
    public String getTipoItem() {
        return "Jogo de Tabuleiro";
    }
    
    public String getNome() {
    return getTitulo();
    }
    @Override
    public String toString() {

        String statusStr;

        if (status.equals(STATUS_DISPONIVEL)) {
            statusStr = "DISPONIVEL";
        } else if (status.equals(STATUS_EMPRESTADO)) {
            statusStr = "EMPRESTADO";
        } else if (status.equals(STATUS_VENDIDO)) {
            statusStr = "VENDIDO";
        } else {
            statusStr = status;
        }

        String tipoStr =
                tipo.equals(TIPO_CARTA) ? "Carta" : "Tabuleiro";

        return "ID: " + getId()
                + " | Tipo: Jogo de Tabuleiro"
                + " | Nome: " + getTitulo()
                + " | Categoria: " + tipoStr
                + " | Pecas: " + quantidadePecas
                + " | Preco: R$ " + String.format("%.2f", preco)
                + " | Status: " + statusStr;
    }
}
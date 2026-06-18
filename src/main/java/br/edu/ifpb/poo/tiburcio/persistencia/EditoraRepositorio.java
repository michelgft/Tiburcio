package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
public class Emprestimo {
    
    public static final String STATUS_ATIVO = "ATIVO";
    public static final String STATUS_FINALIZADO = "FINALIZADO";
    
    {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }
    
    private final String id;
    private Usuario usuario;
    private Object item;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private String status = STATUS_ATIVO;
    private double multaCalculada = 0.0;
    private boolean multaPaga = false;
    
    public Emprestimo(Usuario usuario, Object item, LocalDate dataEmprestimo, int prazo) {
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(prazo);
    }
    
    public boolean isEmAtraso() {
        return STATUS_ATIVO.equals(status) && LocalDate.now().isAfter(dataPrevistaDevolucao);
    }
    
    private double calcularMulta(LocalDate dataDevolucao) {
        if (dataDevolucao.isBefore(dataPrevistaDevolucao) || 
            dataDevolucao.isEqual(dataPrevistaDevolucao)) {
            return 0.0;
        }
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucao);
        return diasAtraso * usuario.getMultaPorDia();
    }
    
    public double registrarDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        this.multaCalculada = calcularMulta(dataDevolucao);
        this.status = STATUS_FINALIZADO;
        
        if (item instanceof Item) {
            ((Item) item).setStatus(Item.STATUS_DISPONIVEL);
        } else if (item instanceof JogoTabuleiro) {
            ((JogoTabuleiro) item).setStatus(JogoTabuleiro.STATUS_DISPONIVEL);
        }
        
        return multaCalculada;
    }
    
    public void pagarMulta() {
        this.multaPaga = true;
    }
    
    public String getItemTitulo() {
        if (item instanceof Item) {
            return ((Item) item).getTitulo();
        } else if (item instanceof JogoTabuleiro) {
            return ((JogoTabuleiro) item).getNome();
        }
        return "Desconhecido";
    }
    
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String info = "ID: " + id + 
                      " | Usuario: " + usuario.getNome() + 
                      " | Item: " + getItemTitulo() + 
                      " | Data: " + dataEmprestimo.format(fmt) +
                      " | Prevista: " + dataPrevistaDevolucao.format(fmt) + 
                      " | Status: " + status;
        if (dataDevolucao != null) {
            info += " | Devolucao: " + dataDevolucao.format(fmt) + 
                    " | Multa: R$ " + String.format("%.2f", multaCalculada);
        }
        if (isEmAtraso()) info += " (EM ATRASO!)";
        return info;
    }
}
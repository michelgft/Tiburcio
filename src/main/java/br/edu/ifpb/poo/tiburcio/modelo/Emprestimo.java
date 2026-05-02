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
    
    private final String id = UUID.randomUUID().toString().substring(0, 8);
    private final Usuario usuario;
    private final Item item;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private String status = STATUS_ATIVO;
    private double multaCalculada = 0.0;
    private boolean multaPaga = false;
    
    public Emprestimo(Usuario usuario, Item item, LocalDate dataEmprestimo, int prazo) {
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
        item.setStatus(Item.STATUS_DISPONIVEL);
        return multaCalculada;
    }
    
    public void pagarMulta() {
        this.multaPaga = true;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String info = "ID: " + id + " | Usuario: " + usuario.getNome() + 
                      " | Item: " + item.getTitulo() + " | Data: " + dataEmprestimo.format(fmt) +
                      " | Prevista: " + dataPrevistaDevolucao.format(fmt) + " | Status: " + status;
        if (dataDevolucao != null) {
            info += " | Devolucao: " + dataDevolucao.format(fmt) + 
                    " | Multa: R$ " + String.format("%.2f", multaCalculada);
        }
        if (isEmAtraso()) info += " (EM ATRASO!)";
        return info;
    }
}
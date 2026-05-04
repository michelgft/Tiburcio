package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Usuario {
    
    // Constantes de tipo de categorias de usuários
    public static final String TIPO_GRADUACAO = "GRADUACAO";
    public static final String TIPO_PROFESSOR = "PROFESSOR";
    public static final String TIPO_POS_GRADUACAO = "POS_GRADUACAO";
    public static final String TIPO_FUNCIONARIO = "FUNCIONARIO";
    
    // Atributos
    private final String id = UUID.randomUUID().toString().substring(0, 8);
    private final String nome;
    private final String email;
    private final String tipo;
    private boolean ativo = true;
    private double multaPendente = 0.0;
    
    //Métodos de negócio
    public int getLimiteEmprestimos() {
        if (tipo.equals(TIPO_GRADUACAO)) return 3;
        if (tipo.equals(TIPO_PROFESSOR) || tipo.equals(TIPO_POS_GRADUACAO)) return 5;
        return 2;
    }
    
    public int getPrazoPadrao() {
        if (tipo.equals(TIPO_GRADUACAO)) return 7;
        if (tipo.equals(TIPO_PROFESSOR) || tipo.equals(TIPO_POS_GRADUACAO)) return 14;
        return 10;
    }
    
    public double getMultaPorDia() {
        if (tipo.equals(TIPO_GRADUACAO)) return 2.00;
        if (tipo.equals(TIPO_PROFESSOR) || tipo.equals(TIPO_POS_GRADUACAO)) return 1.00;
        return 1.50;
    }
    
    public boolean isBloqueado() {
        return !ativo || multaPendente > 0;
    }
    
    public void adicionarMulta(double valor) {
        this.multaPendente += valor;
    }
    
    public void pagarMulta() {
        this.multaPendente = 0.0;
    }
}
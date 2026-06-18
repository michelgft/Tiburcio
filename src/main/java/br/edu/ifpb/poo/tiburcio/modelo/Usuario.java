package br.edu.ifpb.poo.tiburcio.modelo;

import lombok.Data;
import java.util.UUID;

@Data
public class Usuario {
    
    public static final String TIPO_ALUNO = "ALUNO";
    public static final String TIPO_PROFESSOR = "PROFESSOR";
    public static final String TIPO_POS_GRADUACAO = "POS_GRADUACAO";
    public static final String TIPO_FUNCIONARIO = "FUNCIONARIO";
    
    {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }
    
    private final String id;
    private String nome;
    private String email;
    private String tipo;
    private boolean ativo = true;
    private double multaPendente = 0.0;
    
    public Usuario(String nome, String email, String tipo) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }
    
    public int getLimiteEmprestimos() {
        if (TIPO_ALUNO.equals(tipo)) return 3;
        if (TIPO_PROFESSOR.equals(tipo) || TIPO_POS_GRADUACAO.equals(tipo)) return 5;
        return 2;
    }
    
    public int getPrazoPadrao() {
        if (TIPO_ALUNO.equals(tipo)) return 7;
        if (TIPO_PROFESSOR.equals(tipo) || TIPO_POS_GRADUACAO.equals(tipo)) return 14;
        return 10;
    }
    
    public double getMultaPorDia() {
        if (TIPO_ALUNO.equals(tipo)) return 2.00;
        if (TIPO_PROFESSOR.equals(tipo) || TIPO_POS_GRADUACAO.equals(tipo)) return 1.00;
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
    
    @Override
    public String toString() {
        String tipoStr;
        if (TIPO_ALUNO.equals(tipo)) tipoStr = "Aluno";
        else if (TIPO_PROFESSOR.equals(tipo)) tipoStr = "Professor";
        else if (TIPO_POS_GRADUACAO.equals(tipo)) tipoStr = "Pos-Graduacao";
        else tipoStr = "Funcionario";
        
        return "ID: " + id + 
               " | Nome: " + nome + 
               " | Email: " + email + 
               " | Tipo: " + tipoStr + 
               " | Limite: " + getLimiteEmprestimos() +
               " | Multa: R$ " + String.format("%.2f", multaPendente);
    }
}
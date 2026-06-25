package br.edu.ifpb.poo.tiburcio.ui;

import br.edu.ifpb.poo.tiburcio.modelo.Emprestimo;
import br.edu.ifpb.poo.tiburcio.modelo.Venda;
import java.util.List;

public class EmprestimoUI {
    
    private Console console;
    
    public EmprestimoUI(Console console) {
        this.console = console;
    }
    
    public void exibirMenuOperacoes() {
        Menu menu = new Menu(
            "OPERAÇÕES",
            new String[]{"Emprestar", "Devolver", "Voltar"},
            "Opção: ",
            console
        );
        menu.exibir();
    }
    
    public void exibirMenuConsultas() {
        Menu menu = new Menu(
            "CONSULTAS",
            new String[]{
                "Empréstimos Abertos", "Empréstimos em Atraso",
                "Histórico do Usuário", "Todos os Empréstimos", "Voltar"
            },
            "Opção: ",
            console
        );
        menu.exibir();
    }
    
    public void exibirMenuVendas() {
        Menu menu = new Menu(
            "VENDAS",
            new String[]{"Vender Jogo", "Listar Vendas", "Voltar"},
            "Opção: ",
            console
        );
        menu.exibir();
    }
    
    public void exibirDadosEmprestimo() {
        console.println("\n-- EMPRÉSTIMO --");
    }
    
    public void exibirDadosDevolucao() {
        console.println("\n-- DEVOLUÇÃO --");
    }
    
    public String lerIdEmprestimo() {
        console.print("ID do Empréstimo: ");
        return console.nextLine();
    }
    
    public void exibirEmprestimoRealizado(String data) {
        console.println(Cores.VERDE, "Empréstimo realizado! Devolução prevista: " + data);
    }
    
    public void exibirDevolucaoSemMulta() {
        console.println(Cores.VERDE, "Devolução realizada sem multa!");
    }
    
    public void exibirDevolucaoComMulta(double multa) {
        console.printf(Cores.VERMELHO, "Devolução com multa de R$ %.2f\n\n", multa);
    }
    
    public void exibirEmprestimos(List<Emprestimo> emprestimos, String titulo) {
        console.println("\n--- " + titulo + " ---");
        if (emprestimos.isEmpty()) {
            console.println("Nenhum empréstimo encontrado.");
            return;
        }
        for (Emprestimo e : emprestimos) {
            console.println(e.toString());
        }
    }
    
    public void exibirVendas(List<Venda> vendas) {
        console.println("\n--- VENDAS REALIZADAS ---");
        if (vendas.isEmpty()) {
            console.println("Nenhuma venda registrada.");
            return;
        }
        for (Venda v : vendas) {
            console.println(v.toString());
        }
    }
    
    public void exibirMensagemErro(String mensagem) {
        console.println(Cores.VERMELHO, mensagem);
    }
    
    public void exibirMensagemSucesso(String mensagem) {
        console.println(Cores.VERDE, mensagem);
    }
    public int lerOpcao() {
        return console.nextInt();
    }

    public void limparTela() {
        console.clrscr();
    }
    public String lerIdUsuario() {
        console.print("ID do usuario: ");
        return console.nextLine();
    }
    public String lerIdItem() {
        console.print("ID do item: ");
        return console.nextLine();
    }
    public String lerIdJogo() {
        console.print("ID do jogo: ");
        return console.nextLine();
    }
    public String lerNomeComprador() {
        console.print("Nome do comprador: ");
        return console.nextLine();
    }
    public void exibirVendaRealizada(double preco) {
        console.printf(Cores.VERDE, "Venda realizada! Valor: R$ %.2f\n\n", preco);
    }
}
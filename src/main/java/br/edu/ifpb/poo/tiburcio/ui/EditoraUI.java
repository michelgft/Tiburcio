package br.edu.ifpb.poo.tiburcio.ui;

import br.edu.ifpb.poo.tiburcio.modelo.Editora;
import java.util.List;

public class EditoraUI {
    
    private Console console;
    
    public EditoraUI(Console console) {
        this.console = console;
    }
    
    public void exibirMenu() {
        Menu menu = new Menu(
            "EDITORAS",
            new String[]{"Cadastrar Editora", "Listar Editoras", "Voltar"},
            "Opção: ",
            console
        );
        menu.exibir();
    }
    
    public Editora lerEditora() {
        console.println("\n--- CADASTRO EDITORA ---");
        console.print("Nome: ");
        String nome = console.nextLine();
        console.print("CNPJ: ");
        String cnpj = console.nextLine();
        return new Editora(nome, cnpj);
    }
    
    public void exibirEditoras(List<Editora> editoras) {
        console.println("\n--- EDITORAS ---");
        if (editoras.isEmpty()) {
            console.println("Nenhuma editora cadastrada.");
            return;
        }
        for (Editora e : editoras) {
            console.println(e.toString());
        }
    }
    
    public String lerNomeEditora() {
        console.print("Nome da Editora: ");
        return console.nextLine();
    }
    
    public void exibirEditoraNaoEncontrada() {
        console.println(Cores.VERMELHO, "Editora não encontrada! Cadastre primeiro.");
    }
    
    // ========== MÉTODOS ADICIONADOS PARA O CONTROLLER ==========
    
    public int lerOpcao() {
        return console.nextInt();
    }
    
    public void limparTela() {
        console.clrscr();
    }
    
    public void exibirMensagemSucesso(String mensagem) {
        console.println(Cores.VERDE, mensagem);
    }
    
    public void exibirMensagemErro(String mensagem) {
        console.println(Cores.VERMELHO, mensagem);
    }
}
package br.edu.ifpb.poo.tiburcio.ui;

import br.edu.ifpb.poo.tiburcio.modelo.Usuario;
import java.util.List;

public class UsuarioUI {
    
    private Console console;
    
    public UsuarioUI(Console console) {
        this.console = console;
    }
    
    public void exibirMenu() {
        Menu menu = new Menu(
            "USUÁRIOS",
            new String[]{"Cadastrar Usuário", "Listar Usuários", "Remover Usuário", "Voltar"},
            "Opção: ",
            console
        );
        menu.exibir();
    }
    
    public Usuario lerUsuario() {
        console.println("\n--- CADASTRO USUARIO ---");
        console.print("Nome: ");
        String nome = console.nextLine();
        console.print("Email: ");
        String email = console.nextLine();
        console.println("1-Aluno 2-Professor 3-PosGraduacao 4-Funcionario");
        console.print("Categoria: ");
        int cat = console.nextInt();
        
        String tipo;
        if (cat == 2) tipo = Usuario.TIPO_PROFESSOR;
        else if (cat == 3) tipo = Usuario.TIPO_POS_GRADUACAO;
        else if (cat == 4) tipo = Usuario.TIPO_FUNCIONARIO;
        else tipo = Usuario.TIPO_ALUNO;
        
        return new Usuario(nome, email, tipo);
    }
    
    public void exibirUsuarios(List<Usuario> usuarios) {
        console.println("\n--- USUARIOS ---");
        if (usuarios.isEmpty()) {
            console.println("Nenhum usuario cadastrado.");
            return;
        }
        for (Usuario u : usuarios) {
            console.println(u.toString());
        }
    }
    
    public String lerIdUsuario() {
        console.print("ID do usuario: ");
        return console.nextLine();
    }
    
    public void exibirUsuarioNaoEncontrado() {
        console.println(Cores.VERMELHO, "Usuario não encontrado!");
    }
    
    public void exibirUsuarioRemovido() {
        console.println(Cores.VERDE, "Usuario removido com sucesso!");
    }
}
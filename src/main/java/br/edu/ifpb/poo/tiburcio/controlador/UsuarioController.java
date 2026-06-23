package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.modelo.Usuario;
import br.edu.ifpb.poo.tiburcio.persistencia.UsuarioRepositorio;
import br.edu.ifpb.poo.tiburcio.ui.UsuarioUI;

public class UsuarioController {
    
    private UsuarioUI ui;
    private UsuarioRepositorio repositorio;
    
    public UsuarioController(UsuarioUI ui, UsuarioRepositorio repositorio) {
        this.ui = ui;
        this.repositorio = repositorio;
    }
    
    public void menu() {
        int opcao;
        do {
            ui.exibirMenu();
            opcao = ui.lerOpcao();
            ui.limparTela();
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> remover();
            }
        } while (opcao != 4);
    }
    
    private void cadastrar() {
        Usuario usuario = ui.lerUsuario();
        if (repositorio.salvar(usuario)) {
            ui.exibirMensagemSucesso("Usuário cadastrado com sucesso!");
        } else {
            ui.exibirMensagemErro("Erro ao cadastrar usuário!");
        }
    }
    
    private void listar() {
        ui.exibirUsuarios(repositorio.buscarTodos());
    }
    
    private void remover() {
        String id = ui.lerIdUsuario();
        if (repositorio.excluir(id)) {
            ui.exibirUsuarioRemovido();
        } else {
            ui.exibirUsuarioNaoEncontrado();
        }
    }
    
    public Usuario buscarPorId(String id) {
        return repositorio.buscar(id);
    }
    
    public boolean atualizar(Usuario usuario) {
        return repositorio.atualizar(usuario);
    }
}
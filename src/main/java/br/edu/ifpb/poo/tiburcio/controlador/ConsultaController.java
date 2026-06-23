package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.modelo.Emprestimo;
import br.edu.ifpb.poo.tiburcio.modelo.Usuario;
import br.edu.ifpb.poo.tiburcio.ui.EmprestimoUI;

import java.util.ArrayList;
import java.util.List;

public class ConsultaController {
    
    private EmprestimoUI ui;
    private EmprestimoController emprestimoController;
    private UsuarioController usuarioController;
    
    public ConsultaController(EmprestimoUI ui, EmprestimoController emprestimoController,
                              UsuarioController usuarioController) {
        this.ui = ui;
        this.emprestimoController = emprestimoController;
        this.usuarioController = usuarioController;
    }
    
    public void menu() {
        int opcao;
        do {
            ui.exibirMenuConsultas();
            opcao = ui.lerOpcao();
            ui.limparTela();
            switch (opcao) {
                case 1 -> listarAbertos();
                case 2 -> listarAtraso();
                case 3 -> historicoUsuario();
                case 4 -> listarTodos();
            }
        } while (opcao != 5);
    }
    
    private void listarAbertos() {
        List<Emprestimo> todos = emprestimoController.listarTodos();
        List<Emprestimo> abertos = new ArrayList<>();
        for (Emprestimo e : todos) {
            if (e.getStatus().equals(Emprestimo.STATUS_ATIVO)) {
                abertos.add(e);
            }
        }
        ui.exibirEmprestimos(abertos, "EMPRÉSTIMOS EM ABERTO");
    }
    
    private void listarAtraso() {
        List<Emprestimo> todos = emprestimoController.listarTodos();
        List<Emprestimo> atraso = new ArrayList<>();
        for (Emprestimo e : todos) {
            if (e.isEmAtraso() && e.getStatus().equals(Emprestimo.STATUS_ATIVO)) {
                atraso.add(e);
            }
        }
        ui.exibirEmprestimos(atraso, "EMPRÉSTIMOS EM ATRASO");
    }
    
    private void historicoUsuario() {
        String id = ui.lerIdUsuario();
        Usuario u = usuarioController.buscarPorId(id);
        
        if (u == null) {
            ui.exibirMensagemErro("Usuário não encontrado!");
            return;
        }
        
        List<Emprestimo> historico = emprestimoController.listarPorUsuario(id);
        ui.exibirEmprestimos(historico, "HISTÓRICO DO USUÁRIO: " + u.getNome());
    }
    
    private void listarTodos() {
        List<Emprestimo> todos = emprestimoController.listarTodos();
        ui.exibirEmprestimos(todos, "TODOS OS EMPRÉSTIMOS");
    }
}
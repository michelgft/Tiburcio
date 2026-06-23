package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.modelo.JogoTabuleiro;
import br.edu.ifpb.poo.tiburcio.persistencia.JogoRepositorio;
import br.edu.ifpb.poo.tiburcio.ui.JogoUI;

public class JogoController {
    
    private JogoUI ui;
    private JogoRepositorio repositorio;
    
    public JogoController(JogoUI ui, JogoRepositorio repositorio) {
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
        JogoTabuleiro jogo = ui.lerJogo();
        if (repositorio.salvar(jogo)) {
            ui.exibirMensagemSucesso("Jogo cadastrado com sucesso!");
        } else {
            ui.exibirMensagemErro("Erro ao cadastrar jogo!");
        }
    }
    
    private void listar() {
        ui.exibirJogos(repositorio.buscarTodos());
    }
    
    private void remover() {
        String id = ui.lerIdJogo();
        if (repositorio.excluir(id)) {
            ui.exibirJogoRemovido();
        } else {
            ui.exibirJogoNaoEncontrado();
        }
    }
    
    public JogoTabuleiro buscarPorId(String id) {
        return repositorio.buscar(id);
    }
    
    public boolean atualizar(JogoTabuleiro jogo) {
        return repositorio.atualizar(jogo);
    }
}
package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.modelo.JogoTabuleiro;
import br.edu.ifpb.poo.tiburcio.modelo.Venda;
import br.edu.ifpb.poo.tiburcio.persistencia.VendaRepositorio;
import br.edu.ifpb.poo.tiburcio.ui.EmprestimoUI;

import java.time.LocalDate;

public class VendaController {
    
    private EmprestimoUI ui;
    private VendaRepositorio repositorio;
    private JogoController jogoController;
    
    public VendaController(EmprestimoUI ui, VendaRepositorio repositorio, JogoController jogoController) {
        this.ui = ui;
        this.repositorio = repositorio;
        this.jogoController = jogoController;
    }
    
    public void menu() {
        int opcao;
        do {
            ui.exibirMenuVendas();
            opcao = ui.lerOpcao();
            ui.limparTela();
            switch (opcao) {
                case 1 -> vender();
                case 2 -> listar();
            }
        } while (opcao != 3);
    }
    
    private void vender() {
        String id = ui.lerIdJogo();
        JogoTabuleiro jogo = jogoController.buscarPorId(id);
        
        if (jogo == null) {
            ui.exibirMensagemErro("Jogo não encontrado!");
            return;
        }
        
        if (!jogo.isDisponivel()) {
            ui.exibirMensagemErro("Jogo não está disponível para venda!");
            return;
        }
        
        String comprador = ui.lerNomeComprador();
        Venda venda = new Venda(jogo, LocalDate.now(), comprador);
        
        repositorio.salvar(venda);
        jogo.setStatus(JogoTabuleiro.STATUS_VENDIDO);
        jogoController.atualizar(jogo);
        
        ui.exibirVendaRealizada(jogo.getPreco());
    }
    
    private void listar() {
        ui.exibirVendas(repositorio.buscarTodos());
    }
}
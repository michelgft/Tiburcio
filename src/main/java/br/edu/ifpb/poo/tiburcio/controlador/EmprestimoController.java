package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.modelo.*;
import br.edu.ifpb.poo.tiburcio.persistencia.EmprestimoRepositorio;
import br.edu.ifpb.poo.tiburcio.ui.EmprestimoUI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoController {
    
    private EmprestimoUI ui;
    private EmprestimoRepositorio repositorio;
    private UsuarioController usuarioController;
    private ItemController itemController;
    private JogoController jogoController;
    
    public EmprestimoController(EmprestimoUI ui, EmprestimoRepositorio repositorio,
                                UsuarioController usuarioController,
                                ItemController itemController,
                                JogoController jogoController) {
        this.ui = ui;
        this.repositorio = repositorio;
        this.usuarioController = usuarioController;
        this.itemController = itemController;
        this.jogoController = jogoController;
    }
    
    public void menu() {
        int opcao;
        do {
            ui.exibirMenuOperacoes();
            opcao = ui.lerOpcao();
            ui.limparTela();
            switch (opcao) {
                case 1 -> realizarEmprestimo();
                case 2 -> registrarDevolucao();
            }
        } while (opcao != 3);
    }
    
    private void realizarEmprestimo() {
        ui.exibirDadosEmprestimo();
        
        String idU = ui.lerIdUsuario();
        String idI = ui.lerIdItem();
        
        Usuario u = usuarioController.buscarPorId(idU);
        if (u == null) {
            ui.exibirMensagemErro("Usuário não encontrado!");
            return;
        }
        
        Item item = itemController.buscarPorId(idI);
        JogoTabuleiro jogo = null;
        Object itemObj = null;
        
        if (item != null) {
            if (!item.podeSerEmprestado()) {
                ui.exibirMensagemErro("Este item não pode ser emprestado!");
                return;
            }
            if (!item.isDisponivel()) {
                ui.exibirMensagemErro("Item não disponível!");
                return;
            }
            itemObj = item;
        } else {
            jogo = jogoController.buscarPorId(idI);
            if (jogo == null) {
                ui.exibirMensagemErro("Item/Jogo não encontrado!");
                return;
            }
            if (!jogo.isDisponivel()) {
                ui.exibirMensagemErro("Jogo não disponível!");
                return;
            }
            itemObj = jogo;
        }
        
        if (!u.isAtivo()) {
            ui.exibirMensagemErro("Usuário inativo!");
            return;
        }
        
        if (u.getMultaPendente() > 0) {
            ui.exibirMensagemErro("Usuário com multa pendente!");
            return;
        }
        
        if (usuarioTemAtraso(idU)) {
            ui.exibirMensagemErro("Usuário com empréstimo em atraso!");
            return;
        }
        
        int ativos = contarEmprestimosAtivos(idU);
        if (ativos >= u.getLimiteEmprestimos()) {
            ui.exibirMensagemErro("Limite de empréstimos atingido!");
            return;
        }
        
        int prazo = u.getPrazoPadrao();
        
        Emprestimo emprestimo = new Emprestimo(u, itemObj, LocalDate.now(), prazo);
        repositorio.salvar(emprestimo);
        
        if (item != null) {
            item.setStatus(Item.STATUS_EMPRESTADO);
            itemController.atualizar(item);
        } else if (jogo != null) {
            jogo.setStatus(JogoTabuleiro.STATUS_EMPRESTADO);
            jogoController.atualizar(jogo);
        }
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ui.exibirEmprestimoRealizado(LocalDate.now().plusDays(prazo).format(fmt));
    }
    
    private void registrarDevolucao() {
        ui.exibirDadosDevolucao();
        
        String id = ui.lerIdEmprestimo();
        Emprestimo emp = repositorio.buscar(id);
        
        if (emp == null) {
            ui.exibirMensagemErro("Empréstimo não encontrado!");
            return;
        }
        
        if (!emp.getStatus().equals(Emprestimo.STATUS_ATIVO)) {
            ui.exibirMensagemErro("Empréstimo já finalizado!");
            return;
        }
        
        double multa = emp.registrarDevolucao(LocalDate.now());
        repositorio.atualizar(emp);
        
        if (multa > 0) {
            emp.getUsuario().adicionarMulta(multa);
            usuarioController.atualizar(emp.getUsuario());
            ui.exibirDevolucaoComMulta(multa);
        } else {
            ui.exibirDevolucaoSemMulta();
        }
    }
    
    private int contarEmprestimosAtivos(String idUsuario) {
        List<Emprestimo> todos = repositorio.buscarTodos();
        int count = 0;
        for (Emprestimo e : todos) {
            if (e.getUsuario().getId().equals(idUsuario) && 
                e.getStatus().equals(Emprestimo.STATUS_ATIVO)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean usuarioTemAtraso(String idUsuario) {
        List<Emprestimo> todos = repositorio.buscarTodos();
        for (Emprestimo e : todos) {
            if (e.getUsuario().getId().equals(idUsuario) &&
                e.isEmAtraso() &&
                e.getStatus().equals(Emprestimo.STATUS_ATIVO)) {
                return true;
            }
        }
        return false;
    }
    
    public List<Emprestimo> listarTodos() {
        return repositorio.buscarTodos();
    }
    
    public List<Emprestimo> listarPorUsuario(String idUsuario) {
        return repositorio.buscarPorUsuario(idUsuario);
    }
}
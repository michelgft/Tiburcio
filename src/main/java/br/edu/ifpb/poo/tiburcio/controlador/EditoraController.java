package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.modelo.Editora;
import br.edu.ifpb.poo.tiburcio.persistencia.EditoraRepositorio;
import br.edu.ifpb.poo.tiburcio.ui.EditoraUI;

public class EditoraController {
    
    private EditoraUI ui;
    private EditoraRepositorio repositorio;
    
    public EditoraController(EditoraUI ui, EditoraRepositorio repositorio) {
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
            }
        } while (opcao != 3);
    }
    
    private void cadastrar() {
        Editora editora = ui.lerEditora();
        if (repositorio.salvar(editora)) {
            ui.exibirMensagemSucesso("Editora cadastrada com sucesso!");
        } else {
            ui.exibirMensagemErro("Erro ao cadastrar editora!");
        }
    }
    
    private void listar() {
        ui.exibirEditoras(repositorio.buscarTodos());
    }
    
    public Editora buscarPorNome(String nome) {
        return repositorio.buscarPorNome(nome);
    }
}

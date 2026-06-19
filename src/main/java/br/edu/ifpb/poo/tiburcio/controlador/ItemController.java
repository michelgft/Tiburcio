package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.modelo.*;
import br.edu.ifpb.poo.tiburcio.persistencia.ItemRepositorio;
import br.edu.ifpb.poo.tiburcio.ui.ItemUI;

public class ItemController {
    
    private ItemUI ui;
    private ItemRepositorio repositorio;
    private EditoraController editoraController;
    
    public ItemController(ItemUI ui, ItemRepositorio repositorio, EditoraController editoraController) {
        this.ui = ui;
        this.repositorio = repositorio;
        this.editoraController = editoraController;
    }
    
    public void menu() {
        int opcao;
        do {
            ui.exibirMenu();
            opcao = ui.lerOpcao();
            ui.limparTela();
            switch (opcao) {
                case 1 -> cadastrarLivroFisico();
                case 2 -> cadastrarAudioLivro();
                case 3 -> cadastrarCD();
                case 4 -> cadastrarRevista();
                case 5 -> listar();
                case 6 -> remover();
            }
        } while (opcao != 7);
    }
    
    private void cadastrarLivroFisico() {
        LivroFisico livro = ui.lerLivroFisico();
        String nomeEditora = ui.lerNomeEditora();
        Editora editora = editoraController.buscarPorNome(nomeEditora);
        
        if (editora == null) {
            ui.exibirEditoraNaoEncontrada();
            return;
        }
        
        livro.setEditora(editora);
        if (repositorio.salvar(livro)) {
            ui.exibirMensagemSucesso("Livro físico cadastrado com sucesso!");
        } else {
            ui.exibirMensagemErro("Erro ao cadastrar livro!");
        }
    }
    
    private void cadastrarAudioLivro() {
        AudioLivro livro = ui.lerAudioLivro();
        String nomeEditora = ui.lerNomeEditora();
        Editora editora = editoraController.buscarPorNome(nomeEditora);
        
        if (editora == null) {
            ui.exibirEditoraNaoEncontrada();
            return;
        }
        
        livro.setEditora(editora);
        if (repositorio.salvar(livro)) {
            ui.exibirMensagemSucesso("Áudio livro cadastrado com sucesso!");
        } else {
            ui.exibirMensagemErro("Erro ao cadastrar áudio livro!");
        }
    }
    
    private void cadastrarCD() {
        CD cd = ui.lerCD();
        String nomeEditora = ui.lerNomeEditora();
        Editora editora = editoraController.buscarPorNome(nomeEditora);
        
        if (editora == null) {
            ui.exibirEditoraNaoEncontrada();
            return;
        }
        
        cd.setEditora(editora);
        if (repositorio.salvar(cd)) {
            ui.exibirMensagemSucesso("CD cadastrado com sucesso!");
        } else {
            ui.exibirMensagemErro("Erro ao cadastrar CD!");
        }
    }
    
    private void cadastrarRevista() {
        Revista revista = ui.lerRevista();
        String nomeEditora = ui.lerNomeEditora();
        Editora editora = editoraController.buscarPorNome(nomeEditora);
        
        if (editora == null) {
            ui.exibirEditoraNaoEncontrada();
            return;
        }
        
        revista.setEditora(editora);
        if (repositorio.salvar(revista)) {
            ui.exibirMensagemSucesso("Revista cadastrada com sucesso!");
        } else {
            ui.exibirMensagemErro("Erro ao cadastrar revista!");
        }
    }
    
    private void listar() {
        ui.exibirItens(repositorio.buscarTodos());
    }
    
    private void remover() {
        String id = ui.lerIdItem();
        if (repositorio.excluir(id)) {
            ui.exibirItemRemovido();
        } else {
            ui.exibirItemNaoEncontrado();
        }
    }
    
    public Item buscarPorId(String id) {
        return repositorio.buscar(id);
    }
    
    public boolean atualizar(Item item) {
        return repositorio.atualizar(item);
    }
}

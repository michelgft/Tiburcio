package br.edu.ifpb.poo.tiburcio.controlador;

import br.edu.ifpb.poo.tiburcio.persistencia.*;
import br.edu.ifpb.poo.tiburcio.ui.TiburcioUI;

public class TiburcioController {
    
    private static final int OPCAO_SAIR = 8;
    
    private TiburcioUI ui;
    
    // Controladores especializados
    private EditoraController editoraController;
    private ItemController itemController;
    private JogoController jogoController;
    private UsuarioController usuarioController;
    private EmprestimoController emprestimoController;
    private VendaController vendaController;
    private ConsultaController consultaController;
    
    public TiburcioController(TiburcioUI ui) {
        this.ui = ui;
        
        // Inicializa repositórios
        EditoraRepositorio editoraRepo = new EditoraRepositorio();
        ItemRepositorio itemRepo = new ItemRepositorio();
        JogoRepositorio jogoRepo = new JogoRepositorio();
        UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();
        EmprestimoRepositorio emprestimoRepo = new EmprestimoRepositorio();
        VendaRepositorio vendaRepo = new VendaRepositorio();
        
        // Inicializa controladores (respeitando dependências)
        this.editoraController = new EditoraController(ui.getEditoraUI(), editoraRepo);
        this.itemController = new ItemController(ui.getItemUI(), itemRepo, editoraController);
        this.jogoController = new JogoController(ui.getJogoUI(), jogoRepo);
        this.usuarioController = new UsuarioController(ui.getUsuarioUI(), usuarioRepo);
        this.emprestimoController = new EmprestimoController(
            ui.getEmprestimoUI(), emprestimoRepo, 
            usuarioController, itemController, jogoController
        );
        this.vendaController = new VendaController(
            ui.getEmprestimoUI(), vendaRepo, jogoController
        );
        this.consultaController = new ConsultaController(
            ui.getEmprestimoUI(), emprestimoController, usuarioController
        );
    }
    
    public void execute() {
        int opcao;
        do {
            ui.exibirMenuPrincipal();
            opcao = ui.lerOpcao();
            ui.limparTela();
            executarOperacao(opcao);
            if (opcao != OPCAO_SAIR) {
                ui.pausar();
            }
        } while (opcao != OPCAO_SAIR);
    }
    
    private void executarOperacao(int opcao) {
        switch (opcao) {
            case 1 -> editoraController.menu();
            case 2 -> itemController.menu();
            case 3 -> jogoController.menu();
            case 4 -> usuarioController.menu();
            case 5 -> emprestimoController.menu();
            case 6 -> vendaController.menu();
            case 7 -> consultaController.menu();
            case 8 -> ui.exibirMensagemSucesso("Sistema encerrado!");
            default -> ui.exibirMensagemErro("Opção inválida!");
        }
    }
}

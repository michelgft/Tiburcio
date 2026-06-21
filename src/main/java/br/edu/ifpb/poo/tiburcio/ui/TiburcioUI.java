package br.edu.ifpb.poo.tiburcio.ui;

import br.edu.ifpb.poo.tiburcio.modelo.*;

public class TiburcioUI {
    
    private Console console;
    private EditoraUI editoraUI;
    private ItemUI itemUI;
    private JogoUI jogoUI;
    private UsuarioUI usuarioUI;
    private EmprestimoUI emprestimoUI;
    
    public TiburcioUI() {
        this.console = new Console();
        this.editoraUI = new EditoraUI(console);
        this.itemUI = new ItemUI(console);
        this.jogoUI = new JogoUI(console);
        this.usuarioUI = new UsuarioUI(console);
        this.emprestimoUI = new EmprestimoUI(console);
    }
    
    public Console getConsole() {
        return console;
    }
    
    public EditoraUI getEditoraUI() {
        return editoraUI;
    }
    
    public ItemUI getItemUI() {
        return itemUI;
    }
    
    public JogoUI getJogoUI() {
        return jogoUI;
    }
    
    public UsuarioUI getUsuarioUI() {
        return usuarioUI;
    }
    
    public EmprestimoUI getEmprestimoUI() {
        return emprestimoUI;
    }
    
    public void exibirMenuPrincipal() {
        console.clrscr();
        Menu menu = new Menu(
            "TIBURCIO - BIBLIOTECA",
            new String[]{
                "Gerenciar Editoras",
                "Gerenciar Itens",
                "Gerenciar Jogos",
                "Gerenciar Usuários",
                "Empréstimo/Devolução",
                "Vendas",
                "Consultas",
                "Sair"
            },
            "Digite sua opção: ",
            console
        );
        menu.exibir();
    }
    
    public int lerOpcao() {
        return console.nextInt();
    }
    
    public void exibirMensagemSucesso(String mensagem) {
        console.println(Cores.VERDE, mensagem);
    }
    
    public void exibirMensagemErro(String mensagem) {
        console.println(Cores.VERMELHO, mensagem);
    }
    
    public void exibirMensagemInfo(String mensagem) {
        console.println(Cores.AZUL, mensagem);
    }
    
    public void limparTela() {
        console.clrscr();
    }
    
    public void pausar() {
        console.pause();
    }
}
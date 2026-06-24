package br.edu.ifpb.poo.tiburcio.ui;

import br.edu.ifpb.poo.tiburcio.modelo.JogoTabuleiro;
import java.util.List;

public class JogoUI {
    
    private Console console;
    
    public JogoUI(Console console) {
        this.console = console;
    }
    
    public void exibirMenu() {
        Menu menu = new Menu(
            "JOGOS",
            new String[]{"Cadastrar Jogo", "Listar Jogos", "Remover Jogo", "Voltar"},
            "Opção: ",
            console
        );
        menu.exibir();
    }
    
    public JogoTabuleiro lerJogo() {
        console.println("\n--- CADASTRO JOGO ---");
        console.print("Nome: ");
        String nome = console.nextLine();
        console.println("1-Carta 2-Tabuleiro");
        console.print("Tipo: ");
        int tipoOpcao = console.nextInt();
        String tipo = (tipoOpcao == 1) ? JogoTabuleiro.TIPO_CARTA : JogoTabuleiro.TIPO_TABULEIRO;
        console.print("Quantidade de Peças: ");
        int pecas = console.nextInt();
        console.print("Preço: ");
        double preco = console.nextDouble();
        
        return new JogoTabuleiro(nome, tipo, pecas, preco);
    }
    
    public void exibirJogos(List<JogoTabuleiro> jogos) {
        console.println("\n--- JOGOS ---");
        if (jogos.isEmpty()) {
            console.println("Nenhum jogo cadastrado.");
            return;
        }
        for (JogoTabuleiro jogo : jogos) {
            console.println(jogo.toString());
        }
    }
    
    public String lerIdJogo() {
        console.print("ID do jogo: ");
        return console.nextLine();
    }
    
    public String lerNomeComprador() {
        console.print("Nome do comprador: ");
        return console.nextLine();
    }
    
    public void exibirJogoNaoEncontrado() {
        console.println(Cores.VERMELHO, "Jogo não encontrado!");
    }
    
    public void exibirJogoRemovido() {
        console.println(Cores.VERDE, "Jogo removido com sucesso!");
    }
    
    public void exibirJogoIndisponivel() {
        console.println(Cores.VERMELHO, "Jogo não está disponível para venda!");
    }
    
    public void exibirVendaRealizada(double valor) {
        console.printf(Cores.VERDE, "Venda realizada! Valor: R$ %.2f\n\n", valor);
    }
    public int lerOpcao() {
        return console.nextInt();
    }

    public void limparTela() {
        console.clrscr();
    }

    public void exibirMensagemSucesso(String mensagem) {
        console.println(Cores.VERDE, mensagem);
    }

    public void exibirMensagemErro(String mensagem) {
        console.println(Cores.VERMELHO, mensagem);
    }    
}
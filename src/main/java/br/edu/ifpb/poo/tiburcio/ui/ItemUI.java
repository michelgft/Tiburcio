package br.edu.ifpb.poo.tiburcio.ui;

import br.edu.ifpb.poo.tiburcio.modelo.*;
import java.util.List;

public class ItemUI {
    
    private Console console;
    
    public ItemUI(Console console) {
        this.console = console;
    }
    
    public void exibirMenu() {
        Menu menu = new Menu(
            "ITENS",
            new String[]{
                "Livro Físico", "Áudio Livro", "CD", "Revista",
                "Listar Itens", "Remover Item", "Voltar"
            },
            "Opção: ",
            console
        );
        menu.exibir();
    }
    
    public LivroFisico lerLivroFisico() {
        console.println("\n--- CADASTRO LIVRO FISICO ---");
        console.print("ISBN: ");
        String isbn = console.nextLine();
        console.print("Título: ");
        String titulo = console.nextLine();
        console.print("Autor: ");
        String autor = console.nextLine();
        console.print("Ano: ");
        int ano = console.nextInt();
        console.print("Edição: ");
        int edicao = console.nextInt();
        console.print("Gênero: ");
        String genero = console.nextLine();
        console.print("Páginas: ");
        int paginas = console.nextInt();
        console.print("Sinopse: ");
        String sinopse = console.nextLine();
        
        return new LivroFisico(isbn, titulo, autor, null, ano, edicao, genero, paginas, sinopse);
    }
    
    public AudioLivro lerAudioLivro() {
        console.println("\n--- CADASTRO AUDIO LIVRO ---");
        console.print("ISBN: ");
        String isbn = console.nextLine();
        console.print("Título: ");
        String titulo = console.nextLine();
        console.print("Autor: ");
        String autor = console.nextLine();
        console.print("Ano: ");
        int ano = console.nextInt();
        console.print("Edição: ");
        int edicao = console.nextInt();
        console.print("Gênero: ");
        String genero = console.nextLine();
        console.print("Duração (minutos): ");
        int duracao = console.nextInt();
        console.print("Narrador: ");
        String narrador = console.nextLine();
        
        return new AudioLivro(isbn, titulo, autor, null, ano, edicao, genero, duracao, narrador);
    }
    
    public CD lerCD() {
        console.println("\n--- CADASTRO CD ---");
        console.print("ISBN: ");
        String isbn = console.nextLine();
        console.print("Título: ");
        String titulo = console.nextLine();
        console.print("Artista: ");
        String artista = console.nextLine();
        console.print("Ano: ");
        int ano = console.nextInt();
        console.print("Número de Faixas: ");
        int numFaixas = console.nextInt();
        
        return new CD(isbn, titulo, artista, null, ano, numFaixas);
    }
    
    public Revista lerRevista() {
        console.println("\n--- CADASTRO REVISTA ---");
        console.print("ISSN: ");
        String issn = console.nextLine();
        console.print("Título: ");
        String titulo = console.nextLine();
        console.print("Autor: ");
        String autor = console.nextLine();
        console.print("Volume: ");
        int volume = console.nextInt();
        console.print("Número: ");
        int numero = console.nextInt();
        console.print("Data (dd/MM/yyyy): ");
        String data = console.nextLine();
        
        return new Revista(issn, titulo, autor, null, volume, numero, data);
    }
    
    public void exibirItens(List<Item> itens) {
        console.println("\n--- ACERVO ---");
        if (itens.isEmpty()) {
            console.println("Nenhum item cadastrado.");
            return;
        }
        for (Item item : itens) {
            console.println(item.toString());
        }
    }
    
    public String lerIdItem() {
        console.print("ID do item: ");
        return console.nextLine();
    }
    
    public String lerNomeEditora() {
        console.print("Nome da Editora: ");
        return console.nextLine();
    }
    
    public void exibirItemNaoEncontrado() {
        console.println(Cores.VERMELHO, "Item não encontrado!");
    }
    
    public void exibirItemRemovido() {
        console.println(Cores.VERDE, "Item removido com sucesso!");
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
    
    public void exibirEditoraNaoEncontrada() {
        console.println(Cores.VERMELHO, "Editora não encontrada! Cadastre primeiro.");
    }
}
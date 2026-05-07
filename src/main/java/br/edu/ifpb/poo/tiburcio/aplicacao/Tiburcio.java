package br.edu.ifpb.poo.tiburcio.aplicacao;

import br.edu.ifpb.poo.tiburcio.modelo.*;
import br.edu.ifpb.poo.tiburcio.util.Menu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Classe principal Tiburcio

public class Tiburcio {
    
    private static final int MAX_USUARIOS = 100;
    private static final int MAX_ITENS = 500;
    private static final int MAX_EMPRESTIMOS = 500;
    
    private static Usuario[] usuarios = new Usuario[MAX_USUARIOS];
    private static Item[] itens = new Item[MAX_ITENS];
    private static Emprestimo[] emprestimos = new Emprestimo[MAX_EMPRESTIMOS];
    
    private static int totalUsuarios = 0;
    private static int totalItens = 0;
    private static int totalEmprestimos = 0;
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("\n==== TIBURCIO - BIBLIOTECA ====\n");
        
        Menu principal = new Menu();
        principal.setOpcoes(new String[]{
            "1. Gerenciar Itens",
            "2. Gerenciar Usuários",
            "3. Emprestimo/Devolução",
            "4. Consultas",
            "5. Sair"
        });
        
        int opcao;
        do {
            opcao = principal.exibir(scanner);
            if (opcao == 1) menuItens();
            else if (opcao == 2) menuUsuarios();
            else if (opcao == 3) menuOperacoes();
            else if (opcao == 4) menuConsultas();
            else if (opcao == 5) System.out.println("\nSistema encerrado!\n");
            else System.out.println("Opção invalida!");
        } while (opcao != 5);
        
        scanner.close();
    }
    
    // Menus
    
    private static void menuItens() {
        Menu menu = new Menu();
        menu.setOpcoes(new String[]{"1. Livro", "2. Revista", "3. Listar", "4. Remover", "5. Voltar"});
        int op;
        do {
            op = menu.exibir(scanner);
            if (op == 1) cadastrarLivro();
            else if (op == 2) cadastrarRevista();
            else if (op == 3) listarItens();
            else if (op == 4) removerItem();
        } while (op != 5);
    }
    
    private static void menuUsuarios() {
        Menu menu = new Menu();
        menu.setOpcoes(new String[]{"1. Cadastrar", "2. Listar", "3. Remover", "4. Voltar"});
        int op;
        do {
            op = menu.exibir(scanner);
            if (op == 1) cadastrarUsuario();
            else if (op == 2) listarUsuarios();
            else if (op == 3) removerUsuario();
        } while (op != 4);
    }
    
    private static void menuOperacoes() {
        Menu menu = new Menu();
        menu.setOpcoes(new String[]{"1. Emprestar", "2. Devolver", "3. Voltar"});
        int op;
        do {
            op = menu.exibir(scanner);
            if (op == 1) realizarEmprestimo();
            else if (op == 2) registrarDevolucao();
        } while (op != 3);
    }
    
    private static void menuConsultas() {
        Menu menu = new Menu();
        menu.setOpcoes(new String[]{"1. Emprestimos Abertos", "2. Emprestimos Atraso", 
            "3. Historico Usuario", "4. Todos Emprestimos", "5. Voltar"});
        int op;
        do {
            op = menu.exibir(scanner);
            if (op == 1) listarEmprestimosAbertos();
            else if (op == 2) listarEmprestimosAtraso();
            else if (op == 3) historicoUsuario();
            else if (op == 4) listarTodosEmprestimos();
        } while (op != 5);
    }
    
    // Itens
    
    private static void cadastrarLivro() {
        if (totalItens >= MAX_ITENS) { System.out.println("Acervo cheio!"); return; }
        System.out.println("\n-- CADASTRO LIVRO --");
        System.out.print("ISBN: "); String isbn = scanner.nextLine();
        System.out.print("Titulo: "); String titulo = scanner.nextLine();
        System.out.print("Autor(es): "); String autores = scanner.nextLine();
        System.out.print("Editora: "); String editora = scanner.nextLine();
        System.out.print("Ano: "); int ano = lerInteiro();
        System.out.print("Edicao: "); int edicao = lerInteiro();
        System.out.print("Genero: "); String genero = scanner.nextLine();
        System.out.print("Paginas: "); int paginas = lerInteiro();
        System.out.print("Sinopse: "); String sinopse = scanner.nextLine();
        
        itens[totalItens++] = new Livro(isbn, titulo, autores, editora, ano, edicao, genero, paginas, sinopse);
        System.out.println("Livro cadastrado com sucesso!\n");
    }
    
    private static void cadastrarRevista() {
        if (totalItens >= MAX_ITENS) { System.out.println("Acervo cheio!"); return; }
        System.out.println("\n-- CADASTRO REVISTA --");
        System.out.print("ISSN: "); String issn = scanner.nextLine();
        System.out.print("Titulo: "); String titulo = scanner.nextLine();
        System.out.print("Volume: "); int volume = lerInteiro();
        System.out.print("Numero: "); int numero = lerInteiro();
        System.out.print("Editora: "); String editora = scanner.nextLine();
        System.out.print("Data (dd/MM/yyyy): "); String data = scanner.nextLine();
        
        itens[totalItens++] = new Revista(issn, titulo, volume, numero, editora, data);
        System.out.println("Revista cadastrada com sucesso!\n");
    }
    
    private static void listarItens() {
        System.out.println("\n-- ACERVO --");
        if (totalItens == 0) { System.out.println("Nenhum item cadastrado.\n"); return; }
        for (int i = 0; i < totalItens; i++) {
            System.out.println(itens[i].toString() + "\n");
        }
    }
    
    private static void removerItem() {
        System.out.print("\nID do item: ");
        String id = scanner.nextLine();
        for (int i = 0; i < totalItens; i++) {
            if (itens[i].getId().equals(id)) {
                for (int j = i; j < totalItens - 1; j++) itens[j] = itens[j+1];
                itens[totalItens-1] = null;
                totalItens--;
                System.out.println("Item removido!\n");
                return;
            }
        }
        System.out.println("Item nao encontrado!\n");
    }

  //Usuarios// feita por Julio
    
    private static void cadastrarUsuario() {
        if (totalUsuarios >= MAX_USUARIOS) { System.out.println("Limite de usuarios atingido!"); return; }
        System.out.println("\n--- CADASTRO USUARIO ---");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.println("1-Graduacao 2-Professor 3-PosGraduacao 4-Funcionario");
        System.out.print("Categoria: ");
        int cat = lerInteiro();
        
        String tipo;
        if (cat == 2) tipo = Usuario.TIPO_PROFESSOR;
        else if (cat == 3) tipo = Usuario.TIPO_POS_GRADUACAO;
        else if (cat == 4) tipo = Usuario.TIPO_FUNCIONARIO;
        else tipo = Usuario.TIPO_GRADUACAO;
        
        usuarios[totalUsuarios++] = new Usuario(nome, email, tipo);
        System.out.println("Usuario cadastrado com sucesso!\n");
    }
    
    private static void listarUsuarios() {
        System.out.println("\n--- USUARIOS ---");
        if (totalUsuarios == 0) { System.out.println("Nenhum usuario cadastrado.\n"); return; }
        for (int i = 0; i < totalUsuarios; i++) {
            System.out.println(usuarios[i].toString() + "\n");
        }
    }
    
    private static void removerUsuario() {
        System.out.print("\nID do usuario: ");
        String id = scanner.nextLine();
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getId().equals(id)) {
                for (int j = i; j < totalUsuarios - 1; j++) usuarios[j] = usuarios[j+1];
                usuarios[totalUsuarios-1] = null;
                totalUsuarios--;
                System.out.println("Usuario removido!\n");
                return;
            }
        }
        System.out.println("Usuario nao encontrado!\n");
    }

    // OPERAÇÕES 
    
    private static Usuario buscarUsuario(String id) {
        for (int i = 0; i < totalUsuarios; i++) 
            if (usuarios[i].getId().equals(id)) return usuarios[i];
        return null;
    }
    
    private static Item buscarItem(String id) {
        for (int i = 0; i < totalItens; i++) 
            if (itens[i].getId().equals(id)) return itens[i];
        return null;
    }
    
    private static int contarEmprestimosAtivos(String idUsuario) {
        int count = 0;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].getUsuario().getId().equals(idUsuario) && 
                emprestimos[i].getStatus().equals(Emprestimo.STATUS_ATIVO)) count++;
        }
        return count;
    }
    
    private static boolean temAtraso(String idUsuario) {
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].getUsuario().getId().equals(idUsuario) && 
                emprestimos[i].isEmAtraso() && 
                emprestimos[i].getStatus().equals(Emprestimo.STATUS_ATIVO)) return true;
        }
        return false;
    }
    
    private static void realizarEmprestimo() {
        System.out.println("\n-- EMPRÉSTIMO --");
        System.out.print("ID Usuario: "); String idU = scanner.nextLine();
        System.out.print("ID Item: "); String idI = scanner.nextLine();
        
        Usuario u = buscarUsuario(idU);
        if (u == null) { System.out.println("Usuário não encontrado!\n"); return; }
        
        Item item = buscarItem(idI);
        if (item == null) { System.out.println("Item não encontrado!\n"); return; }
        
        if (!u.isAtivo()) { System.out.println("Usuário inativo!\n"); return; }
        if (u.getMultaPendente() > 0) { System.out.println("Usuário com multa pendente!\n"); return; }
        if (temAtraso(idU)) { System.out.println("Usuário com empréstimo em atraso!\n"); return; }
        
        int ativos = contarEmprestimosAtivos(idU);
        if (ativos >= u.getLimiteEmprestimos()) { System.out.println("Limite de empréstimos atingido!\n"); return; }
        
        if (!item.isDisponivel()) { System.out.println("Item indisponível!\n"); return; }
        
        int prazo = u.getPrazoPadrao();
        if ((u.getTipo().equals(Usuario.TIPO_PROFESSOR) || u.getTipo().equals(Usuario.TIPO_POS_GRADUACAO)) 
            && item.getTipoItem().equals("Revista")) prazo = 7;
        
        emprestimos[totalEmprestimos++] = new Emprestimo(u, item, LocalDate.now(), prazo);
        item.setStatus(Item.STATUS_EMPRESTADO);
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Empréstimo realizado! Devolução prevista: " + LocalDate.now().plusDays(prazo).format(fmt) + "\n");
    }
    
    private static void registrarDevolucao() {
        System.out.println("\n-- DEVOLUÇÃO --");
        System.out.print("ID Emprestimo: ");
        String id = scanner.nextLine();
        
        Emprestimo emp = null;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].getId().equals(id)) { emp = emprestimos[i]; break; }
        }
        
        if (emp == null) { System.out.println("Emprestimo nao encontrado!\n"); return; }
        if (!emp.getStatus().equals(Emprestimo.STATUS_ATIVO)) { System.out.println("Emprestimo ja finalizado!\n"); return; }
        
        double multa = emp.registrarDevolucao(LocalDate.now());
        if (multa > 0) {
            emp.getUsuario().adicionarMulta(multa);
            System.out.printf("Devolucao com multa de R$ %.2f\n\n", multa);
        } else {
            System.out.println("Devolucao realizada sem multa!\n");
        }
    }

  //Consultas do emprestimo// Feita por Julio
    
    private static void listarEmprestimosAbertos() {
        System.out.println("\n--- EMPRESTIMOS EM ABERTO ---");
        int count = 0;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].getStatus().equals(Emprestimo.STATUS_ATIVO)) {
                System.out.println(emprestimos[i].toString() + "\n");
                count++;
            }
        }
        if (count == 0) System.out.println("Nenhum emprestimo em aberto.\n");
    }
    
    private static void listarEmprestimosAtraso() {
        System.out.println("\n--- EMPRESTIMOS EM ATRASO ---");
        int count = 0;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].isEmAtraso() && emprestimos[i].getStatus().equals(Emprestimo.STATUS_ATIVO)) {
                System.out.println(emprestimos[i].toString() + "\n");
                count++;
            }
        }
        if (count == 0) System.out.println("Nenhum emprestimo em atraso.\n");
    }
    
    private static void historicoUsuario() {
        System.out.print("\nID do usuario: ");
        String id = scanner.nextLine();
        System.out.println("\n--- HISTORICO ---");
        int count = 0;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i].getUsuario().getId().equals(id)) {
                System.out.println(emprestimos[i].toString() + "\n");
                count++;
            }
        }
        if (count == 0) System.out.println("Nenhum emprestimo encontrado.\n");
    }
    
    private static void listarTodosEmprestimos() {
        System.out.println("\n--- TODOS EMPRESTIMOS ---");
        if (totalEmprestimos == 0) { System.out.println("Nenhum emprestimo registrado.\n"); return; }
        for (int i = 0; i < totalEmprestimos; i++) {
            System.out.println(emprestimos[i].toString() + "\n");
        }
    }
    
    private static int lerInteiro() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.print("Digite um numero: "); }
        }
    }
}
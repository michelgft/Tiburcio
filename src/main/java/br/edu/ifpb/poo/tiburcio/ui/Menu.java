package br.edu.ifpb.poo.tiburcio.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String titulo;
    private String[] opcoes;
    private String prompt;
    private Console console;

    public Menu exibir() {
        final char TL = '╔';
        final char TR = '╗';
        final char BL = '╚';
        final char BR = '╝';
        final char HL = '═';
        final char VL = '║';

        int maxConteudo = titulo.length();
        for (int i = 0; i < opcoes.length; i++) {
            int tamanhoItem = (i + 1 + ") " + opcoes[i]).length();
            if (tamanhoItem > maxConteudo) {
                maxConteudo = tamanhoItem;
            }
        }

        int largura = maxConteudo + 2;

        System.out.print(TL);
        for (int i = 0; i < largura; i++) System.out.print(HL);
        System.out.println(TR);

        int espacos = largura - titulo.length();
        int esquerda = espacos / 2;
        int direita = espacos - esquerda;

        System.out.print(VL);
        for (int i = 0; i < esquerda; i++) System.out.print(" ");
        System.out.print(titulo);
        for (int i = 0; i < direita; i++) System.out.print(" ");
        System.out.println(VL);

        System.out.print(VL);
        for (int i = 0; i < largura; i++) System.out.print(HL);
        System.out.println(VL);

        for (int i = 0; i < opcoes.length; i++) {
            String linha = (i + 1) + ") " + opcoes[i];
            System.out.print(VL);
            System.out.print(linha);
            for (int j = linha.length(); j < largura; j++) System.out.print(" ");
            System.out.println(VL);
        }

        System.out.print(BL);
        for (int i = 0; i < largura; i++) System.out.print(HL);
        System.out.println(BR);

        return this;
    }

    public int leiaOpcao() {
        System.out.print(Cores.AZUL + this.prompt + Cores.RESET);
        return console.nextInt();
    }
}
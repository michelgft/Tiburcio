package br.edu.ifpb.poo.tiburcio.ui;

import java.util.Scanner;

public class Console {
    private Scanner in;

    public Console() {
        this.in = new Scanner(System.in);
    }

    public void println(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public String nextLine() {
        return in.nextLine();
    }

    public int nextInt() {
        try {
            return Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public double nextDouble() {
        try {
            return Double.parseDouble(in.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void clrscr() {
        System.out.print("\u001B[2J\u001B[H");
        System.out.flush();
    }

    public void pause() {
        this.println("\nPressione ENTER para continuar...");
        this.nextLine();
    }

    public void printBorder(int nLinhas, int largura) {
        final String TL = "╔";
        final String TR = "╗";
        final String BL = "╚";
        final String BR = "╝";
        final String HL = "═";
        final String VL = "║";

        System.out.println(TL + HL.repeat(largura) + TR);
        for (int i = 0; i < nLinhas; i++) {
            System.out.println(VL + " ".repeat(largura) + VL);
        }
        System.out.println(BL + HL.repeat(largura) + BR);
    }

    public void print(String cor, String texto) {
        System.out.print(cor + texto + Cores.RESET);
    }

    public void println(String cor, String texto) {
        System.out.println(cor + texto + Cores.RESET);
    }
}
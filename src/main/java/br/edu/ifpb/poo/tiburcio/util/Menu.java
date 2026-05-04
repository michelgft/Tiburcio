package br.edu.ifpb.poo.tiburcio.util;

import lombok.Data;
import java.util.Scanner;

@Data
public class Menu {
    
    private String[] opcoes;
    private String prompt = "Opcao: ";
    
    public int exibir(Scanner scanner) {
        System.out.println();
        for (String opcao : opcoes) {
            System.out.println(opcao);
        }
        
        while (true) {
            System.out.print(prompt);
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                if (opcao >= 1 && opcao <= opcoes.length) {
                    return opcao;
                } else {
                    System.out.print("Opção inválida! Digite novamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Digite um numero: ");
            }
        }
    }
}
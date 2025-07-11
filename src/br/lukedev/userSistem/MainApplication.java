package br.lukedev.userSistem;

import br.lukedev.userSistem.dao.UserDAO;
import br.lukedev.userSistem.model.MenuOption;

import java.util.Scanner;

public class MainApplication {

    private final UserDAO dao = new UserDAO();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seja bem vindo ao cadastro de usuarios em java \n" +
                "digite o número da opção desejada:");

        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Atualizar usuário");
        System.out.println("3 - Deletar usuário");
        System.out.println("4 - Procurar usuário");
        System.out.println("5 - Listar todos os usuários");
        System.out.println("6 - Sair");
        int userInput = sc.nextInt();

        while (true){
            MenuOption selectedOption = MenuOption.values()[userInput - 1];
        }
    }
}

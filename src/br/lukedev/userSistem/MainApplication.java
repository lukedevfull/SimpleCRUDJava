package br.lukedev.userSistem;

import br.lukedev.userSistem.dao.UserDAO;
import br.lukedev.userSistem.model.MenuOption;
import br.lukedev.userSistem.model.UserModel;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    private static Scanner sc = new Scanner(System.in);
    private final static UserDAO dao = new UserDAO();

    public static void main(String[] args) {
        List responseList = new ArrayList<>(List.of("s", "S", "y", "Y"));
        List exitList = new ArrayList<>(List.of("n", "N"));
        System.out.println("Deseja iniciar?<s/n>");
        String response = sc.nextLine();

        Boolean isResponseInList = responseList.contains(response);
        Boolean isExitInList = exitList.contains(response);
        do {
            try {
                if (isResponseInList) {
                    MenuOption selectedOption = menu();
                    UserModel user = collectDataUser(selectedOption);
                    processMenu(selectedOption, user);

                    System.out.println("Deseja fazer outra interação?<s/n>");
                    response = sc.nextLine();
                }
                if (isExitInList) {
                    System.out.println("Obrigado por usar o sistema");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Opção invalida");
                System.exit(0);
            }} while (isResponseInList);

        System.out.println("Obrigado por usar o sistema");
    }

    public static UserModel collectDataUser(MenuOption option){
        UserModel user = new UserModel();
        switch (option){
            case CREATE, UPDATE ->{
                System.out.println("Insira o nome:");
                user.setName(sc.nextLine());
                System.out.println("Insira o email:");
                user.setEmail(sc.nextLine());
                System.out.println("Insira a data de nascimento (dd/MM/yyyy):");
                String dateInput = sc.nextLine();
                DateTimeFormatter formatter =
                        DateTimeFormatter
                                .ofPattern("dd/MM/yyyy");
                LocalDate birthDate =
                        LocalDate
                                .parse(dateInput, formatter);
                user.setBirthDate(birthDate
                        .atStartOfDay()
                        .atOffset(ZoneOffset.UTC));
            }
            case FIND_BY_ID, DELETE -> {
                System.out.println("Insira o id:");
                user.setId(sc.nextLong());
                sc.nextLine(); // Limpa o buffer
            }
            case EXIT -> {
                System.out.println("Obrigado por usar o sistema");
                sc.nextLine(); // Limpa o buffer
                System.exit(0);
            }
        }
        return user;
    }
    public static MenuOption menu() {
        System.out.println("\nSeja bem vindo ao cadastro de usuarios");
        System.out.println("Digite o número da opção desejada:");

        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%d - %s%n", option.ordinal() + 1, option.getDescription());
        }
        try {
            int userInput = sc.nextInt();
            sc.nextLine();  // Limpa o buffer

            if (userInput < 1 || userInput > MenuOption.values().length) {
                throw new IllegalArgumentException("Opção inválida");
            }
            return MenuOption.values()[userInput - 1];
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
                sc.nextLine();  // Limpa o buffer em caso de erro
                return menu();  // Recursão para nova tentativa
        }
    }

    public static void processMenu(MenuOption selectedOption, UserModel user){
        try {
            switch (selectedOption){
                case CREATE -> {
                    dao.saveUser(user);
                    System.out.println("Usuário cadastrado com sucesso");
                    break;
                }
                case UPDATE -> {
                    dao.updateUser(user);
                    System.out.println("Usuário atualizado com sucesso");
                    break;
                }
                case DELETE -> {
                    dao.deleteUser(user.getId());
                    System.out.println("Usuário deletado com sucesso");
                    break;
                }
                case FIND_BY_ID -> {
                    dao.findById(user.getId());
                    System.out.println("Usuário encontrado");
                    break;
                }
                case FIND_ALL -> {
                    dao.findAll();
                    System.out.println("Lista de usuarios");
                    break;
                }
                case EXIT -> {
                    System.out.println("Obrigado por usar o sistema");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Opção invalida");
            }
        }
    }


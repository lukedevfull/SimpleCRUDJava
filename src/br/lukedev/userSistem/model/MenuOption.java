package br.lukedev.userSistem.model;

public enum MenuOption {
    CREATE("Cadastrar usuário"),
    UPDATE("Atualizar usuário"),
    DELETE("Deletar usuário"),
    FIND_BY_ID("Procurar usuário"),
    FIND_ALL("Listar todos os usuários"),
    EXIT("Sair");

    private final String description;

    MenuOption(String description) {
            this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


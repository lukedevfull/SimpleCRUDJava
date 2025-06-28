package br.lukedev.userSistem.dao;

import br.lukedev.userSistem.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final List<UserModel>  UsersModel = new ArrayList<>();

    private long nextId = 1L;

    public UserModel saveUser ( final UserModel user){
        user.setId(nextId++);
        UsersModel.add(user);
        return user;
    }
}

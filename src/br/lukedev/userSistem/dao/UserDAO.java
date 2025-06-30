package br.lukedev.userSistem.dao;

import br.lukedev.userSistem.model.UserModel;
import br.lukedev.userSistem.personalExeptions.UserNotFoundExeption;
//import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final List<UserModel> models = new ArrayList<>();

    private long nextId = 1L;

//    @NotNull
    public UserModel saveUser (final UserModel model){
        model.setId(nextId++);
        models.add(model);
        return model;
    }

//    public UserModel updateUser (final UserModel user){
//        return null;
//    }

    public UserModel findById(final Long id){
        var message = String.format("User not found with id: %d", id);
        return
                models.stream().filter(user -> user.getId()
                    .equals(id))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundExeption(message));
    }

    public List<UserModel> findAll(){
        return new ArrayList<>(models);
    }
}

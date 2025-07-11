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

    public void deleteUser(final Long id){
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public UserModel updateUser (final UserModel user){
        var toUpdate = findById(user.getId());
        if (user.getName() != null){
            toUpdate.setName(user.getName());
        }
        if (user.getEmail() != null){
            toUpdate.setEmail(user.getEmail());
        }
        if (user.getBirthDate() != null){
            toUpdate.setBirthDate(user.getBirthDate());
        }
        return toUpdate;
    }

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

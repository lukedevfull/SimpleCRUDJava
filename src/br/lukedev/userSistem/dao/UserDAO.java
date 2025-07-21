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
        toUpdate.setName(user.getName());
        toUpdate.setEmail(user.getEmail());
        toUpdate.setBirthDate(user.getBirthDate());
        return toUpdate;
    }

    public UserModel findById(final Long id){
        var message = String.format("User not found with id: %d", id);

        var result = models.stream().filter(user -> user.getId()
                    .equals(id))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundExeption(message));
        System.out.println(result.toString());
        return result;
    }

    public List<UserModel> findAll(){
        for (UserModel model : models)
            System.out.println(model.toString());
        return new ArrayList<>(models);
    }
}

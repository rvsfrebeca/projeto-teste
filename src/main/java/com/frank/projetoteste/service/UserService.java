package com.frank.projetoteste.service;

import com.frank.projetoteste.model.User;
import com.frank.projetoteste.model.UserDTO;
import com.frank.projetoteste.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers(){
        return repository.getAllUsers();
    }

    public User getUser(UUID id) {
        return repository.getUser(id);
    }

    public User createUser(UserDTO userReceived) {
        return repository.createUser(userReceived);
    }

    public void updateUser(UserDTO userToUpdate, UUID id) {
        repository.updateUser(userToUpdate, id);
    }

    public void deleteUser(UUID id) {
        repository.deleteUser(id);
    }
}

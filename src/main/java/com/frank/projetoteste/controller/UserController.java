package com.frank.projetoteste.controller;

import com.frank.projetoteste.model.User;
import com.frank.projetoteste.model.UserDTO;
import com.frank.projetoteste.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping()
    public List<User> retrieveUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable UUID id) {
        return service.getUser(id);
    }


    @PostMapping()
    public User createUser(@Valid @RequestBody UserDTO userReceived) {
        return service.createUser(userReceived);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable UUID id, @Valid @RequestBody UserDTO userToUpdate) {
        service.updateUser(userToUpdate, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
    }


}

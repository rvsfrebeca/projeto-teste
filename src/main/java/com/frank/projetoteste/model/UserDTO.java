package com.frank.projetoteste.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@JsonInclude()
public class UserDTO {

    @NotNull
    @NotEmpty
    private final String nome;

    @NotNull
    @NotEmpty
    private final String telefone;

    @NotNull
    @NotEmpty
    private final String email;

    public User toController(UUID userId){
        return User.builder()
                .id(userId)
                .nome(this.nome)
                .telefone(this.telefone)
                .email(this.email)
                .build();
    }
}

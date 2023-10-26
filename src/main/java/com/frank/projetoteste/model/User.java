package com.frank.projetoteste.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Builder
@Getter
public class User {

    private final UUID id;

    private final String nome;

    private final String telefone;

    private final String email;
}

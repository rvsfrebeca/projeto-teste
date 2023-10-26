package com.frank.projetoteste.model;

import lombok.Builder;

import java.util.UUID;
@Builder
public class Product {
    private final UUID id;

    private final String name;
}

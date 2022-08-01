package com.example.demo.dao.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {
    SCIENTIFIC,
    PUBLICIST,
    MARKETING;

    @JsonCreator
    public static Type create(String value) {
        return Type.valueOf(value.toUpperCase());
    }
}

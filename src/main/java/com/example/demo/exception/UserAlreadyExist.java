package com.example.demo.exception;


import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;

import java.util.List;

public class UserAlreadyExist extends RuntimeException implements GraphQLError {

    @Getter
    private final String message;

    public UserAlreadyExist(String email) {
        this.message = String.format("User with email %s already exist", email);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }
    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.ExecutionAborted;
    }
}


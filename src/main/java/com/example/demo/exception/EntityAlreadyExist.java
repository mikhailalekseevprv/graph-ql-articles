package com.example.demo.exception;


import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;

import java.util.List;

public class EntityAlreadyExist extends RuntimeException implements GraphQLError {

    @Getter
    private final String message;

    public EntityAlreadyExist(Class<?> clazz, String property, String value) {
        this.message = clazz.getSimpleName() + " already exist with " + property + "=" + value;
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


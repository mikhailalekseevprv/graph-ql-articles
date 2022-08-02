package com.example.demo.exception;


import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityNotFoundException extends RuntimeException implements GraphQLError {

    public EntityNotFoundException(Class<?> clazz, String id) {
        super(clazz.getSimpleName() + " was not found with id: " + id);
    }

    public EntityNotFoundException(Class<?> clazz, String... params) {
        super(generateMessage(clazz.getSimpleName(), params));
    }

    private static String generateMessage(String entity, String... params) {
        if (params.length % 2 == 0) {
            return String.format("%s was not found for parameters %s", entity, toMap(params));
        }

        return String.format("%s was not found for parameters %s", entity, Arrays.asList(params));
    }

    private static Map<String, String> toMap(String... entries) {
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
            .collect(
                HashMap::new,
                (m, i) -> m.put(entries[i], entries[i + 1]),
                Map::putAll
            );
    }



    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }
}


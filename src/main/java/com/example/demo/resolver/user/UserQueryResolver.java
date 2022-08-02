package com.example.demo.resolver.user;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {

    private final UserService userService;

    public List<UserEntity> users() {
        return userService.getAll();
    }

    public UserEntity userById(Long id) {
        return userService.getById(id);
    }

    public List<UserEntity> usersByIds(Set<Long> ids) {
        return userService.findByIds(ids);
    }
}

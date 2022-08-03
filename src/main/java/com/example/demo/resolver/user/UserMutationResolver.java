package com.example.demo.resolver.user;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Component
@RequiredArgsConstructor
@Validated
@Transactional
public class UserMutationResolver implements GraphQLMutationResolver {

    private final UserService userService;

    public UserEntity createUser(@Valid @Email String email, String password, String firstName, String lastName, String info) {
        return userService.create(email, password, firstName, lastName, info);
    }

    public UserEntity updateUser(Long id, String firstName, String lastName, String info) {
        return userService.update(id, firstName, lastName, info);
    }

    public Boolean deleteUser(Long id) {
        userService.delete(id);
        return true;
    }
}

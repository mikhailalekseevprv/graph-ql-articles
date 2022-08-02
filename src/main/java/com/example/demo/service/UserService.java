package com.example.demo.service;

import com.example.demo.dao.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserEntity create(String email, String password, String firstName, String lastName, String info);

    List<UserEntity> getAll();

    UserEntity getById(long id);

    List<UserEntity> findByIds(Set<Long> ids);

    UserEntity update(long id, String firstName, String lastName, String info);

    void delete(long id);
}

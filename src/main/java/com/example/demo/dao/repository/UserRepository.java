package com.example.demo.dao.repository;

import com.example.demo.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}

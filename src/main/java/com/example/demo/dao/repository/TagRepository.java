package com.example.demo.dao.repository;

import com.example.demo.dao.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<TagEntity, Integer> {
    boolean existsByName(String name);
}

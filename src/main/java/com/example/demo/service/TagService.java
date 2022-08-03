package com.example.demo.service;

import com.example.demo.dao.entity.TagEntity;

import java.util.List;
import java.util.Set;

public interface TagService {
    TagEntity create(String name);

    TagEntity getById(int id);

    List<TagEntity> getByIds(Set<Integer> ids);

    TagEntity update(int id, String name);

    void delete(int id);
}

package com.example.demo.service;

import com.example.demo.dao.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    CommentEntity create(long articleId, long userId, String text);

    CommentEntity getById(long id);

    List<CommentEntity> getByArticleId(long articleId);

    CommentEntity update(long id, String text);

    void delete(long id);
}

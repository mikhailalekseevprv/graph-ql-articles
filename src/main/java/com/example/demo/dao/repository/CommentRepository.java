package com.example.demo.dao.repository;

import com.example.demo.dao.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByArticleId(Long articleId);
}

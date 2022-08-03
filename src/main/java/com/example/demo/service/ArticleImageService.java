package com.example.demo.service;

import com.example.demo.dao.entity.ArticleImageEntity;

import java.util.List;
import java.util.Set;

public interface ArticleImageService {
    ArticleImageEntity upload(long articleId, int position);

    ArticleImageEntity getById(long id);

    List<ArticleImageEntity> findByIds(Set<Long> ids);

    ArticleImageEntity update(long id, Integer position);

    void delete(long id);
}

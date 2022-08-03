package com.example.demo.service;

import com.example.demo.dao.entity.ArticleEntity;

public interface ArticleService {
    ArticleEntity getById(long id);
}

package com.example.demo.service;

import com.example.demo.dao.entity.ArticleEntity;
import com.example.demo.dao.repository.ArticleRepository;
import com.example.demo.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleEntity getById(long id) {
        return articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ArticleEntity.class, Long.toString(id)));
    }
}

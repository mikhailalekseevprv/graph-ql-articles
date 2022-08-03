package com.example.demo.service;

import com.example.demo.dao.entity.ArticleEntity;
import com.example.demo.dao.entity.Type;

import java.util.List;
import java.util.Set;

public interface ArticleService {
    ArticleEntity getById(long id);

    ArticleEntity create(Type type, String title, String text, long authorId, Set<Integer> tagIds,
        Set<Long> referenceIds, String scientificTheory, String publicationStyle, String geographicalTarget,
        String ageTarget, String sexTarget
    );

    List<ArticleEntity> findByIds(Set<Long> ids);

    ArticleEntity update(long id, Type type, String title, String text, Set<Integer> tagIds,
        Set<Long> referenceIds, String scientificTheory, String publicationStyle, String geographicalTarget,
        String ageTarget, String sexTarget
    );

    void delete(long id);
}

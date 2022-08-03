package com.example.demo.resolver.article;

import com.example.demo.dao.entity.ArticleEntity;
import com.example.demo.service.ArticleService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional
public class ArticleQueryResolver implements GraphQLQueryResolver {

    private final ArticleService articleService;

    public ArticleEntity articleById(Long id) {
        return articleService.getById(id);
    }

    public List<ArticleEntity> articlesByIds(Set<Long> ids) {
        return articleService.findByIds(ids);
    }
}

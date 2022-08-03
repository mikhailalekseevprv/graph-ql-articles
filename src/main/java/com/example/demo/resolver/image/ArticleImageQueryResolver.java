package com.example.demo.resolver.image;

import com.example.demo.dao.entity.ArticleImageEntity;
import com.example.demo.service.ArticleImageService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ArticleImageQueryResolver implements GraphQLQueryResolver {

    private final ArticleImageService imageService;

    public ArticleImageEntity imageById(Long id) {
        return imageService.getById(id);
    }

    public List<ArticleImageEntity> imagesByIds(Set<Long> ids) {
        return imageService.findByIds(ids);
    }
}

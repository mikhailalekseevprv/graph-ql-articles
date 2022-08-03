package com.example.demo.resolver.image;

import com.example.demo.dao.entity.ArticleImageEntity;
import com.example.demo.service.ArticleImageService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ArticleImageMutationResolver implements GraphQLMutationResolver {

    private final ArticleImageService imageService;

    @Transactional
    public ArticleImageEntity uploadImage(Long articleId, Integer position) {
        return imageService.upload(articleId, position);
    }

    @Transactional
    public ArticleImageEntity updateImage(Long id, Integer position) {
        return imageService.update(id, position);
    }

    @Transactional
    public Boolean deleteImage(Long id) {
        imageService.delete(id);
        return true;
    }
}

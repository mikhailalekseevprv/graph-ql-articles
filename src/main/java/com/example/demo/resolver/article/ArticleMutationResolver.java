package com.example.demo.resolver.article;

import com.example.demo.dao.entity.ArticleEntity;
import com.example.demo.dao.entity.Type;
import com.example.demo.service.ArticleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional
public class ArticleMutationResolver implements GraphQLMutationResolver {

    private final ArticleService articleService;

    public ArticleEntity createArticle(Type type, String title, String text, long authorId, Set<Integer> tagIds,
        Set<Long> referenceIds, String scientificTheory, String publicationStyle, String geographicalTarget,
        String ageTarget, String sexTarget
    ) {
        return articleService.create(type, title, text, authorId, tagIds, referenceIds, scientificTheory,
            publicationStyle, geographicalTarget, ageTarget, sexTarget);
    }

    public ArticleEntity updateArticle(long id, Type type, String title, String text, Set<Integer> tagIds,
        Set<Long> referenceIds, String scientificTheory, String publicationStyle, String geographicalTarget,
        String ageTarget, String sexTarget
    ) {
        return articleService.update(id, type, title, text, tagIds, referenceIds, scientificTheory, publicationStyle,
            geographicalTarget, ageTarget, sexTarget
        );
    }

    public Boolean deleteArticle(Long id) {
        articleService.delete(id);
        return true;
    }
}

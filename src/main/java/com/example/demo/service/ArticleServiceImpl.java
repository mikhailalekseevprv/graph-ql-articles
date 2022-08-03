package com.example.demo.service;

import com.example.demo.dao.entity.ArticleEntity;
import com.example.demo.dao.entity.TagEntity;
import com.example.demo.dao.entity.Type;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repository.ArticleRepository;
import com.example.demo.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final TagService tagService;

    @Override
    public ArticleEntity getById(long id) {
        return articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ArticleEntity.class, Long.toString(id)));
    }

    @Override
    public ArticleEntity create(Type type, String title, String text, long authorId, Set<Integer> tagIds,
        Set<Long> referenceIds, String scientificTheory, String publicationStyle, String geographicalTarget,
        String ageTarget, String sexTarget
    ) {
        UserEntity author = userService.getById(authorId);
        ArticleEntity article = new ArticleEntity();
        article.setAuthor(author);

        if (!CollectionUtils.isEmpty(tagIds)) {
            List<TagEntity> tags = tagService.getByIds(tagIds);
            article.setTags(tags);
        }
        if (!CollectionUtils.isEmpty(referenceIds)) {
            List<ArticleEntity> references = findByIds(referenceIds);
            article.setReferences(references);
        }

        article.setType(type);
        article.setTitle(title);
        article.setText(text);
        article.setScientificTheory(scientificTheory);
        article.setGeographicalTarget(geographicalTarget);
        article.setAgeTarget(ageTarget);
        article.setSexTarget(sexTarget);
        article.setPublicationStyle(publicationStyle);
        return articleRepository.save(article);
    }

    @Override
    public List<ArticleEntity> findByIds(Set<Long> ids) {
        return StreamSupport.stream(articleRepository.findAllById(ids).spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public ArticleEntity update(long id, Type type, String title, String text, Set<Integer> tagIds, Set<Long> referenceIds, String scientificTheory, String publicationStyle, String geographicalTarget, String ageTarget, String sexTarget) {
        ArticleEntity article = getById(id);

        if (tagIds != null) {
            List<TagEntity> tags = tagService.getByIds(tagIds);
            article.setTags(tags);
        }
        if (referenceIds != null) {
            List<ArticleEntity> references = findByIds(referenceIds);
            article.setReferences(references);
        }
        if (type != null) {
            article.setType(type);
        }
        if (title != null) {
            article.setTitle(title);
        }
        if (text != null) {
            article.setText(text);
        }
        if (scientificTheory != null) {
            article.setScientificTheory(scientificTheory);
        }
        if (geographicalTarget != null) {
            article.setGeographicalTarget(geographicalTarget);
        }
        if (ageTarget != null) {
            article.setAgeTarget(ageTarget);
        }
        if (sexTarget != null) {
            article.setSexTarget(sexTarget);
        }
        if (publicationStyle != null) {
            article.setPublicationStyle(publicationStyle);
        }
        return articleRepository.save(article);
    }

    @Override
    public void delete(long id) {
        ArticleEntity entity = getById(id);
        entity.getReferences().clear();
        articleRepository.save(entity);
        articleRepository.delete(entity);
    }
}

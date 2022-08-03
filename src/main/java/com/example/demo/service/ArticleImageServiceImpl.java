package com.example.demo.service;

import com.example.demo.dao.entity.ArticleEntity;
import com.example.demo.dao.entity.ArticleImageEntity;
import com.example.demo.dao.repository.ArticleImageRepository;
import com.example.demo.dao.repository.ArticleRepository;
import com.example.demo.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArticleImageServiceImpl implements ArticleImageService {

    private final ArticleImageRepository articleImageRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ArticleImageEntity upload(long articleId, int position) {
        ArticleEntity article = articleRepository.findById(articleId)
            .orElseThrow(() -> new EntityNotFoundException(ArticleEntity.class, Long.toString(articleId)));

        ArticleImageEntity articleImage = new ArticleImageEntity();
        articleImage.setPosition(position);
        articleImage.setUrl("Some-bucket/" + UUID.randomUUID());
        articleImage.setArticle(article);
        return articleImageRepository.save(articleImage);
    }

    @Override
    public ArticleImageEntity getById(long id) {
        return articleImageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ArticleImageEntity.class, Long.toString(id)));
    }

    @Override
    public List<ArticleImageEntity> findByIds(Set<Long> ids) {
        return StreamSupport.stream(articleImageRepository.findAllById(ids)
                .spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public ArticleImageEntity update(long id, Integer position) {
        ArticleImageEntity articleImage = getById(id);

        if (position != null) {
            articleImage.setPosition(position);
        }
        return articleImageRepository.save(articleImage);
    }

    @Override
    public void delete(long id) {
        ArticleImageEntity entity = getById(id);
        articleImageRepository.delete(entity);
    }
}

package com.example.demo.service;

import com.example.demo.dao.entity.ArticleEntity;
import com.example.demo.dao.entity.CommentEntity;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repository.CommentRepository;
import com.example.demo.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;
    private final UserService userService;

    @Override
    public CommentEntity create(long articleId, long userId, String text) {
        ArticleEntity article = articleService.getById(articleId);
        UserEntity user = userService.getById(userId);
        CommentEntity comment = new CommentEntity();
        comment.setText(text);
        comment.setUser(user);
        comment.setArticle(article);
        return commentRepository.save(comment);
    }

    @Override
    public CommentEntity getById(long id) {
        return commentRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(CommentEntity.class, Long.toString(id))
        );
    }

    @Override
    public List<CommentEntity> getByArticleId(long articleId) {
        articleService.getById(articleId);
        return commentRepository.findAllByArticleId(articleId);
    }

    @Override
    public CommentEntity update(long id, String text) {
        CommentEntity comment = getById(id);
        comment.setText(text);
        return commentRepository.save(comment);
    }

    @Override
    public void delete(long id) {
        CommentEntity entity = getById(id);
        commentRepository.delete(entity);
    }
}

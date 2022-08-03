package com.example.demo.resolver.comment;

import com.example.demo.dao.entity.CommentEntity;
import com.example.demo.service.CommentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CommentMutationResolver implements GraphQLMutationResolver {

    private final CommentService commentService;

    @Transactional
    public CommentEntity createComment(Long articleId, Long userId, String text) {
        return commentService.create(articleId, userId, text);
    }

    @Transactional
    public CommentEntity updateComment(Long id, String text) {
        return commentService.update(id, text);
    }

    @Transactional
    public Boolean deleteComment(Long id) {
        commentService.delete(id);
        return true;
    }
}

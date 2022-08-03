package com.example.demo.resolver.comment;

import com.example.demo.dao.entity.CommentEntity;
import com.example.demo.service.CommentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentQueryResolver implements GraphQLQueryResolver {

    private final CommentService commentService;

    public CommentEntity commentById(Long id) {
        return commentService.getById(id);
    }

    public List<CommentEntity> commentsByArticleId(Long articleId) {
        return commentService.getByArticleId(articleId);
    }
}

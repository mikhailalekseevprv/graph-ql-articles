package com.example.demo.resolver.tag;

import com.example.demo.dao.entity.TagEntity;
import com.example.demo.service.TagService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagMutationResolver implements GraphQLMutationResolver {

    private final TagService tagService;

    public TagEntity createTag(String name) {
        return tagService.create(name);
    }

    public TagEntity updateTag(Integer id, String name) {
        return tagService.update(id, name);
    }

    public Boolean deleteTag(int id) {
        tagService.delete(id);
        return true;
    }
}

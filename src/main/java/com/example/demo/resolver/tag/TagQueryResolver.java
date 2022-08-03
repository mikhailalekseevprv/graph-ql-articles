package com.example.demo.resolver.tag;

import com.example.demo.dao.entity.TagEntity;
import com.example.demo.service.TagService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TagQueryResolver implements GraphQLQueryResolver {

    private final TagService tagService;

    public TagEntity tagById(Integer id) {
        return tagService.getById(id);
    }

    public List<TagEntity> tagsByIds(Set<Integer> ids) {
        return tagService.getByIds(ids);
    }
}

package com.example.demo.service;

import com.example.demo.dao.entity.TagEntity;
import com.example.demo.dao.repository.TagRepository;
import com.example.demo.exception.EntityAlreadyExist;
import com.example.demo.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public TagEntity create(String name) {
        validateTagNotExist(name);
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(name);
        return tagRepository.save(tagEntity);
    }

    @Override
    public TagEntity getById(int id) {
        return tagRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(TagEntity.class, Long.toString(id))
        );
    }

    @Override
    public List<TagEntity> getByIds(Set<Integer> ids) {
        return StreamSupport.stream(tagRepository.findAllById(ids).spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public TagEntity update(int id, String name) {
        validateTagNotExist(name);
        TagEntity tag = getById(id);
        tag.setName(name);
        return tagRepository.save(tag);
    }

    @Override
    public void delete(int id) {
        TagEntity entity = getById(id);
        tagRepository.delete(entity);
    }

    private void validateTagNotExist(String name) {
        if (tagRepository.existsByName(name)) {
            throw new EntityAlreadyExist(TagEntity.class, "name", name);
        }
    }
}

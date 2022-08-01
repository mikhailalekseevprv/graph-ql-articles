package com.example.demo.dao.entity.id;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class ArticleClapId implements Serializable {
    private long userId;
    private long articleId;
}

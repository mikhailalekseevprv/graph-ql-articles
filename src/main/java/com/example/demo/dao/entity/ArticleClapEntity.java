package com.example.demo.dao.entity;

import com.example.demo.dao.entity.id.ArticleClapId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "article_claps")
@IdClass(ArticleClapId.class)
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "article")
public class ArticleClapEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Id
    @Column(name = "article_id", nullable = false)
    private long articleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    @ToString.Exclude
    private ArticleEntity article;

    public ArticleClapEntity(long userId, long articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }
}

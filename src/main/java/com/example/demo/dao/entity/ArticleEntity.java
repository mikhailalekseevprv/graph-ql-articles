package com.example.demo.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@Entity(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private UserEntity author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade= CascadeType.ALL)
    @ToString.Exclude
    private List<CommentEntity> comments = new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ArticleClapEntity> claps = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ArticleImageEntity> images = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "articles_to_tags", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private List<TagEntity> tags;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "article_references", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "reference_article_id"))
    @ToString.Exclude
    private List<ArticleEntity> references;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column
    private String scientificTheory;

    @Column
    private String publicationStyle;

    @Column
    private String geographicalTarget;

    @Column
    private String ageTarget;

    @Column
    private String sexTarget;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleEntity article = (ArticleEntity) o;
        return id != null && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

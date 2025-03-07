package com.org.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FilmCategoryId implements java.io.Serializable {
    private static final long serialVersionUID = 5921994518833709420L;
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer filmId;

    @Column(name = "category_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FilmCategoryId entity = (FilmCategoryId) o;
        return Objects.equals(this.filmId, entity.filmId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }

}
package com.org.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "language", schema = "sakila")
public class Language {
    @Id
    @Column(name = "language_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short id;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}
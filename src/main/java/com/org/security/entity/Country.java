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
@Table(name = "country", schema = "sakila")
public class Country {
    @Id
    @Column(name = "country_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}
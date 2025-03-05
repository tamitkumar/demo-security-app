package com.org.security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "city", schema = "sakila")
public class City {
    @Id
    @Column(name = "city_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}
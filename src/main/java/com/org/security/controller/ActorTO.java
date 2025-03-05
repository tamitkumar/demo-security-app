package com.org.security.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ActorTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Instant lastUpdate;
}

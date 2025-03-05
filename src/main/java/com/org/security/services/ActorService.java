package com.org.security.services;

import com.org.security.controller.ActorTO;

public interface ActorService {
    ActorTO findById(Integer id);

    ActorTO save(ActorTO actor);
}

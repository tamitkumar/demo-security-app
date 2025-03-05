package com.org.security.services.impl;

import com.org.security.controller.ActorTO;
import com.org.security.entity.Actor;
import com.org.security.repository.ActorRepository;
import com.org.security.services.ActorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public ActorTO findById(Integer id) {

        Optional<Actor> act = actorRepository.findById(id);
        ActorTO actorTO = new ActorTO();
        act.ifPresent(actor -> BeanUtils.copyProperties(actor, actorTO));
        return actorTO;
    }

    @Override
    public ActorTO save(ActorTO actor) {
        Actor actorEntity = new Actor();
        BeanUtils.copyProperties(actor, actorEntity);
        actorEntity = actorRepository.save(actorEntity);
        BeanUtils.copyProperties(actorEntity, actor);
        return actor;
    }


}

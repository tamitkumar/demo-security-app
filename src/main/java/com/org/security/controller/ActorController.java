package com.org.security.controller;

import com.org.security.services.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_create')")
    public ResponseEntity<ActorTO> login(@RequestBody ActorTO actor) {
        return ResponseEntity.ok(actorService.save(actor));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<ActorTO> sayHello(@PathVariable Integer id) {
        return ResponseEntity.ok(actorService.findById(id));
    }
}

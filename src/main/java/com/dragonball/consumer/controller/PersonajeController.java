package com.dragonball.consumer.controller;

import com.dragonball.consumer.model.Personaje;
import com.dragonball.consumer.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepository;



    @PostMapping
    public ResponseEntity<Personaje> crearPersonaje(@RequestBody Personaje personaje) {
        Personaje personajeGuardado = personajeRepository.save(personaje);
        return ResponseEntity.ok(personajeGuardado);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPersonaje(@PathVariable Integer id) {
        return personajeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
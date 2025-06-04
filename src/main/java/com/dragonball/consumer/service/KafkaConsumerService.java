package com.dragonball.consumer.service;

import com.dragonball.consumer.model.Personaje;
import com.dragonball.consumer.repository.PersonajeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final PersonajeRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "DragonBall", groupId = "dragonball-group")
    public void consumirMensaje(String mensajeJson) {
        try {
            System.out.println("Mensaje recibido bruto: " + mensajeJson); // debug
            Personaje personaje = objectMapper.readValue(mensajeJson, Personaje.class);
            repository.save(personaje);
            System.out.println("Personaje guardado: " + personaje.getNombre());
        } catch (Exception e) {
            System.err.println("❌ Error al consumir mensaje:");
            e.printStackTrace();
        }
    }

}

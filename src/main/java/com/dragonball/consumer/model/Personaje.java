package com.dragonball.consumer.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dragon_ball")
@Data
public class Personaje {

    @Id
    private Integer id;

    private String nombre;
    private String ki;
    private String maxKi;
    private String raza;
    private String genero;
    private String descripcion;

    public Personaje() {}

    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() > 255) {
            this.descripcion = descripcion.substring(0, 255);
        } else {
            this.descripcion = descripcion;
        }
    }
}

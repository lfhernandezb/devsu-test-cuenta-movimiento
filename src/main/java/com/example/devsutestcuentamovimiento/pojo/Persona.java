package com.example.devsutestcuentamovimiento.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persona {
    private long personaId;
    private String nombre;
    private int generoId;
    private Genero genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}

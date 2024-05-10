package com.example.devsutestcuentamovimiento.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    private long ClienteId;
    private String contrasena;
    private boolean estado;
    private long personaId;
    private Persona persona;
}

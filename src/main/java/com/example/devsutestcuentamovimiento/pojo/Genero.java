package com.example.devsutestcuentamovimiento.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genero {
    private int generoId;
    private String abreviacion;
    private String descripcion;
}

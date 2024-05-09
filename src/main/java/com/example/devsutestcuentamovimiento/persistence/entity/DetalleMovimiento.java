package com.example.devsutestcuentamovimiento.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DetalleMovimiento {
    @Id
    @JsonProperty("Fecha")
    private String fecha;
    @JsonProperty("Cliente")
    private String cliente;
    @JsonProperty("Numero Cuenta")
    String numerocuenta;
    @JsonProperty("Tipo")
    String tipo;
    @JsonProperty("Saldo Inicial")
    long saldoinicial;
    @JsonProperty("Estado")
    boolean estado;
    @JsonProperty("Movimiento")
    long movimiento;
    @JsonProperty("Saldo Disponible")
    long saldodisponible;
}

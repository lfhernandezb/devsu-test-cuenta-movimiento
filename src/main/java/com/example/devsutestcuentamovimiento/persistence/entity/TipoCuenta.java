package com.example.devsutestcuentamovimiento.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tipo_cuenta")
public class TipoCuenta {
    @Id
    @Column(name = "id_tipo_cuenta")
    private int tipoCuentaId;
    private String descripcion;
}

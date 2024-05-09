package com.example.devsutestcuentamovimiento.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private long cuentaId;
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Column(name = "id_tipo_cuenta")
    private int tipoCuentaId;
    @ManyToOne
    @JoinColumn(name = "id_tipo_cuenta", insertable = false, updatable = false)
    private TipoCuenta tipoCuenta;
    private long saldo;
    private boolean estado;
    @Column(name = "id_cliente")
    private long clienteId;
}

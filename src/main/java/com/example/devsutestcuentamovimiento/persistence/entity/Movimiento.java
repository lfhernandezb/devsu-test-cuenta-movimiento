package com.example.devsutestcuentamovimiento.persistence.entity;

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
@Table(name = "movimiento")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private long movimientoId;
    private Date fecha;
    @Column(name = "id_tipo_movimiento")
    private int tipoMovimientoId;
    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento", insertable = false, updatable = false)
    private TipoMovimiento tipoMovimiento;
    private long valor;
    private long saldoInicial;
    @Column(name = "id_cuenta")
    private long cuentaId;
    //@ManyToOne
    //@JoinColumn(name = "id_cuenta", insertable = false, updatable = false)
    //private Cuenta cuenta;
}

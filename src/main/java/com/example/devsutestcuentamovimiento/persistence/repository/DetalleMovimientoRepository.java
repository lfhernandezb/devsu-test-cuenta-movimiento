package com.example.devsutestcuentamovimiento.persistence.repository;

import com.example.devsutestcuentamovimiento.persistence.entity.DetalleMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

/*
SELECT TO_CHAR(m.fecha, 'FMDD/FMMM/yyyy') AS Fecha, p.nombre AS Cliente,
c.numero_cuenta AS "Numero Cuenta", tc.descripcion AS Tipo,
c.estado AS Estado, m.saldo_inicial AS "Saldo Inicial",
CASE
  WHEN (tm.descripcion = 'Deposito') THEN m.valor
  WHEN (tm.descripcion = 'Retiro') THEN m.valor * -1
END
AS "Movimiento",
CASE
  WHEN (tm.descripcion = 'Deposito') THEN m.saldo_inicial + m.valor
  WHEN (tm.descripcion = 'Retiro') THEN m.saldo_inicial - m.valor
END
AS "Saldo Disponible"
FROM movimiento m
LEFT JOIN cuenta c ON m.id_cuenta = c.id_cuenta
LEFT JOIN cliente cl ON cl.id_cliente = c.id_cliente
LEFT JOIN persona p ON p.id_persona = cl.id_persona
LEFT JOIN tipo_cuenta tc ON tc.id_tipo_cuenta = c.id_tipo_cuenta
LEFT JOIN tipo_movimiento tm ON tm.id_tipo_movimiento = m.id_tipo_movimiento
WHERE c.id_cliente = 5
AND m.fecha >= '2024-01-30'
AND m.fecha <= '2024-07-30'
ORDER BY m.fecha ASC
*/

//@Repository
public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimiento, String> {
  //private final EntityManagerFactory emf;

    @Query(value=
            "SELECT " +
                    "TO_CHAR(m.fecha, 'FMDD/FMMM/yyyy') AS fecha, p.nombre AS cliente, \n" +
                    "c.numero_cuenta AS numeroCuenta, tc.descripcion AS tipo, \n" +
                    "c.estado AS estado, m.saldo_inicial AS saldoInicial, \n" +
                    "CASE\n" +
                    "  WHEN (tm.descripcion = 'Deposito') THEN m.valor\n" +
                    "  WHEN (tm.descripcion = 'Retiro') THEN m.valor * -1\n" +
                    "END\n" +
                    "AS \"movimiento\",\n" +
                    "CASE\n" +
                    "  WHEN (tm.descripcion = 'Deposito') THEN m.saldo_inicial + m.valor\n" +
                    "  WHEN (tm.descripcion = 'Retiro') THEN m.saldo_inicial - m.valor\n" +
                    "END\n" +
                    "AS saldoDisponible \n" +
                    "FROM movimiento m \n" +
                    "LEFT JOIN cuenta c ON m.id_cuenta = c.id_cuenta\n" +
                    "LEFT JOIN cliente cl ON cl.id_cliente = c.id_cliente\n" +
                    "LEFT JOIN persona p ON p.id_persona = cl.id_persona\n" +
                    "LEFT JOIN tipo_cuenta tc ON tc.id_tipo_cuenta = c.id_tipo_cuenta\n" +
                    "LEFT JOIN tipo_movimiento tm ON tm.id_tipo_movimiento = m.id_tipo_movimiento\n" +
                    "WHERE c.id_cliente = :clienteId\n" +
                    "AND m.fecha >= TO_DATE(:fechaInicial, 'YYYY-MM-DD')\n" +
                    "AND m.fecha <= TO_DATE(:fechaFinal, 'YYYY-MM-DD')\n" +
                    "ORDER BY m.fecha ASC", nativeQuery = true)
            List<DetalleMovimiento> queryBy(
                    @Param("clienteId") long clienteId,
                    @Param("fechaInicial") String fechaInicial,
                    @Param("fechaFinal") String fechaFinal);

}

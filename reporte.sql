SELECT TO_CHAR(m.fecha, 'FMDD/FMMM/yyyy') AS Fecha, p.nombre AS "Cliente", 
c.numero_cuenta AS numeroCuenta, tc.descripcion AS Tipo, 
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
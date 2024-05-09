package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.persistence.entity.DetalleMovimiento;
import com.example.devsutestcuentamovimiento.persistence.repository.DetalleMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DetalleMovimientoService {

    @Autowired
    private DetalleMovimientoRepository detalleMovimientoRepository;

    public List<DetalleMovimiento> getMovimientos(long clienteId, String fechaInicial, String fechaFinal) {
        return detalleMovimientoRepository.queryBy(clienteId, fechaInicial, fechaFinal);
        /*
        List<Map<String, Object>> reportDetails = detalleMovimientoRepository.queryBy(clienteId, fechaInicial, fechaFinal);
        List<DetalleMovimiento> list = new ArrayList<>();

        for (Map<String, Object> reportDetail : reportDetails) {

            reportDetail.forEach((k, v) -> {
                System.out.println("k: "+k);
                System.out.println("v: "+v);
            });

            DetalleMovimiento dm = new DetalleMovimiento();

            dm.setFecha((String) reportDetail.get("fecha"));
            dm.setCliente((String) reportDetail.get("cliente"));
            dm.setNumeroCuenta((String) reportDetail.get("numerocuenta"));
            dm.setTipo((String) reportDetail.get("tipo"));
            dm.setSaldoInicial(((BigInteger) reportDetail.get("saldoinicial")).longValue());
            dm.setEstado((boolean) reportDetail.get("estado"));
            dm.setMovimiento(((BigInteger) reportDetail.get("movimiento")).longValue());
            dm.setSaldoDisponible(((BigInteger) reportDetail.get("saldodisponible")).longValue());

            list.add(dm);

        }
        return list;

         */
    }
}

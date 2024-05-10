package com.example.devsutestcuentamovimiento.web.controller;

import com.example.devsutestcuentamovimiento.persistence.entity.DetalleMovimiento;
import com.example.devsutestcuentamovimiento.service.DetalleMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ReporteController {

    @Autowired
    private DetalleMovimientoService detalleMovimientoService;

    @GetMapping("/reportes/clienteId")
    public ResponseEntity<List<DetalleMovimiento>> getDetalleMovimientoByClienteId(
            @RequestParam("clienteId") long clienteId,
            @RequestParam("fechaInicial") String fechaInicial,
            @RequestParam("fechaFinal") String fechaFinal) throws ExecutionException, InterruptedException {

        return new ResponseEntity<List<DetalleMovimiento>>(detalleMovimientoService.getMovimientosByClienteId(
                clienteId, fechaInicial, fechaFinal), HttpStatus.OK);
    }

    @GetMapping("/reportes/nombreCliente")
    public ResponseEntity<List<DetalleMovimiento>> getDetalleMovimientoByNombreCliente(
            @RequestParam("nombreCliente") String nombreCliente,
            @RequestParam("fechaInicial") String fechaInicial,
            @RequestParam("fechaFinal") String fechaFinal) {

            return new ResponseEntity<List<DetalleMovimiento>>(detalleMovimientoService.getMovimientosByNombreCliente(
                    nombreCliente, fechaInicial, fechaFinal), HttpStatus.OK);

    }
}

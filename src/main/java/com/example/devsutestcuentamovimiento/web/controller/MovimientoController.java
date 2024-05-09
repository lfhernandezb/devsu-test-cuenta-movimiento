package com.example.devsutestcuentamovimiento.web.controller;

import com.example.devsutestcuentamovimiento.persistence.entity.Cuenta;
import com.example.devsutestcuentamovimiento.persistence.entity.Movimiento;
import com.example.devsutestcuentamovimiento.persistence.entity.TipoCuenta;
import com.example.devsutestcuentamovimiento.service.CuentaService;
import com.example.devsutestcuentamovimiento.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/all")
    public ResponseEntity<List<Movimiento>> getAll() {
        return new ResponseEntity<>(movimientoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getByClient(@PathVariable("id") long movimientoId) {
        return movimientoService.getById(movimientoId).map(
                movimiento -> new ResponseEntity<>(movimiento, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Movimiento> create(@RequestBody Movimiento movimiento) {
        System.out.println("create");

        Optional<Movimiento> mov = movimientoService.save(movimiento);

        return mov.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    /*@PatchMapping("/save")
    public ResponseEntity<Movimiento> update(@RequestBody Movimiento movimiento) {
        return new ResponseEntity<>(movimientoService.save(movimiento), HttpStatus.CREATED);
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") long movimientoId) {
        if (movimientoService.delete(movimientoId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}

package com.example.devsutestcuentamovimiento.web.controller;

import com.example.devsutestcuentamovimiento.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.devsutestcuentamovimiento.persistence.entity.Cuenta;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/all")
    public ResponseEntity<List<Cuenta>> getAll() {
        return new ResponseEntity<>(cuentaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getByClient(@PathVariable("id") long cuentaId) {
        return cuentaService.getById(cuentaId).map(
                cuenta -> new ResponseEntity<>(cuenta, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Cuenta> create(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.CREATED);
    }

    @PatchMapping("/save")
    public ResponseEntity<Cuenta> update(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") long cuentaId) {
        if (cuentaService.delete(cuentaId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}

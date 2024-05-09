package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.persistence.repository.TipoMovimientoCrudRepository;
import com.example.devsutestcuentamovimiento.persistence.entity.TipoMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimientoService {

    @Autowired
    private TipoMovimientoCrudRepository tipoMovimientoCrudRepository;

    public List<TipoMovimiento> getAll() {
        return (List<TipoMovimiento>) tipoMovimientoCrudRepository.findAll();
    }

    public Optional<TipoMovimiento> getById(int id) {
        return tipoMovimientoCrudRepository.findById(id);
    }

}

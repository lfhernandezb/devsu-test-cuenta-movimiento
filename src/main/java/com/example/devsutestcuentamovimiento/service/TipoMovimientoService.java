package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.error_handling.RestErrorHandler;
import com.example.devsutestcuentamovimiento.persistence.repository.TipoMovimientoCrudRepository;
import com.example.devsutestcuentamovimiento.persistence.entity.TipoMovimiento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimientoService {

    private static final Logger logger = LoggerFactory.getLogger(TipoMovimientoService.class);

    @Autowired
    private TipoMovimientoCrudRepository tipoMovimientoCrudRepository;

    public List<TipoMovimiento> getAll() {
        return (List<TipoMovimiento>) tipoMovimientoCrudRepository.findAll();
    }

    public Optional<TipoMovimiento> getById(int id) {
        return tipoMovimientoCrudRepository.findById(id);
    }

}

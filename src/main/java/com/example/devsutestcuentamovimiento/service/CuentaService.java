package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.error_handling.RestErrorHandler;
import com.example.devsutestcuentamovimiento.persistence.repository.CuentaCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.devsutestcuentamovimiento.persistence.entity.Cuenta;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private static final Logger logger = LoggerFactory.getLogger(CuentaService.class);
    @Autowired
    private CuentaCrudRepository cuentaCrudRepository;

    public List<Cuenta> getAll() {
        return (List<Cuenta>) cuentaCrudRepository.findAll();
    }

    public Optional<Cuenta> getById(long id) {
        return cuentaCrudRepository.findById(id);
    }

    public Cuenta save(Cuenta cuenta) {
        return cuentaCrudRepository.save(cuenta);
    }

    public boolean delete(long id) throws IllegalArgumentException {
        return getById(id).map((cuenta) -> {
            cuentaCrudRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}

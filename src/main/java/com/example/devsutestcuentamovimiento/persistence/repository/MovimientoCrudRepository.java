package com.example.devsutestcuentamovimiento.persistence.repository;

import com.example.devsutestcuentamovimiento.persistence.entity.Movimiento;
import org.springframework.data.repository.CrudRepository;

public interface MovimientoCrudRepository extends CrudRepository<Movimiento, Long> {
}

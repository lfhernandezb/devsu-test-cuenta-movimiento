package com.example.devsutestcuentamovimiento.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.devsutestcuentamovimiento.persistence.entity.Cuenta;

public interface CuentaCrudRepository extends CrudRepository<Cuenta, Long> {
}

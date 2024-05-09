package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.persistence.repository.MovimientoCrudRepository;
import com.example.devsutestcuentamovimiento.persistence.entity.Cuenta;
import com.example.devsutestcuentamovimiento.persistence.entity.Movimiento;
import com.example.devsutestcuentamovimiento.persistence.entity.TipoMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoCrudRepository movimientoCrudRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private TipoMovimientoService tipoMovimientoService;

    public List<Movimiento> getAll() {
        return (List<Movimiento>) movimientoCrudRepository.findAll();
    }

    public Optional<Movimiento> getById(long id) {
        return movimientoCrudRepository.findById(id);
    }

    public Optional<Movimiento> save(Movimiento movimiento) {

        int factorToMultiplyMovementValue = 1;

        System.out.println("movimiento: "+movimiento.toString());
        Cuenta cuenta = cuentaService.getById(movimiento.getCuentaId()).orElse(null);

        System.out.println("cuenta encotrada");

        TipoMovimiento tipoMovimiento = tipoMovimientoService.getById(movimiento.getTipoMovimientoId()).orElse(null);

        System.out.println("tipo movimiento encotrado");

        if (tipoMovimiento.getDescripcion().trim().equals("Retiro")) {
            factorToMultiplyMovementValue = -1;
        }
        else if (!tipoMovimiento.getDescripcion().trim().equals("Deposito")) {
            System.out.println("tipo movimiento no soportado");
            return Optional.empty();
        }

        if (cuenta.getSaldo() + factorToMultiplyMovementValue * movimiento.getValor() < 0) {
            System.out.println("saldo insuficiente");
            return Optional.empty();
        }

        movimiento.setSaldoInicial(cuenta.getSaldo());
        movimiento.setFecha(new Date());
        cuenta.setSaldo(cuenta.getSaldo() + factorToMultiplyMovementValue * movimiento.getValor());

        // begin transaction
        Movimiento mov = movimientoCrudRepository.save(movimiento);
        Cuenta cta = cuentaService.save(cuenta);
        // commit
        return Optional.of(mov);
    }

    public boolean delete(long id) {
        return getById(id).map((movimiento) -> {
            movimientoCrudRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}

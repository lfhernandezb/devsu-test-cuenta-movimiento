package com.example.devsutestcuentamovimiento.service;

import com.example.devsutestcuentamovimiento.error_handling.RestErrorHandler;
import com.example.devsutestcuentamovimiento.exception.AccountNotFoundException;
import com.example.devsutestcuentamovimiento.exception.InsufficientFundsException;
import com.example.devsutestcuentamovimiento.exception.MovementTypeNotFoundException;
import com.example.devsutestcuentamovimiento.exception.MovementTypeNotSupportedException;
import com.example.devsutestcuentamovimiento.persistence.repository.MovimientoCrudRepository;
import com.example.devsutestcuentamovimiento.persistence.entity.Cuenta;
import com.example.devsutestcuentamovimiento.persistence.entity.Movimiento;
import com.example.devsutestcuentamovimiento.persistence.entity.TipoMovimiento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoService.class);

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

    public Optional<Movimiento> save(Movimiento movimiento)
            throws AccountNotFoundException, MovementTypeNotFoundException, MovementTypeNotSupportedException, InsufficientFundsException {

        int factorToMultiplyMovementValue = 1;

        System.out.println("movimiento: "+movimiento.toString());
        Cuenta cuenta = cuentaService.getById(movimiento.getCuentaId()).orElse(null);

        if (cuenta == null) {
            throw new AccountNotFoundException("Cuenta no encontrada con id " + movimiento.getCuentaId());
        }

        System.out.println("cuenta encotrada");

        TipoMovimiento tipoMovimiento = tipoMovimientoService.getById(movimiento.getTipoMovimientoId()).orElse(null);

        if (tipoMovimiento == null) {
            throw new MovementTypeNotFoundException("Tipo movimiento no encontrada con id " + movimiento.getTipoMovimientoId());
        }

        System.out.println("tipo movimiento encotrado");

        if (tipoMovimiento.getDescripcion().trim().equals("Retiro")) {
            factorToMultiplyMovementValue = -1;
        }
        else if (!tipoMovimiento.getDescripcion().trim().equals("Deposito")) {
            throw new MovementTypeNotSupportedException("Tipo de movimiento no soportado " + tipoMovimiento.getDescripcion().trim());
        }

        if (cuenta.getSaldo() + factorToMultiplyMovementValue * movimiento.getValor() < 0) {
            throw new InsufficientFundsException("Saldo insuficiente para la operacion");
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

    public boolean delete(long id) throws IllegalArgumentException {
        return getById(id).map((movimiento) -> {
            movimientoCrudRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}

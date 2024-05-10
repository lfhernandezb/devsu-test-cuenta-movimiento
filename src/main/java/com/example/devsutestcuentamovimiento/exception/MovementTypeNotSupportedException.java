package com.example.devsutestcuentamovimiento.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class MovementTypeNotSupportedException extends RuntimeException {

    private static final long serialVersionUID = -1500970971344655842L;
    private int errorCode;
    private String errorMessage;

    public MovementTypeNotSupportedException(Throwable throwable) {
        super(throwable);
    }

    public MovementTypeNotSupportedException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public MovementTypeNotSupportedException(String msg) {
        super(msg);
    }

}

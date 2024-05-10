package com.example.devsutestcuentamovimiento.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class InsufficientFundsException extends RuntimeException {

    private static final long serialVersionUID = -1500970941344655842L;
    private int errorCode;
    private String errorMessage;

    public InsufficientFundsException(Throwable throwable) {
        super(throwable);
    }

    public InsufficientFundsException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public InsufficientFundsException(String msg) {
        super(msg);
    }

}

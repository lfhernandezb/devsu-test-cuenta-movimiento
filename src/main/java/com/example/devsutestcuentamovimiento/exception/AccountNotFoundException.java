package com.example.devsutestcuentamovimiento.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class AccountNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1500970911344655842L;
    private int errorCode;
    private String errorMessage;

    public AccountNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public AccountNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }
}

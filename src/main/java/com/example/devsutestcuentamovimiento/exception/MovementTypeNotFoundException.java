package com.example.devsutestcuentamovimiento.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class MovementTypeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1500970961344655842L;
    private int errorCode;
    private String errorMessage;

    public MovementTypeNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public MovementTypeNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public MovementTypeNotFoundException(String msg) {
        super(msg);
    }
}

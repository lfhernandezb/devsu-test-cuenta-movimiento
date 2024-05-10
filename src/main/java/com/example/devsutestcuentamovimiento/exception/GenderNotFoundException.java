package com.example.devsutestcuentamovimiento.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class GenderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1500970931344655842L;
    private int errorCode;
    private String errorMessage;

    public GenderNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public GenderNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public GenderNotFoundException(String msg) {
        super(msg);
    }

}

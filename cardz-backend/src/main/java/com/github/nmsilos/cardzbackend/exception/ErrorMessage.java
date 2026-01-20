package com.github.nmsilos.cardzbackend.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorMessage {

    private String method;
    private int status;
    private String statusMessage;
    private String message;

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String mensagem) {
        this.method = request.getMethod();
        this.status = status.value();
        this.statusMessage = status.getReasonPhrase();
        this.message = mensagem;
    }

}

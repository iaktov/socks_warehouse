package com.example.socks_warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ColorNotFound extends RuntimeException {
    public ColorNotFound() {
        super("Specified color not found");
    }
}

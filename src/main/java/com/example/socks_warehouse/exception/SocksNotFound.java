package com.example.socks_warehouse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SocksNotFound extends RuntimeException {
    public SocksNotFound() {
        super("Socks not found");
    }
}

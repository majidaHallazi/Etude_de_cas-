package com.example.voiture.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message) {

        super(message);
    }
}

package com.example.clothesshop.exeption;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption (String message){
        super(message);
    }
}

package com.example.knightmove.Exceptions;

public class NotAllFieldsFullException extends Exception {
    public NotAllFieldsFullException () {
        super ("All the fields need to be filled");
    }
}
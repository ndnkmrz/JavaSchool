package com.gamershop.admin.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String message){
        super(message);
    }
}

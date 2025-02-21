package com.in.One.Click_Contacts.Helpers;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(){
        super("Resource Not Found..");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}

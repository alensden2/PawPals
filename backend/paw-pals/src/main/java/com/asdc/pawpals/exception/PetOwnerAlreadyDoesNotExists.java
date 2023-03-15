package com.asdc.pawpals.exception;

public class PetOwnerAlreadyDoesNotExists extends Exception {
    public PetOwnerAlreadyDoesNotExists(){

    }

    public PetOwnerAlreadyDoesNotExists(String message){
        super(message);
    }

    public PetOwnerAlreadyDoesNotExists(Throwable cause){
        super(cause);
    }

    public PetOwnerAlreadyDoesNotExists(String message, Throwable cause){
        super(message,cause);
    }

    public PetOwnerAlreadyDoesNotExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


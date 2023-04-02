package com.asdc.pawpals.Enums;

public enum Status {
    CONFIRMED("CONFIRMED"), REJECTED("REJECTED"), PENDING("PENDING"), COMPLETED("COMPLETED");

    private String label;

    private Status(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}

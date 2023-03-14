package com.asdc.pawpals.Enums;

public enum AppointmentStatus {
    CONFIRMED("CONFIRMED"), REJECTED("REJECTED"), PENDING("PENDING");

    private String label;

    private AppointmentStatus(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}

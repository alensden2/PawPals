package com.asdc.pawpals.Enums;

public enum ProfileStatus {
    APPROVED("APPROVED"), REJECTED("REJECTED"), PENDING("PENDING");

    private String label;

    private ProfileStatus(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}

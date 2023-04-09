package com.asdc.pawpals.utils;

/**
 * This class serves as a utility class to hold constants used throughout the application.
 */
public class Constants {

    private Constants() {
    }

    public static final String DATE_FORMAT = "dd-MM-yyyy"; // 01-01-2023
    public static final String TIME_FORMAT = "HH:mm"; //24 hour format e.g. 23:59:59
    public static final Integer SLOT_DURATION = 30; //in Minutes

    public static final String[] STATUS = {"CONFIRMED", "REJECTED", "PENDING", "COMPLETED"};
    public static final String[] DAY_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
}

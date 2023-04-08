package com.asdc.pawpals.utils;

import com.asdc.pawpals.exception.InvalidImage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The CommonUtils class provides various utility methods that can be used throughout the project.
 * <p>
 * It includes methods for converting input objects to a target type, getting the day of the week from a date,
 * <p>
 * calculating the next and previous time slots based on a given time, and converting a multipart file image to a Byte array.
 */
public class CommonUtils {
    private static ObjectMapper objectMapper = ObjectMapperWrapper.getInstance();
    private static final Logger logger = LogManager.getLogger(CommonUtils.class);

    private CommonUtils() {
    }

    /**
     * This is a utility method that checks if an input object is strictly of a given target type. It uses the Jackson ObjectMapper library to convert the input object to the target type.
     *
     * @param input      - the object to be checked
     * @param targetType - the target class to check against
     * @return a boolean value indicating whether the input object is strictly of the target class
     * This method uses the Jackson ObjectMapper library to convert the input object to the target type, and returns a boolean value indicating whether the conversion succeeded.
     * If the conversion fails, an error message is logged to the logger.
     * This method is generic, meaning that it can be used with any class type.
     */
    public static <T> Boolean isStrictTypeOf(Object input, Class<T> targetType) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        T output = null;
        try {
            output =
                    objectMapper.convertValue(input, typeFactory.constructType(targetType));
        } catch (Exception e) {
            logger.info("invalid type ", targetType.toString());
        }
        return output != null;
    }


    /**
     * This is a utility method that checks if an input object is strictly of a given target type reference. It uses the Jackson ObjectMapper library to convert the input object to the target type.
     *
     * @param input      - the object to be checked
     * @param targetType - the target TypeReference to check against
     * @return a boolean value indicating whether the input object is strictly of the target TypeReference
     * This method uses the Jackson ObjectMapper library to convert the input object to the target type, and returns a boolean value indicating whether the conversion succeeded.
     * If the conversion fails, an error message is logged to the logger.
     * This method is generic, meaning that it can be used with any TypeReference.
     */
    public static <T> Boolean isStrictTypeOf(
            Object input,
            TypeReference<T> targetType
    ) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        T output = null;
        try {
            output =
                    objectMapper.convertValue(input, typeFactory.constructType(targetType));
        } catch (Exception e) {
            logger.info("invalid type ", targetType.toString());
        }
        return output != null;
    }

    /**
     * Will return day of the week from given date
     *
     * @param dateRaw
     * @return String - day of the week in full e.g. Monday, Tuesday ... Sunday
     */
    public static String getDayFromDate(String dateRaw) {
        String day = null;
        if (dateRaw != null && !dateRaw.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            try {
                Date date = format.parse(dateRaw);
                if (date != null) {
                    calendar.setTime(date);
                    logger.info(date.toString());
                    day = Constants.DAY_OF_WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1];
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return day;
    }

    public static String getNextSlotTime(String currSlotTime) {
        String nextSlotTime = null;
        if (currSlotTime != null && !currSlotTime.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
            try {
                Date time = timeFormat.parse(currSlotTime);
                calendar.setTime(time);
                calendar.add(Calendar.MINUTE, Constants.SLOT_DURATION);
                nextSlotTime = timeFormat.format(calendar.getTime()).toString();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return nextSlotTime;
    }

    /**
     * This is a utility method that takes a time string in a given format and returns the time of the previous time slot in a given duration.
     *
     * @param currSlotTime - the time string in the format specified in Constants.TIME_FORMAT
     * @return a String representing the time of the previous time slot in the format specified in Constants.TIME_FORMAT
     * This method takes a time string in the format specified in Constants.TIME_FORMAT, and subtracts the slot duration specified in Constants.SLOT_DURATION from the current time to get the time of the previous time slot.
     * The method then returns the time of the previous time slot as a String in the format specified in Constants.TIME_FORMAT.
     * If the input time string cannot be parsed into a valid Date object, an error message is logged to the logger and null is returned.
     * This method uses the Calendar and SimpleDateFormat classes to perform the date/time calculations.
     */

    public static String getPreviousSlotTime(String currSlotTime) {
        String prevSlotTime = null;
        if (currSlotTime != null && !currSlotTime.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
            try {
                Date time = timeFormat.parse(currSlotTime);
                calendar.setTime(time);
                calendar.add(Calendar.MINUTE, -Constants.SLOT_DURATION); //subtracting slot duration from curr time
                prevSlotTime = timeFormat.format(calendar.getTime()).toString();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return prevSlotTime;
    }

    /**
     * This is a utility method that converts a multipart file image to an array of bytes.
     *
     * @param image - the multipart file image to be converted
     * @return a Byte array representing the contents of the input image
     * @throws IOException  if there is an error reading the image data from the input stream
     * @throws InvalidImage if the input image is not a valid image file type
     *                      This method takes a multipart file image as input, checks if it is a valid image file type by checking its file extension against a regular expression that matches common image file types, and throws an InvalidImage exception if the file type is invalid.
     *                      If the file type is valid, the method creates a Byte array of the same length as the image byte data, reads the image byte data into the Byte array, and returns it.
     *                      If the input image is null or empty, an InvalidImage exception is thrown.
     *                      This method is commonly used to convert a multipart file image to a Byte array so that it can be stored in a database or sent over a network.
     */
    public static Byte[] getBytes(MultipartFile image)
            throws IOException, InvalidImage {
        if (image != null && !image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            if (!extension.matches("(?i)(jpg|jpeg|png|gif|bmp)")) {
                throw new InvalidImage("invalid image type uploaded");
            }
            Byte[] byteObjects = new Byte[image.getBytes().length];
            int i = 0;
            for (byte b : image.getBytes()) {
                byteObjects[i++] = b;
            }
            return byteObjects;
        } else {
            throw new InvalidImage("invalid image uploaded");
        }
    }
}

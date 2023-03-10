package com.asdc.pawpals.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.bcel.Const;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class CommonUtils {
    private static ObjectMapper objectMapper = ObjectMapperWrapper.getInstance();
    private static final Logger logger = LogManager.getLogger(CommonUtils.class);

    private CommonUtils(){

    }

    public static <T> Boolean isStrictTypeOf(Object input, Class<T> targetType) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        T output = null;
        try{
            output = objectMapper.convertValue(input, typeFactory.constructType(targetType));
        }
        catch(Exception e){
            logger.info("invalid type ", targetType.toString());
        }
        return output != null;
    }

    public static <T> Boolean isStrictTypeOf(Object input, TypeReference<T> targetType) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        T output = null;
        try{
            output = objectMapper.convertValue(input, typeFactory.constructType(targetType));
        }
        catch(Exception e){
            logger.info("invalid type ", targetType.toString());
        }
        return output != null;
    }

    /**
     * Will return day of the week from given date
     * @param date
     * @return String - day of the week in full e.g. Monday, Tuesday ... Sunday
     */
    public static String getDayFromDate(String dateRaw){   
        String day = null;
        if(dateRaw != null && !dateRaw.isEmpty()){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
            try {
                Date date = format.parse(dateRaw);
                if(date != null){
                    calendar.setTime(date);
                    logger.info(date.toString());
                    day = Constants.DAY_OF_WEEK[calendar.get(Calendar.DAY_OF_WEEK)-1];
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }   
        return day;
    }

    public static String getNextSlotTime(String currSlotTime){
        String nextSlotTime = null;
        if(currSlotTime != null && !currSlotTime.isEmpty()){
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

    public static String getPreviousSlotTime(String currSlotTime){
        String prevSlotTime = null;
        if(currSlotTime != null && !currSlotTime.isEmpty()){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
            try {
                Date time = timeFormat.parse(currSlotTime);
                calendar.setTime(time);
                calendar.add(Calendar.MINUTE, - Constants.SLOT_DURATION); //subtracting slot duration from curr time
                prevSlotTime = timeFormat.format(calendar.getTime()).toString();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return prevSlotTime;
    }
}

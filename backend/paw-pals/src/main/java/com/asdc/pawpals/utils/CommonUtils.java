package com.asdc.pawpals.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}

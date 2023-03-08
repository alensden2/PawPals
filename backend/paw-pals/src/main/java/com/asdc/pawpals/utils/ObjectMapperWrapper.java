package com.asdc.pawpals.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperWrapper {
    private static final Logger logger = LogManager.getLogger(ObjectMapperWrapper.class);
    private static ObjectMapperWrapper instance;
    private static ObjectMapper objectMapper;

    private ObjectMapperWrapper() {
        objectMapper = new ObjectMapper();
    }

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapperWrapper();
        }
        return objectMapper;
    }

}



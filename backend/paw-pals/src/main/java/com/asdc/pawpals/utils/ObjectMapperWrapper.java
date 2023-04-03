package com.asdc.pawpals.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.util.Pair;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperWrapper {
    private static final Logger logger = LogManager.getLogger(ObjectMapperWrapper.class);
    private static ObjectMapperWrapper instance;
    private static ObjectMapper objectMapper;

    private ObjectMapperWrapper() {
        objectMapper = new ObjectMapper();
        registerCustomDeserializer();
    }

    private static void registerCustomDeserializer(){
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Pair.class, new PairDeSerializer());
        objectMapper.registerModule(module);
    }

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapperWrapper();
        }
        return objectMapper;
    }

}

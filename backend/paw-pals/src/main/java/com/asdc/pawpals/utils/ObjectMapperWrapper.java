package com.asdc.pawpals.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.util.Pair;

/**
 * This is a utility class that provides a singleton instance of the Jackson ObjectMapper with a custom deserializer registered.
 * <p>
 * The ObjectMapper is a library for converting JSON to Java objects and vice versa. This class ensures that only one instance of the ObjectMapper is created during the lifetime of the application.
 * <p>
 * The class also registers a custom deserializer for the Spring Data Pair class.
 * <p>
 * The custom deserializer is defined in the PairDeserializer class.
 */
public class ObjectMapperWrapper {
    private static final Logger logger = LogManager.getLogger(ObjectMapperWrapper.class);
    private static ObjectMapperWrapper instance;
    private static ObjectMapper objectMapper;

    /**
     * Private constructor to prevent external instantiation of the class.
     * Initializes the ObjectMapper and registers the custom deserializer using the registerCustomDeserializer method.
     */
    private ObjectMapperWrapper() {
        objectMapper = new ObjectMapper();
        registerCustomDeserializer();
    }

    /**
     * Registers the custom deserializer for the Spring Data Pair class.
     * The custom deserializer is defined in the PairDeserializer class.
     */
    private static void registerCustomDeserializer() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Pair.class, new PairDeSerializer());
        objectMapper.registerModule(module);
    }

    /**
     * Returns the singleton instance of the ObjectMapper.
     * If the instance has not been created yet, a new instance is created using the private constructor.
     *
     * @return the singleton instance of the ObjectMapper
     */
    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapperWrapper();
        }
        return objectMapper;
    }
}




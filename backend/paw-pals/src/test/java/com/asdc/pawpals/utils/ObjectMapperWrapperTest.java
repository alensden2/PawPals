package com.asdc.pawpals.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperWrapperTest {

    @Test
    public void testGetInstance() throws JsonProcessingException {
        ObjectMapper objectMapper = ObjectMapperWrapper.getInstance();
        Assertions.assertNotNull(objectMapper, "ObjectMapper instance should not be null");
        Pair<String, Integer> pair = Pair.of("key", 1);
        String json = objectMapper.writeValueAsString(pair);
        Assertions.assertNotNull(json, "JSON string should not be null");
        Assertions.assertEquals("{\"first\":\"key\",\"second\":1}", json,
                "JSON string should match the expected value");
    }
}

package com.asdc.pawpals.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PairDeSerializerTest {

    @Test
    void testDeserialize() throws Exception {
        // create the test input JSON
        String jsonInput = "{\"first\":\"foo\", \"second\":\"bar\"}";

        // create the expected output pair
        Pair<String, String> expectedOutput = Pair.of("foo", "bar");

        // deserialize the JSON input using PairDeSerializer
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory(objectMapper);
        JsonParser parser = jsonFactory.createParser(jsonInput);
        Pair<String, String> actualOutput = new PairDeSerializer().deserialize(parser, objectMapper.getDeserializationContext());

        // assert that the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);
    }
}

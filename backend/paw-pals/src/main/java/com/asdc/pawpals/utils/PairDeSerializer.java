package com.asdc.pawpals.utils;

import java.io.IOException;

import org.springframework.data.util.Pair;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class PairDeSerializer extends JsonDeserializer<Pair<String, String>>{
    @Override
    public Pair<String,String> deserialize(JsonParser jp, DeserializationContext ctxt){
        Pair<String, String> output = null;
        ObjectCodec codec = jp.getCodec();
        try {
            JsonNode node = codec.readTree(jp);
            final String first = node.get("first").asText();
            final String second = node.get("second").asText();
            output = Pair.of(first, second);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }
}



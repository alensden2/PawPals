package com.asdc.pawpals.utils;

import java.io.IOException;

import org.springframework.data.util.Pair;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**

 This class deserializes a JSON string into a Pair object of type String and String.

 It extends the JsonDeserializer class from the Jackson library.
 */
public class PairDeSerializer extends JsonDeserializer<Pair<String, String>>{
    /**

     This method deserializes a JSON string into a Pair object of type String and String.
     @param jp - the JsonParser object to read the JSON string.
     @param ctxt - the DeserializationContext object.
     @return a Pair object of type String and String.
     @throws IOException if an error occurs during the deserialization process.
     */
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



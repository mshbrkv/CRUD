package com.maria.crudapp_participants.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;


@RequiredArgsConstructor
public class Serialization {

    private final ObjectMapper objectMapper;
//
//    public static void main(String[] args) throws Exception {
//        Serialization serialization = new Serialization(new ObjectMapper());
//        List<String> deserialization = serialization.deserialization("""
//                ["dfdf", "dfdfdf"]
//                """, new TypeReference<>() {
//        });
//        System.out.println(deserialization);
//        System.out.println(serialization.serialization(deserialization));
//    }

    public <T> String serialization(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);

    }

    public <T> T deserialization(String string, TypeReference<T> type) throws JsonProcessingException {
        return objectMapper.readValue(string, type);
    }
}

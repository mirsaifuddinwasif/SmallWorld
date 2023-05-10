package com.smallworld.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.Transaction;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Util {

    public static List<Transaction> readAllTransactions() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("transactions.json")));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Error reading transactions.json", e);
        }
    }
}
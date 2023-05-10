package com.smallworld;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("transactions.json")));
            ObjectMapper objectMapper = new ObjectMapper();
            List<Transaction> transactions = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
            TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher(transactions);
            double totalTransactionAmount = transactionDataFetcher.getTotalTransactionAmount();
            System.out.println("Total transaction amount: " + totalTransactionAmount);
        } catch (Exception e) {
            throw new RuntimeException("Error reading transactions.json", e);
        }
    }
}
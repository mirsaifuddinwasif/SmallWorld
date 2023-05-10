package com.smallworld;

import com.smallworld.util.Util;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = Util.readAllTransactions();
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher(transactions);
        double totalTransactionAmount = transactionDataFetcher.getTotalTransactionAmount();
        System.out.println("Total transaction amount: " + totalTransactionAmount);
    }
}
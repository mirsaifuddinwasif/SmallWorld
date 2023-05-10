package com.smallworld;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.smallworld.util.Util;

public class TransactionDataFetcher {
    private List<Transaction> transactions;

    public TransactionDataFetcher() {
        transactions = Util.readAllTransactions();
    }

    public TransactionDataFetcher(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getTotalTransactionAmount() {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalTransactionAmountSentBy(String senderFullName) {
        return transactions.stream()
                .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getMaxTransactionAmount() {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .max()
                .orElse(0);
    }

    public long countUniqueClients() {
        Set<String> uniqueClients = transactions.stream()
                .flatMap(transaction -> Stream.of(transaction.getSenderFullName(), transaction.getBeneficiaryFullName()))
                .collect(Collectors.toSet());
        return uniqueClients.size();
    }

    public boolean hasOpenComplianceIssues(String clientFullName) {
        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null)
                .filter(transaction -> !transaction.isIssueSolved())
                .anyMatch(transaction -> transaction.getSenderFullName().equals(clientFullName) || transaction.getBeneficiaryFullName().equals(clientFullName));
    }

    public Map<String, List<Transaction>> getTransactionsByBeneficiaryName() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getBeneficiaryFullName));
    }

    public Set<Integer> getUnsolvedIssueIds() {
        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null)
                .filter(transaction -> !transaction.isIssueSolved())
                .map(Transaction::getIssueId)
                .collect(Collectors.toSet());
    }

    public List<String> getAllSolvedIssueMessages() {
        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null)
                .filter(Transaction::isIssueSolved)
                .map(Transaction::getIssueMessage)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTop3TransactionsByAmount() {
        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public Optional<String> getTopSender() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getSenderFullName, Collectors.summingDouble(Transaction::getAmount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
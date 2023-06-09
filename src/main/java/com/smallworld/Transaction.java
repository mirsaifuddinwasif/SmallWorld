package com.smallworld;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private String mtn;
    private double amount;
    @JsonProperty("senderFullName")
    private String senderFullName;
    @JsonProperty("senderAge")
    private int senderAge;
    @JsonProperty("beneficiaryFullName")
    private String beneficiaryFullName;
    @JsonProperty("beneficiaryAge")
    private int beneficiaryAge;
    @JsonProperty("issueId")
    private Integer issueId;
    @JsonProperty("issueSolved")
    private boolean issueSolved;
    @JsonProperty("issueMessage")
    private String issueMessage;

    public Transaction(int mtn, double amount, String senderFullName, int senderAge, String beneficiaryFullName,
                       int beneficiaryAge, Integer issueId, boolean issueSolved, String issueMessage) {
        this.mtn = String.valueOf(mtn);
        this.amount = amount;
        this.senderFullName = senderFullName;
        this.senderAge = senderAge;
        this.beneficiaryFullName = beneficiaryFullName;
        this.beneficiaryAge = beneficiaryAge;
        this.issueId = issueId;
        this.issueSolved = issueSolved;
        this.issueMessage = issueMessage;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "mtn='" + mtn + '\'' +
                ", amount=" + amount +
                ", senderFullName='" + senderFullName + '\'' +
                ", senderAge=" + senderAge +
                ", beneficiaryFullName='" + beneficiaryFullName + '\'' +
                ", beneficiaryAge=" + beneficiaryAge +
                ", issueId=" + issueId +
                ", issueSolved=" + issueSolved +
                ", issueMessage='" + issueMessage + '\'' +
                '}';
    }
}
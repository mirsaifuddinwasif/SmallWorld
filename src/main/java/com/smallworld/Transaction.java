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
}

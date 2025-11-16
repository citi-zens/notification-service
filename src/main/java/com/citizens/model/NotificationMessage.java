package com.citizens.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {

    private String accountId;
    private String email;
    private Double amount;
    private String status;
    private String transactionId;
    private long timestamp;

}
package com.botola.apicmi.entities;

import com.botola.apicmi.enums.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ribSender;
    private String ribReceiver;
    private Double amount;
    private String currency;
    private LocalDateTime transactionDate;
    private TransactionStatus status;
    private String transactionNumber;
}

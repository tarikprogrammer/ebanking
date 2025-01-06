package com.botola.apicmi.dto;


import com.botola.apicmi.enums.TransactionStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequestDto {
    private Long id;

    private String ribSender;
    private String ribReceiver;
    private Double amount;
}

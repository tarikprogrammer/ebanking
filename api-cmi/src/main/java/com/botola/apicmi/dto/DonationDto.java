package com.botola.apicmi.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DonationDto {

    private Long id;
    private String fname;
    private String lname;
    private String email;
    private double price;
    private String rib;
}

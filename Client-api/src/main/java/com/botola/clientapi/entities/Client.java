package com.botola.clientapi.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String confirmEmail;
    private String phone;
    private String address;
    private String IdentityDocument;
    private String NIdentityDocument;
    private String TaxIdentificationNumber;
    private String dateBirth;
    private String CommercialRegisterRegistrationNumber;
    private String password;
    private boolean locked;
    @Lob
    private byte[] imageIdentity;


    @OneToOne(mappedBy = "client")
    private Otp otp;








}

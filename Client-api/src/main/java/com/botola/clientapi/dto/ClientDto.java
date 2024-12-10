package com.botola.clientapi.dto;


import com.botola.clientapi.entities.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record ClientDto(String id, @NotEmpty(message = "Enter your first name") String fname, @NotEmpty(message = "Enter your last name") String lname, @NotEmpty(message = "Enter your  email") @Email(message = "Enter a valid email") String email, @NotEmpty(message = "please Confirm your email")  String confirmEmail, @NotEmpty(message = "Enter your  phone") String phone, @NotEmpty(message = "Enter your address") String address, @NotEmpty(message = "Enter your date birth") String dateBirth, String IdentityDocument, String NIdentityDocument, String TaxIdentificationNumber, String password, String CommercialRegisterRegistrationNumber, byte[] file) {
    public static Client toEntity(ClientDto clientDto) {
        return Client.builder()
                .fname(clientDto.fname)
                .lname(clientDto.lname)
                .email(clientDto.email)
                .confirmEmail(clientDto.confirmEmail)
                .phone(clientDto.phone)
                .address(clientDto.address)
                .dateBirth(clientDto.dateBirth)
                .password(clientDto.password)
                .IdentityDocument(clientDto.IdentityDocument)
                .NIdentityDocument(clientDto.NIdentityDocument)
                .TaxIdentificationNumber(clientDto.TaxIdentificationNumber)
                .CommercialRegisterRegistrationNumber(clientDto.CommercialRegisterRegistrationNumber)
                .build();
    }
}

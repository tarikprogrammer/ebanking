package com.botola.clientapi.dto;


import com.botola.clientapi.entities.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record ClientDto(Long id, @NotEmpty(message = "Enter your first name") String fname, @NotEmpty(message = "Enter your last name") String lname, @NotEmpty(message = "Enter your  email") @Email(message = "Enter a valid email") String email, @NotEmpty(message = "please Confirm your email")  String confirmEmail, @NotEmpty(message = "Enter your  phone") String phone, @NotEmpty(message = "Enter your address") String address, @NotEmpty(message = "Enter your date birth") String dateBirth, String IdentityDocument, String NIdentityDocument, String TaxIdentificationNumber, String password, String CommercialRegisterRegistrationNumber, byte[] file) {
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
                .TaxIdentificationNumber(!clientDto.TaxIdentificationNumber.isEmpty() ? clientDto.TaxIdentificationNumber : "undefined")
                .CommercialRegisterRegistrationNumber(clientDto.CommercialRegisterRegistrationNumber.isEmpty() ? clientDto.CommercialRegisterRegistrationNumber : "undefined")
                .build();
    }

    public static ClientDto toDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .fname(client.getFname())
                .lname(client.getLname())
                .email(client.getEmail())
                .confirmEmail(client.getConfirmEmail())
                .phone(client.getPhone())
                .address(client.getAddress())
                .dateBirth(client.getDateBirth())
                .IdentityDocument(client.getIdentityDocument())
                .NIdentityDocument(client.getNIdentityDocument())
                .TaxIdentificationNumber(client.getTaxIdentificationNumber())
                .CommercialRegisterRegistrationNumber(client.getCommercialRegisterRegistrationNumber())
                .build();
    }
}

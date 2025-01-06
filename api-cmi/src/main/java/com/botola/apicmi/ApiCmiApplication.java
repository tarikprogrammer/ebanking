package com.botola.apicmi;

import com.botola.apicmi.entities.Facture;
import com.botola.apicmi.repositories.FactureRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;

@SpringBootApplication
@EnableFeignClients
public class ApiCmiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCmiApplication.class, args);
    }

    @Bean
    CommandLineRunner run(FactureRepo factureRepo) {
        return args -> {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Facture factureIAM = Facture.builder()

                    .factureCode("654577")
                    .factureName("Facture du Maroc Télecom")
                    .price(150)
                    .nonPaye(true)
                    .autoPaye(false)
                    .dateEcheance(calendar.getTime())
                    .build();

            factureRepo.save(factureIAM);

            Facture factureOrange = Facture.builder()

                    .factureCode("664577")
                    .factureName("Facture du Orange")
                    .price(100)
                    .nonPaye(true)
                    .autoPaye(false)
                    .dateEcheance(calendar.getTime())
                    .build();

            factureRepo.save(factureOrange);

            Facture factureINWI = Facture.builder()

                    .factureCode("674577")
                    .factureName("Facture du INWI Maroc")
                    .price(200)
                    .nonPaye(true)
                    .autoPaye(false)
                    .dateEcheance(calendar.getTime())
                    .build();
            factureRepo.save(factureINWI);


            Facture facturelydec = Facture.builder()

                    .factureCode("684577")
                    .factureName("Facture du Lydec")
                    .price(100)
                    .nonPaye(true)
                    .autoPaye(false)
                    .dateEcheance(calendar.getTime())
                    .build();

            factureRepo.save(facturelydec);

            Facture factureAmendis = Facture.builder()

                    .factureCode("694577")
                    .factureName("Facture du Amendis Tangier")
                    .price(250)
                    .nonPaye(true)
                    .autoPaye(false)
                    .dateEcheance(calendar.getTime())
                    .build();
            factureRepo.save(factureAmendis);

            Facture factureRedal = Facture.builder()

                    .factureCode("704577")
                    .factureName("Facture du Redal")
                    .price(50)
                    .nonPaye(true)
                    .autoPaye(false)
                    .dateEcheance(calendar.getTime())
                    .build();

            factureRepo.save(factureRedal);
        };
    }

}

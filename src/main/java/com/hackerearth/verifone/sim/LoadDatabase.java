package com.hackerearth.verifone.sim;

import com.hackerearth.verifone.sim.SimCard.SimCardBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SimCardRepository repository) {
        return args -> {
            SimCard sim1 = new SimCardBuilder()
                    .simCardNo(1)
                    .mobileNo("987654321")
                    .status("ACTIVE")
                    .expiryDate(LocalDate.now().plusDays(31))
                    .stateOfRegistration("Maharashtra")
                    .kyc("COMPLETE")
                    .telecomProvider("Airtel")
                    .fullName("Somaditya Basak")
                    .build();

            SimCard sim2 = new SimCardBuilder()
                    .simCardNo(2)
                    .mobileNo("123456789")
                    .status("EXPIRED")
                    .expiryDate(LocalDate.now().plusDays(29))
                    .stateOfRegistration("West Bengal")
                    .kyc("PENDING")
                    .telecomProvider("Jio")
                    .fullName("Jon Doe")
                    .build();

            log.info("Preloading " + repository.save(sim1));
            log.info("Preloading " + repository.save(sim2));
        };
    }
}

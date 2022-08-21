package com.hackerearth.verifone.sim;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimCardService {

    private final SimCardRepository repository;

    public SimCardService(SimCardRepository repository) {
        this.repository = repository;
    }

    SimCard addNewSimCard(SimCard simCard) {
        return repository.save(simCard);
    }

    List<SimCard> listAllSimCards() {
        return repository.findAll();
    }

    SimCard updateSim(SimCard update, Long simCardNo) {
        return repository.findById(simCardNo)
                .map(simCard -> {
                    simCard.setSimCardNo(update.getSimCardNo());
                    simCard.setMobileNo(update.getMobileNo());
                    simCard.setStatus(update.getStatus());
                    simCard.setExpiryDate(update.getExpiryDate());
                    simCard.setStateOfRegistration(update.getStateOfRegistration());
                    simCard.setKyc(update.getKyc());
                    simCard.setTelecomProvider(update.getTelecomProvider());
                    simCard.setFullName(update.getFullName());

                    return repository.save(simCard);
                }).orElseThrow();
    }

    void removeSim(Long simCardNo) {
        repository.deleteById(simCardNo);
    }

    List<SimCard> listDueForRenewal() {
        return repository.findAll().stream()
                .filter(sim -> sim.getExpiryDate().isBefore(LocalDate.now().plusDays(30)))
                .collect(Collectors.toList());
    }

    SimCard renewSim(Long id) {
        SimCard sim = repository.findById(id).get();
        RestTemplate restTemplate = new RestTemplate();
        boolean verified = Boolean.TRUE.equals(restTemplate
                .getForObject("http://localhost:8080/verify/" + id, Boolean.class));

        if (verified) {
            sim.setStatus("ACTIVE");
            sim.setExpiryDate(LocalDate.now().plusDays(180));

            return repository.save(sim);
        }

        return sim;
    }
}

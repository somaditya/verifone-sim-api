package com.hackerearth.verifonesimapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class SimCardController {

    @Autowired
    private final SimCardRepository repository;

    public SimCardController(SimCardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public ResponseEntity root() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add")
    public SimCard add(@RequestBody SimCard newSim) {
        return repository.save(newSim);
    }

    @GetMapping("/listall")
    public List<SimCard> listAll() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public SimCard update(@RequestBody SimCard updatedSim, @PathVariable Long id) {
        return repository.findById(id)
                .map(simCard -> {
                    simCard.setSimCardNo(updatedSim.getSimCardNo());
                    simCard.setMobileNo(updatedSim.getMobileNo());
                    simCard.setStatus(updatedSim.getStatus());
                    simCard.setExpiryDate(updatedSim.getExpiryDate());
                    simCard.setStateOfRegistration(updatedSim.getStateOfRegistration());
                    simCard.setKyc(updatedSim.getKyc());
                    simCard.setTelecomProvider(updatedSim.getTelecomProvider());
                    simCard.setFullName(updatedSim.getFullName());

                    return repository.save(simCard);
                }).orElseGet(() -> {
                    updatedSim.setSimCardNo(id);
                    return repository.save(updatedSim);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/to-renew")
    public List<SimCard> toRenew() {
        return repository.findAll().stream()
                .filter(sim -> sim.getExpiryDate().isBefore(LocalDate.now().plusDays(30)))
                .toList();
    }

    @PutMapping("/renew/{id}")
    public SimCard renew(@PathVariable Long id) {
        SimCard sim = repository.findById(id).get();
        RestTemplate restTemplate = new RestTemplate();
        boolean verified = Boolean.TRUE.equals(restTemplate.getForObject("http://localhost:8080/verify/" + id, Boolean.class));

        if (verified) {
            sim.setStatus("ACTIVE");
            sim.setExpiryDate(LocalDate.now().plusDays(180));

            return repository.save(sim);
        }

        return sim;
    }
}

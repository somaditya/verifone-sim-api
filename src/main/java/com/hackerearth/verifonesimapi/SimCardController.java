package com.hackerearth.verifonesimapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimCardController {

    @Autowired
    private final SimCardRepository repository;

    public SimCardController(SimCardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public ResponseEntity<SimCard> root() {
        return new ResponseEntity<>(null, HttpStatus.OK);
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
}

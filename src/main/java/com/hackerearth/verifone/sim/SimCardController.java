package com.hackerearth.verifone.sim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimCardController {

    @Autowired
    private final SimCardService simCardService;

    public SimCardController(SimCardService simCardService) {
        this.simCardService = simCardService;
    }

    @GetMapping("/")
    ResponseEntity root() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add")
    SimCard add(@RequestBody SimCard newSim) {
        return simCardService.addNewSimCard(newSim);
    }

    @GetMapping("/listall")
    List<SimCard> listAll() {
        return simCardService.listAllSimCards();
    }

    @PutMapping("/{id}")
    SimCard update(@RequestBody SimCard newSimData, @PathVariable Long id) {
        return simCardService.updateSim(newSimData, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        simCardService.removeSim(id);
    }

    @GetMapping("/to-renew")
    List<SimCard> toRenew() {
        return simCardService.listDueForRenewal();
    }

    @PutMapping("/renew/{id}")
    SimCard renew(@PathVariable Long id) {
        return simCardService.renewSim(id);
    }
}

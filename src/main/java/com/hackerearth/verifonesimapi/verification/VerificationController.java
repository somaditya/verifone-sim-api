package com.hackerearth.verifonesimapi.verification;

import com.hackerearth.verifonesimapi.SimCard;
import com.hackerearth.verifonesimapi.SimCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VerificationController {

    @Autowired
    private final SimCardRepository repository;

    public VerificationController(SimCardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/verify/{id}")
    public boolean verify(@PathVariable Long id) {
        SimCard sim = repository.findById(id).get();
        
        return sim.getKyc().equals("COMPLETE");
    }
}

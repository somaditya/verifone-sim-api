package com.hackerearth.verifonesimapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/")
}

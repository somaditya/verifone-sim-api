package com.hackerearth.verifonesimapi;

import com.hackerearth.verifonesimapi.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimCardRepository extends JpaRepository<SimCard, Long> {
}
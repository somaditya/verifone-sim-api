package com.hackerearth.verifone.sim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SimCard {
    @Id
    @GeneratedValue
    private long simCardNo;
    private String mobileNo;
    private String status;
    private LocalDate expiryDate;
    private String stateOfRegistration;
    private String kyc;
    private String telecomProvider;
    private String fullName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimCard simCard = (SimCard) o;
        return simCardNo == simCard.simCardNo
                && mobileNo.equals(simCard.mobileNo)
                && status.equals(simCard.status)
                && expiryDate.equals(simCard.expiryDate)
                && stateOfRegistration.equals(simCard.stateOfRegistration)
                && kyc.equals(simCard.kyc)
                && telecomProvider.equals(simCard.telecomProvider)
                && fullName.equals(simCard.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simCardNo,
                mobileNo,
                status,
                expiryDate,
                stateOfRegistration,
                kyc,
                telecomProvider,
                fullName);
    }
}

package com.ycompany.claims.dto;

import java.math.BigDecimal;

public class SurveyClaimRequestDto {
    String claimId;
    BigDecimal amount;

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

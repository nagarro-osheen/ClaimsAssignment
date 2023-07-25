package com.ycompany.claims.events;

import com.ycompany.claims.entity.ClaimEntity;
import com.ycompany.claims.enums.ClaimStatusEnum;
import com.ycompany.claims.repository.ClaimsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@KafkaListener(id = "multiGroup", topics = { "claim_approved" })
public class ApprovedClaimConsumer {

    @Autowired ClaimsRepository claimsRepository;
    @KafkaHandler
    public void approved(String claimId) {
        System.out.println("Received approved message for claim id: " + claimId);
        Optional<ClaimEntity> byId = claimsRepository.findById(claimId);
        if(byId.isPresent()){
            ClaimEntity entity = byId.get();
            entity.setStatus(ClaimStatusEnum.APPROVED);
            entity.setApproved(true);
            claimsRepository.save(entity);

        }
    }
}

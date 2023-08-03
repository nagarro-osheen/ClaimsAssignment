package com.ycompany.claims.controller;


import com.ycompany.claims.dto.ClaimApproveDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycompany.claims.dto.ClaimRequestDto;
import com.ycompany.claims.dto.SurveyClaimRequestDto;
import com.ycompany.claims.entity.ClaimEntity;
import com.ycompany.claims.enums.ClaimStatusEnum;
import com.ycompany.claims.service.ClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired KafkaTemplate kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired ClaimService claimService;

    private static final Logger LOG = LoggerFactory.getLogger(ClaimController.class);

    @PostMapping("/create")
    public ClaimEntity createClaim(@RequestBody ClaimRequestDto claimRequest){
        // resttemplate call to create claim workflow instance.
        return claimService.createClaimForPolicy(claimRequest);


    }

    @GetMapping("/status/{status}")
    public List<ClaimEntity> getAllClaimsByStatus(@PathVariable String status){
        return claimService.getClaimsWithStatus(ClaimStatusEnum.valueOf(status));
    }

    @GetMapping("/{claimId}")
    public ClaimEntity getAllNewClaims(@PathVariable String claimId){
        return claimService.getClaimForId(claimId);
    }

    @GetMapping("/user/{userId}")
    public List<ClaimEntity> getClaimsForUserID(@PathVariable String userId){
        return claimService.getClaimsForUserName(userId);
    }


    @PostMapping("/survey")
    public boolean surveyClaim(@RequestBody SurveyClaimRequestDto request){
        claimService.surveyClaim(request.getClaimId(), request.getAmount());
        return true;
    }

    @PostMapping("/approve/{taskId}")
    public boolean approveClaim(@PathVariable String taskId){
        this.kafkaTemplate.send("user_approve_task", taskId);
        try{
        this.initiatePaymentRelease(taskId);}catch(JsonProcessingException e){
            LOG.error("Exception in JSON Processing");
        }
        return true;
    }

    private void initiatePaymentRelease(String taskId) throws JsonProcessingException {
     String uri = "http://localhost:8080/rest/GOV.UK+Pay+API/1.0.3/v1/payments";
     RestTemplate restTemplate = new RestTemplate();
     ResponseEntity<String> response = restTemplate.postForEntity(uri,taskId,String.class);
     String responseJson = response.getBody();
     ObjectMapper mapper = new ObjectMapper();
     JsonNode node = mapper.readTree(responseJson);
     String email = node.get("email").asText();
     this.kafkaTemplate.send("claim_payment_initiated", email);
     }
}

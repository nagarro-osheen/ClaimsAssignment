package com.ycompany.claims.controller;


import com.ycompany.claims.dto.ClaimApproveDto;
import com.ycompany.claims.dto.ClaimRequestDto;
import com.ycompany.claims.dto.SurveyClaimRequestDto;
import com.ycompany.claims.entity.ClaimEntity;
import com.ycompany.claims.enums.ClaimStatusEnum;
import com.ycompany.claims.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/claim")
public class CreateClaimController {

    @Autowired KafkaTemplate kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;



    @Autowired ClaimService claimService;


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
        return true;
    }



}

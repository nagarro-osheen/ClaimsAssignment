package com.ycompany.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClaimApprovedDelegate implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(ClaimApprovedDelegate.class);
@Autowired KafkaTemplate kafkaTemplate;



    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOG.info("Approved claim");
        kafkaTemplate.send("claim_approved", delegateExecution.getVariable("claimId"));
    }


}
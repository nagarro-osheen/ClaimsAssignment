package com.ycompany.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculateClaimAmountDelegate implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(CalculateClaimAmountDelegate.class);




    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String claimId = (String) delegateExecution.getVariable("claimId");
        BigDecimal amount = BigDecimal.valueOf((double)delegateExecution.getVariable("amount"));
        LOG.info(" {} adding claim id {} for amount {}", delegateExecution.getProcessInstanceId(), claimId, amount);
        //can hit any api to get data here. for now it is just printing variables
    }

}
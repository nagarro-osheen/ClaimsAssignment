package com.ycompany.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClaimRejectedDelegate implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(ClaimRejectedDelegate.class);




    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOG.info("Approved claim");
    }


}
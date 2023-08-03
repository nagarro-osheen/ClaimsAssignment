package com.ycompany.event;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@KafkaListener(id = "multiGroup", topics = { "user_approve_task" })
public class UserApprovedClaimConsumer {

    @Autowired TaskService taskService;
    @KafkaHandler
    public void approved(String taskId) {
        System.out.println("Received taskId for approval by user: " + taskId);
        Task userTask = taskService.createTaskQuery().taskId(taskId).singleResult();


            //add checks for pending status for marking it complete
        if(userTask != null) {
            System.out.println("taskId is in status: " + userTask.getDelegationState());

            Map<String, Object> approvalDecision = new HashMap<>();
            //TODO implement an object receiver for false scenario.

            approvalDecision.put("approve", true);
            taskService.complete(taskId, approvalDecision);
        }
        else{
            System.out.println("task not found for approval by user: " + taskId);
        }
    }
}

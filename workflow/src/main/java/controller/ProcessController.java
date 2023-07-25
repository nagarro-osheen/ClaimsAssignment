//package controller;
//
//import org.camunda.bpm.engine.RuntimeService;
//import org.camunda.bpm.engine.runtime.ProcessInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class ProcessController {
//
//    @Autowired
//    RuntimeService runtimeService;
//
//    @PostMapping
//    @RequestMapping("/process/claim")
//    public ResponseEntity<String> createProcessInstance(){
//        Map<String,Object> quoteObjectMap = new HashMap<>();
//        quoteObjectMap.put("policyId", "123");
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("workflow-process", quoteObjectMap);
//        return new ResponseEntity<>(processInstance.getId(), HttpStatus.OK);
//    }
//
//    @GetMapping
//    @RequestMapping("/process")
//    public ResponseEntity<String> getProcessInstance(){
//        return new ResponseEntity<>("12345", HttpStatus.OK);
//    }
//
//
//
//}

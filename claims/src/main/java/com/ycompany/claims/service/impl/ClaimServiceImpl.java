package com.ycompany.claims.service.impl;

import com.ycompany.claims.dto.ClaimRequestDto;
import com.ycompany.claims.dto.WorkflowProcessStartResponseDto;
import com.ycompany.claims.dto.WorkflowProcessStartVariablesDto;
import com.ycompany.claims.dto.WorkflowUserTaskDto;
import com.ycompany.claims.entity.ClaimEntity;
import com.ycompany.claims.enums.ClaimStatusEnum;
import com.ycompany.claims.repository.ClaimsRepository;
import com.ycompany.claims.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${workflow.service.base.url}")
	private String workflowServiceBaseUrl;

	@Autowired ClaimsRepository claimsRepository;

	@Override public ClaimEntity createClaimForPolicy(ClaimRequestDto req) {
		ClaimEntity claimEntity = new ClaimEntity();
		claimEntity.setPolicyNumber(req.getPolicyNumber());
		claimEntity.setUsername(req.getUsername());
		claimEntity.setStatus(ClaimStatusEnum.CREATED);
		ClaimEntity saved = claimsRepository.save(claimEntity);
		return saved;
	}

	@Override public List<ClaimEntity> getClaimsWithStatus(ClaimStatusEnum status) {
		List<ClaimEntity> claims = claimsRepository.findAllByStatus(status);
		//surveyed is marked after process started..
		if(ClaimStatusEnum.SURVEYED.equals(status)){
			//check task id on which they are waiting..
			claims.forEach(claim->{

				ResponseEntity<List<WorkflowUserTaskDto>> response = restTemplate.exchange(workflowServiceBaseUrl + "/task?processInstanceId=" + claim.getWorkflowProcessId(), HttpMethod.GET, null,
						new ParameterizedTypeReference<List<WorkflowUserTaskDto>>() {
						});
				if(response != null){
					List<WorkflowUserTaskDto> taskList = response.getBody();
					if(taskList != null && taskList.size() > 0){
						claim.setWorkflowApprovalTaskId(taskList.get(0).getId());
					}
				}

				//check name shoud be "user task approval"
			});
		}
		return claims;
	}

	@Override public ClaimEntity getClaimForId(String uuid) {
		Optional<ClaimEntity> byId = claimsRepository.findById(uuid);
		return byId.isPresent()? byId.get(): null ;
	}

	@Override public List<ClaimEntity> getClaimsForUserName(String userId) {
		return claimsRepository.findAllByUsername(userId);
	}

	@Override public void surveyClaim(String uuid, BigDecimal amount) {
		Optional<ClaimEntity> claimOpt = claimsRepository.findById(uuid);
		if(claimOpt.isPresent()){
			ClaimEntity claim = claimOpt.get();
			claim.setStatus(ClaimStatusEnum.SURVEYED);
			claim.setAmount(amount);

			WorkflowProcessStartVariablesDto body = new WorkflowProcessStartVariablesDto();
			Map<String, Map<String, Object>> variables = new HashMap<>();
			Map<String, Object> claimId = new HashMap<>();
			claimId.put("value", claim.getUuid());
			claimId.put("type", "String" );
			Map<String, Object> amountMap = new HashMap<>();
			amountMap.put("value", claim.getAmount());
			amountMap.put("type", "Double" );
			variables.put("claimId", claimId);
			variables.put("amount", amountMap);
			body.setVariables(variables);

			HttpEntity<WorkflowProcessStartVariablesDto> request = new HttpEntity<>(body);
			WorkflowProcessStartResponseDto o = restTemplate.postForObject(workflowServiceBaseUrl + "/process-definition/key/workflow-process/start", request, WorkflowProcessStartResponseDto.class);
			System.out.println(o.getId());
			claim.setWorkflowProcessId(o.getId());
			claimsRepository.save(claim);
		}
	}

}

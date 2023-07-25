package com.ycompany.claims.service;

import com.ycompany.claims.dto.ClaimRequestDto;
import com.ycompany.claims.entity.ClaimEntity;
import com.ycompany.claims.enums.ClaimStatusEnum;

import java.math.BigDecimal;
import java.util.List;

public interface ClaimService {
	ClaimEntity createClaimForPolicy(ClaimRequestDto req);

	List<ClaimEntity> getClaimsWithStatus(ClaimStatusEnum created);

	ClaimEntity getClaimForId(String uuid);

	List<ClaimEntity> getClaimsForUserName(String userId);

	void surveyClaim(String uuid, BigDecimal amount);

}

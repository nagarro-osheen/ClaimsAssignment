package com.ycompany.claims.repository;

import com.ycompany.claims.entity.ClaimEntity;
import com.ycompany.claims.enums.ClaimStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimsRepository extends JpaRepository<ClaimEntity, String> {

	List<ClaimEntity> findAllByStatus(ClaimStatusEnum status);
	List<ClaimEntity> findAllByUsername(String username);
}

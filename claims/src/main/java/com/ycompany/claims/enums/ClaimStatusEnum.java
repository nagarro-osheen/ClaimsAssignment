package com.ycompany.claims.enums;
public enum ClaimStatusEnum {

	CREATED("created"),
	SURVEYED("surveyed"),//lets say surveyed claims have amount.
	WAITING_FOR_APPROVAL("waiting_for_approval"),
	APPROVED("approved"),
	CLOSED("closed");

	private ClaimStatusEnum(String displayName) {
		this.displayName = displayName;
	}

	private String displayName;

	public String getDisplayName() {
		return displayName;
	}



}
package com.isaiah.enums;

public enum taskStatusEnum {
	
	INPROGRESS("In Progress"), COMPLETED("Completed"), FAILED("Failed"), TORESCHEDULE("To Reschedule"), ARCHIVED("Archived");

	private taskStatusEnum(String string) {
		// TODO Auto-generated constructor stub
		this.status = string;
	}
	
	private final String status;
	
	public String getStatusString() {
		return status;
	}

}

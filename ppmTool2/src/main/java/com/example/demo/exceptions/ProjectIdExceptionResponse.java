package com.example.demo.exceptions;


public class ProjectIdExceptionResponse {
	private String ProjectIdentifier;

	public ProjectIdExceptionResponse(String projectIdentifier) {
		super();
		ProjectIdentifier = projectIdentifier;
	}

	public String getProjectIdentifier() {
		return ProjectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		ProjectIdentifier = projectIdentifier;
	}


}

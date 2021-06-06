package com.example.demo.exceptions;

public class ProjectNotFoundExceptionResponse {
    private String projectNotFound;
    public ProjectNotFoundExceptionResponse(String message) {
        super();
        this.projectNotFound = message;

    }

    public String getProjectNotFound() {
        return projectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}

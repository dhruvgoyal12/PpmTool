package com.example.demo.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	@NotBlank(message="Project name is required")
	private String projectName;
	
	
	@Column(updatable = false, unique = true)
	@NotBlank(message="Project identifier is required")
	@Size(min=4, max=5, message = "Please use 4 to 5 characters")
	private String projectIdentifier;
	
	
	@Column
	@NotBlank(message = "Project description is required")
	private String description;
	
	
	@Column
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date startDate;
	
	@Column
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date endData;
	
	@Column
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date createdAt;
	
	@Column
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updatedAt;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")

	private Backlog backlog;



	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndData() {
		return endData;
	}

	public void setEndData(Date endData) {
		this.endData = endData;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public Project(){}
	
	
	
	
}

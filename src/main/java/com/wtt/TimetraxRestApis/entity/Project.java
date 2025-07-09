package com.wtt.TimetraxRestApis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Project", uniqueConstraints = { @UniqueConstraint(columnNames = "ProjectName") })
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;

	@ManyToOne // Assuming Project is associated with a Customer)
	@JoinColumn(name = "CustomerId", nullable = false)
	private Customer customer; // Consider using @ManyToOne if there's a Customer entity.

	@Column(length = 200, nullable = false, unique = true,name = "ProjectName")
	private String projectName;

	@Column(length = 500, name = "ProjectDescription")
	private String projectDescription;

	@Column(nullable = false, name = "Active")
	private Boolean active;

	@Column(name = "CreatedBy")
	private Integer createdBy;

	@Column(name = "ModifiedBy")
	private Integer modifiedBy;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(String projectName, String projectDescription, Boolean active, Integer createdBy,
			Integer modifiedBy) {
		super();

		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.active = active;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	// Getters and Setters

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDescription="
				+ projectDescription + ", active=" + active + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}

}

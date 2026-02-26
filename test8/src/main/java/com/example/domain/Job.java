package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Job domain entity representing a job position.
 * Contains information about the job including title, company, salary, and description.
 */
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job title is required")
    @Size(max = 100, message = "Job title must not exceed 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must not exceed 100 characters")
    @Column(name = "company", nullable = false, length = 100)
    private String company;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    /**
     * Default constructor required by JPA.
     */
    public Job() {
    }

    /**
     * Constructor with all fields.
     *
     * @param title the job title
     * @param company the company name
     * @param salary the salary amount
     * @param description the job description
     */
    public Job(String title, String company, BigDecimal salary, String description) {
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.description = description;
    }

    /**
     * Gets the job ID.
     *
     * @return the job ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the job ID.
     *
     * @param id the job ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the job title.
     *
     * @return the job title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the job title.
     *
     * @param title the job title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company name.
     *
     * @param company the company name to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Gets the salary amount.
     *
     * @return the salary amount
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets the salary amount.
     *
     * @param salary the salary amount to set
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Gets the job description.
     *
     * @return the job description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the job description.
     *
     * @param description the job description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Compares this job to another object for equality.
     * Two jobs are considered equal if they have the same ID.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    /**
     * Returns a hash code value for the job.
     * The hash code is based on the job ID.
     *
     * @return a hash code value for this job
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the job.
     *
     * @return a string representation of the job
     */
    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", description='" + description + '\'' +
                '}';
    }
}
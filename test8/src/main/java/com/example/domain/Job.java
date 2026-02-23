package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Objects;

/**
 * Job domain entity representing a job position in the system.
 */
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private Double salary;

    /**
     * Default constructor for JPA.
     */
    public Job() {
    }

    /**
     * Constructor with all fields.
     *
     * @param title the job title
     * @param company the company name
     * @param salary the salary amount
     */
    public Job(String title, String company, Double salary) {
        this.title = title;
        this.company = company;
        this.salary = salary;
    }

    /**
     * Gets the job ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the job ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the job title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the job title.
     *
     * @param title the title to set
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
     * @return the salary
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Sets the salary amount.
     *
     * @param salary the salary to set
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * Compares this job to another object for equality.
     * Equality is based on the ID field.
     *
     * @param o the object to compare to
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    /**
     * Generates a hash code for this job.
     * The hash code is based on the ID field.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of this job.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                '}';
    }
}
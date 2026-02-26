package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Objects;

/**
 * Person domain entity representing a person in the system.
 * Contains personal information and references to associated entities.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bankAccount;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    /**
     * Default constructor for JPA.
     */
    public Person() {
    }

    /**
     * Constructor with all fields.
     *
     * @param name the person's name
     * @param age the person's age
     * @param bankAccount the associated bank
     * @param job the associated job
     */
    public Person(String name, Integer age, Bank bankAccount, Job job) {
        this.name = name;
        this.age = age;
        this.bankAccount = bankAccount;
        this.job = job;
    }

    /**
     * Gets the person's ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the person's ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the person's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the person's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the person's age.
     *
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the person's age.
     *
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Gets the associated bank.
     *
     * @return the bank
     */
    public Bank getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the associated bank.
     *
     * @param bankAccount the bank to set
     */
    public void setBankAccount(Bank bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * Gets the associated job.
     *
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * Sets the associated job.
     *
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Compares this person to another object for equality.
     * Equality is based on the ID field.
     *
     * @param o the object to compare to
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    /**
     * Generates a hash code for this person.
     * The hash code is based on the ID field.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of this person.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bankAccount=" + bankAccount +
                ", job=" + job +
                '}';
    }
}
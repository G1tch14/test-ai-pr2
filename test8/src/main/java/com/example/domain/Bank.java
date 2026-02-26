package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Objects;

/**
 * Bank domain entity representing a bank in the system.
 */
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String swiftCode;

    /**
     * Default constructor for JPA.
     */
    public Bank() {
    }

    /**
     * Constructor with all fields.
     *
     * @param name the bank name
     * @param address the bank address
     * @param swiftCode the SWIFT code
     */
    public Bank(String name, String address, String swiftCode) {
        this.name = name;
        this.address = address;
        this.swiftCode = swiftCode;
    }

    /**
     * Gets the bank ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the bank ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the bank name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the bank name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the bank address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the bank address.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the SWIFT code.
     *
     * @return the SWIFT code
     */
    public String getSwiftCode() {
        return swiftCode;
    }

    /**
     * Sets the SWIFT code.
     *
     * @param swiftCode the SWIFT code to set
     */
    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    /**
     * Compares this bank to another object for equality.
     * Equality is based on the ID field.
     *
     * @param o the object to compare to
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id);
    }

    /**
     * Generates a hash code for this bank.
     * The hash code is based on the ID field.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of this bank.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", swiftCode='" + swiftCode + '\'' +
                '}';
    }
}
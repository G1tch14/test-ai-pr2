package com.example.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Person entity.
 */
class PersonTest {

    @Test
    void testPersonCreation() {
        // Given
        Bank bank = new Bank("Test Bank", "123 Test St", "TESTUS33");
        Job job = new Job("Developer", "Test Company", 50000.0);
        
        // When
        Person person = new Person("John Doe", 30, bank, job);
        
        // Then
        assertNull(person.getId()); // ID should be null before persistence
        assertEquals("John Doe", person.getName());
        assertEquals(30, person.getAge());
        assertEquals(bank, person.getBankAccount());
        assertEquals(job, person.getJob());
    }

    @Test
    void testSettersAndGetters() {
        // Given
        Person person = new Person();
        Bank bank = new Bank("Another Bank", "456 Another St", "ANOTUS44");
        Job job = new Job("Manager", "Another Company", 70000.0);
        
        // When
        person.setId(1L);
        person.setName("Jane Smith");
        person.setAge(25);
        person.setBankAccount(bank);
        person.setJob(job);
        
        // Then
        assertEquals(1L, person.getId());
        assertEquals("Jane Smith", person.getName());
        assertEquals(25, person.getAge());
        assertEquals(bank, person.getBankAccount());
        assertEquals(job, person.getJob());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        Person person1 = new Person();
        person1.setId(1L);
        
        Person person2 = new Person();
        person2.setId(1L);
        
        Person person3 = new Person();
        person3.setId(2L);
        
        // Then
        assertEquals(person1, person2);
        assertNotEquals(person1, person3);
        assertEquals(person1.hashCode(), person2.hashCode());
        assertNotEquals(person1.hashCode(), person3.hashCode());
    }

    @Test
    void testEqualsWithNull() {
        // Given
        Person person = new Person();
        person.setId(1L);
        
        // Then
        assertNotEquals(null, person);
        assertNotEquals(person, new Object());
    }

    @Test
    void testToString() {
        // Given
        Person person = new Person();
        person.setId(1L);
        person.setName("Test Person");
        person.setAge(30);
        
        // When
        String toString = person.toString();
        
        // Then
        assertTrue(toString.contains("Person{"));
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name='Test Person'"));
        assertTrue(toString.contains("age=30"));
    }
}
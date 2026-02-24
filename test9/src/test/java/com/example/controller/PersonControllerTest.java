package com.example.controller;

import com.example.model.Person;
import com.example.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PersonController.
 */
@ExtendWith(MockitoExtension.class)
class PersonControllerTest {
    
    @Mock
    private PersonService personService;
    
    @InjectMocks
    private PersonController personController;
    
    private Person testPerson;
    private Person testPerson2;
    
    @BeforeEach
    void setUp() {
        testPerson = new Person(1L, "John", "Doe", 30, "john.doe@example.com");
        testPerson2 = new Person(2L, "Jane", "Smith", 25, "jane.smith@example.com");
    }
    
    @Test
    void createPerson_ValidPerson_ReturnsCreatedResponse() {
        // Arrange
        Person newPerson = new Person("John", "Doe", 30, "john.doe@example.com");
        when(personService.createPerson(any(Person.class))).thenReturn(testPerson);
        
        // Act
        ResponseEntity<Person> response = personController.createPerson(newPerson);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testPerson, response.getBody());
        verify(personService, times(1)).createPerson(newPerson);
    }
    
    @Test
    void createPerson_InvalidPerson_ReturnsBadRequest() {
        // Arrange
        Person invalidPerson = new Person("", "Doe", 30, "invalid-email");
        when(personService.createPerson(any(Person.class)))
            .thenThrow(new IllegalArgumentException("Invalid data"));
        
        // Act
        ResponseEntity<Person> response = personController.createPerson(invalidPerson);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(personService, times(1)).createPerson(invalidPerson);
    }
    
    @Test
    void getPersonById_ExistingId_ReturnsPerson() {
        // Arrange
        when(personService.getPersonById(1L)).thenReturn(testPerson);
        
        // Act
        ResponseEntity<Person> response = personController.getPersonById(1L);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testPerson, response.getBody());
        verify(personService, times(1)).getPersonById(1L);
    }
    
    @Test
    void getPersonById_NonExistingId_ReturnsNotFound() {
        // Arrange
        when(personService.getPersonById(999L)).thenReturn(null);
        
        // Act
        ResponseEntity<Person> response = personController.getPersonById(999L);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(personService, times(1)).getPersonById(999L);
    }
    
    @Test
    void getPersonById_NullId_ReturnsBadRequest() {
        // Arrange
        when(personService.getPersonById(null))
            .thenThrow(new IllegalArgumentException("ID не может быть null"));
        
        // Act
        ResponseEntity<Person> response = personController.getPersonById(null);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(personService, times(1)).getPersonById(null);
    }
    
    @Test
    void getAllPersons_ReturnsAllPersons() {
        // Arrange
        List<Person> persons = Arrays.asList(testPerson, testPerson2);
        when(personService.getAllPersons()).thenReturn(persons);
        
        // Act
        ResponseEntity<List<Person>> response = personController.getAllPersons();
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(persons, response.getBody());
        verify(personService, times(1)).getAllPersons();
    }
    
    @Test
    void getAllPersons_EmptyList_ReturnsEmptyList() {
        // Arrange
        List<Person> emptyList = List.of();
        when(personService.getAllPersons()).thenReturn(emptyList);
        
        // Act
        ResponseEntity<List<Person>> response = personController.getAllPersons();
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(personService, times(1)).getAllPersons();
    }
    
    @Test
    void updatePerson_ValidUpdate_ReturnsUpdatedPerson() {
        // Arrange
        Person updatedData = new Person("John", "Updated", 31, "john.updated@example.com");
        Person updatedPerson = new Person(1L, "John", "Updated", 31, "john.updated@example.com");
        when(personService.updatePerson(1L, updatedData)).thenReturn(updatedPerson);
        
        // Act
        ResponseEntity<Person> response = personController.updatePerson(1L, updatedData);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedPerson, response.getBody());
        verify(personService, times(1)).updatePerson(1L, updatedData);
    }
    
    @Test
    void updatePerson_NonExistingId_ReturnsNotFound() {
        // Arrange
        Person updatedData = new Person("John", "Updated", 31, "john.updated@example.com");
        when(personService.updatePerson(999L, updatedData))
            .thenThrow(new IllegalArgumentException("Человек с ID 999 не найден"));
        
        // Act
        ResponseEntity<Person> response = personController.updatePerson(999L, updatedData);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(personService, times(1)).updatePerson(999L, updatedData);
    }
    
    @Test
    void updatePerson_InvalidData_ReturnsBadRequest() {
        // Arrange
        Person invalidData = new Person("", "", -1, "invalid");
        when(personService.updatePerson(1L, invalidData))
            .thenThrow(new IllegalArgumentException("Invalid data"));
        
        // Act
        ResponseEntity<Person> response = personController.updatePerson(1L, invalidData);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(personService, times(1)).updatePerson(1L, invalidData);
    }
    
    @Test
    void deletePerson_ExistingId_ReturnsNoContent() {
        // Arrange
        when(personService.deletePerson(1L)).thenReturn(true);
        
        // Act
        ResponseEntity<Void> response = personController.deletePerson(1L);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(personService, times(1)).deletePerson(1L);
    }
    
    @Test
    void deletePerson_NonExistingId_ReturnsNotFound() {
        // Arrange
        when(personService.deletePerson(999L)).thenReturn(false);
        
        // Act
        ResponseEntity<Void> response = personController.deletePerson(999L);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(personService, times(1)).deletePerson(999L);
    }
    
    @Test
    void deletePerson_NullId_ReturnsBadRequest() {
        // Arrange
        when(personService.deletePerson(null))
            .thenThrow(new IllegalArgumentException("ID не может быть null"));
        
        // Act
        ResponseEntity<Void> response = personController.deletePerson(null);
        
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(personService, times(1)).deletePerson(null);
    }
}
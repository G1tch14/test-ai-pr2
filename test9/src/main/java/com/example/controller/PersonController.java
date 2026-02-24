package com.example.controller;

import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Person entities.
 * Provides CRUD operations through REST API endpoints.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    
    private final PersonService personService;
    
    /**
     * Constructor for PersonController with dependency injection.
     * 
     * @param personService the PersonService to use for business logic
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    /**
     * Creates a new person.
     * 
     * @param person the person data to create (without id)
     * @return ResponseEntity containing the created person with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person createdPerson = personService.createPerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    /**
     * Retrieves a person by their ID.
     * 
     * @param id the ID of the person to retrieve
     * @return ResponseEntity containing the person if found (HTTP 200),
     *         or HTTP 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        try {
            Person person = personService.getPersonById(id);
            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    /**
     * Retrieves all persons.
     * 
     * @return ResponseEntity containing a list of all persons (HTTP 200)
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }
    
    /**
     * Updates an existing person.
     * 
     * @param id the ID of the person to update
     * @param updatedPerson the updated person data
     * @return ResponseEntity containing the updated person (HTTP 200),
     *         HTTP 404 if person not found, or HTTP 400 for invalid data
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        try {
            Person person = personService.updatePerson(id, updatedPerson);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("не найден") || e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    /**
     * Deletes a person by their ID.
     * 
     * @param id the ID of the person to delete
     * @return ResponseEntity with HTTP 204 (No Content) if successful,
     *         HTTP 404 if person not found, or HTTP 400 for invalid ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        try {
            boolean deleted = personService.deletePerson(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
package com.example.service;

import com.example.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса PersonService.
 */
class PersonServiceTest {
    
    private PersonService personService;
    
    @BeforeEach
    void setUp() {
        personService = new PersonService();
    }
    
    @Test
    @DisplayName("Создание валидного человека")
    void createPerson_ValidPerson_ReturnsCreatedPerson() {
        // Arrange
        Person person = new Person("Иван", "Иванов", 30, "ivan@example.com");
        
        // Act
        Person created = personService.createPerson(person);
        
        // Assert
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Иван", created.getFirstName());
        assertEquals("Иванов", created.getLastName());
        assertEquals(30, created.getAge());
        assertEquals("ivan@example.com", created.getEmail());
        assertEquals(1, personService.getPersonCount());
    }
    
    @Test
    @DisplayName("Создание человека с пустым именем - должно выбросить исключение")
    void createPerson_EmptyFirstName_ThrowsException() {
        // Arrange
        Person person = new Person("", "Иванов", 30, "ivan@example.com");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.createPerson(person)
        );
        assertEquals("Имя не может быть пустым", exception.getMessage());
    }
    
    @Test
    @DisplayName("Создание человека с отрицательным возрастом - должно выбросить исключение")
    void createPerson_NegativeAge_ThrowsException() {
        // Arrange
        Person person = new Person("Иван", "Иванов", -1, "ivan@example.com");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.createPerson(person)
        );
        assertEquals("Возраст не может быть отрицательным", exception.getMessage());
    }
    
    @Test
    @DisplayName("Создание человека с некорректным email - должно выбросить исключение")
    void createPerson_InvalidEmail_ThrowsException() {
        // Arrange
        Person person = new Person("Иван", "Иванов", 30, "invalid-email");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.createPerson(person)
        );
        assertTrue(exception.getMessage().contains("Некорректный формат email"));
    }
    
    @Test
    @DisplayName("Создание человека с дублирующимся email - должно выбросить исключение")
    void createPerson_DuplicateEmail_ThrowsException() {
        // Arrange
        Person person1 = new Person("Иван", "Иванов", 30, "ivan@example.com");
        Person person2 = new Person("Петр", "Петров", 25, "ivan@example.com");
        
        // Act
        personService.createPerson(person1);
        
        // Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.createPerson(person2)
        );
        assertEquals("Email уже существует: ivan@example.com", exception.getMessage());
    }
    
    @Test
    @DisplayName("Получение человека по существующему ID")
    void getPersonById_ExistingId_ReturnsPerson() {
        // Arrange
        Person person = new Person("Иван", "Иванов", 30, "ivan@example.com");
        Person created = personService.createPerson(person);
        Long id = created.getId();
        
        // Act
        Person found = personService.getPersonById(id);
        
        // Assert
        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Иван", found.getFirstName());
    }
    
    @Test
    @DisplayName("Получение человека по несуществующему ID")
    void getPersonById_NonExistingId_ReturnsNull() {
        // Act
        Person found = personService.getPersonById(999L);
        
        // Assert
        assertNull(found);
    }
    
    @Test
    @DisplayName("Получение человека по null ID - должно выбросить исключение")
    void getPersonById_NullId_ThrowsException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.getPersonById(null)
        );
        assertEquals("ID не может быть null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Получение всех людей из пустого хранилища")
    void getAllPersons_EmptyStorage_ReturnsEmptyList() {
        // Act
        List<Person> persons = personService.getAllPersons();
        
        // Assert
        assertNotNull(persons);
        assertTrue(persons.isEmpty());
    }
    
    @Test
    @DisplayName("Получение всех людей из непустого хранилища")
    void getAllPersons_NonEmptyStorage_ReturnsAllPersons() {
        // Arrange
        Person person1 = new Person("Иван", "Иванов", 30, "ivan@example.com");
        Person person2 = new Person("Петр", "Петров", 25, "petr@example.com");
        
        personService.createPerson(person1);
        personService.createPerson(person2);
        
        // Act
        List<Person> persons = personService.getAllPersons();
        
        // Assert
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }
    
    @Test
    @DisplayName("Обновление существующего человека")
    void updatePerson_ExistingPerson_ReturnsUpdatedPerson() {
        // Arrange
        Person person = new Person("Иван", "Иванов", 30, "ivan@example.com");
        Person created = personService.createPerson(person);
        Long id = created.getId();
        
        Person updatedData = new Person("Иван", "Сидоров", 31, "ivan.sidorov@example.com");
        
        // Act
        Person updated = personService.updatePerson(id, updatedData);
        
        // Assert
        assertNotNull(updated);
        assertEquals(id, updated.getId());
        assertEquals("Иван", updated.getFirstName());
        assertEquals("Сидоров", updated.getLastName());
        assertEquals(31, updated.getAge());
        assertEquals("ivan.sidorov@example.com", updated.getEmail());
        
        // Проверяем, что данные действительно обновились
        Person retrieved = personService.getPersonById(id);
        assertEquals("Сидоров", retrieved.getLastName());
    }
    
    @Test
    @DisplayName("Обновление несуществующего человека - должно выбросить исключение")
    void updatePerson_NonExistingPerson_ThrowsException() {
        // Arrange
        Person updatedData = new Person("Иван", "Иванов", 30, "ivan@example.com");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.updatePerson(999L, updatedData)
        );
        assertEquals("Человек с ID 999 не найден", exception.getMessage());
    }
    
    @Test
    @DisplayName("Обновление человека с дублирующимся email - должно выбросить исключение")
    void updatePerson_DuplicateEmail_ThrowsException() {
        // Arrange
        Person person1 = new Person("Иван", "Иванов", 30, "ivan@example.com");
        Person person2 = new Person("Петр", "Петров", 25, "petr@example.com");
        
        Person created1 = personService.createPerson(person1);
        personService.createPerson(person2);
        
        Person updatedData = new Person("Иван", "Иванов", 30, "petr@example.com");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.updatePerson(created1.getId(), updatedData)
        );
        assertEquals("Email уже существует: petr@example.com", exception.getMessage());
    }
    
    @Test
    @DisplayName("Удаление существующего человека")
    void deletePerson_ExistingPerson_ReturnsTrue() {
        // Arrange
        Person person = new Person("Иван", "Иванов", 30, "ivan@example.com");
        Person created = personService.createPerson(person);
        Long id = created.getId();
        
        // Act
        boolean result = personService.deletePerson(id);
        
        // Assert
        assertTrue(result);
        assertNull(personService.getPersonById(id));
        assertEquals(0, personService.getPersonCount());
    }
    
    @Test
    @DisplayName("Удаление несуществующего человека")
    void deletePerson_NonExistingPerson_ReturnsFalse() {
        // Act
        boolean result = personService.deletePerson(999L);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    @DisplayName("Удаление человека по null ID - должно выбросить исключение")
    void deletePerson_NullId_ThrowsException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.deletePerson(null)
        );
        assertEquals("ID не может быть null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Очистка хранилища")
    void clearStorage_RemovesAllPersons() {
        // Arrange
        Person person1 = new Person("Иван", "Иванов", 30, "ivan@example.com");
        Person person2 = new Person("Петр", "Петров", 25, "petr@example.com");
        
        personService.createPerson(person1);
        personService.createPerson(person2);
        
        // Act
        personService.clearStorage();
        
        // Assert
        assertEquals(0, personService.getPersonCount());
        assertTrue(personService.getAllPersons().isEmpty());
    }
    
    @Test
    @DisplayName("Проверка валидации слишком длинного имени")
    void validatePerson_TooLongFirstName_ThrowsException() {
        // Arrange
        String longName = "A".repeat(51);
        Person person = new Person(longName, "Иванов", 30, "ivan@example.com");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.createPerson(person)
        );
        assertEquals("Имя слишком длинное (максимум 50 символов)", exception.getMessage());
    }
    
    @Test
    @DisplayName("Проверка валидации слишком длинной фамилии")
    void validatePerson_TooLongLastName_ThrowsException() {
        // Arrange
        String longLastName = "A".repeat(51);
        Person person = new Person("Иван", longLastName, 30, "ivan@example.com");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.createPerson(person)
        );
        assertEquals("Фамилия слишком длинная (максимум 50 символов)", exception.getMessage());
    }
    
    @Test
    @DisplayName("Проверка валидации слишком большого возраста")
    void validatePerson_TooOld_ThrowsException() {
        // Arrange
        Person person = new Person("Иван", "Иванов", 151, "ivan@example.com");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> personService.createPerson(person)
        );
        assertEquals("Возраст не может быть больше 150 лет", exception.getMessage());
    }
}
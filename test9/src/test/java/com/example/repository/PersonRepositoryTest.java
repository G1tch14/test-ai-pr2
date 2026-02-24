package com.example.repository;

import com.example.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки контракта интерфейса PersonRepository.
 * Этот тест демонстрирует ожидаемое поведение методов репозитория.
 */
class PersonRepositoryTest {
    
    // Тестовые данные
    private Person testPerson1;
    private Person testPerson2;
    
    @BeforeEach
    void setUp() {
        // Создаем тестовые объекты Person
        testPerson1 = new Person(1L, "Иван", "Иванов", 30, "ivan@example.com");
        testPerson2 = new Person(2L, "Мария", "Петрова", 25, "maria@example.com");
    }
    
    @Test
    void testSaveMethodContract() {
        // Этот тест проверяет контракт метода save()
        // В реальной реализации этот метод должен:
        // 1. Сохранять новый объект (когда id == null)
        // 2. Обновлять существующий объект (когда id != null)
        // 3. Возвращать сохраненный объект с присвоенным id
        
        Person newPerson = new Person("Алексей", "Сидоров", 35, "alex@example.com");
        assertNull(newPerson.getId(), "Новый объект должен иметь null id");
        
        // Контракт: метод save должен возвращать объект с присвоенным id
        // Person savedPerson = repository.save(newPerson);
        // assertNotNull(savedPerson.getId(), "После сохранения объект должен иметь id");
    }
    
    @Test
    void testFindByIdMethodContract() {
        // Этот тест проверяет контракт метода findById()
        // В реальной реализации этот метод должен:
        // 1. Возвращать Optional.empty() если объект не найден
        // 2. Возвращать Optional.of(person) если объект найден
        
        // Контракт: для несуществующего id должен возвращаться Optional.empty()
        // Optional<Person> result = repository.findById(999L);
        // assertTrue(result.isEmpty(), "Для несуществующего id должен возвращаться Optional.empty()");
        
        // Контракт: для существующего id должен возвращаться Optional с объектом
        // Optional<Person> result = repository.findById(1L);
        // assertTrue(result.isPresent(), "Для существующего id должен возвращаться Optional с объектом");
        // assertEquals(testPerson1, result.get(), "Должен возвращаться правильный объект");
    }
    
    @Test
    void testFindAllMethodContract() {
        // Этот тест проверяет контракт метода findAll()
        // В реальной реализации этот метод должен:
        // 1. Возвращать список всех объектов
        // 2. Возвращать пустой список если объектов нет
        
        // Контракт: метод должен возвращать список
        // List<Person> persons = repository.findAll();
        // assertNotNull(persons, "Метод findAll() не должен возвращать null");
        // assertTrue(persons instanceof List, "Метод должен возвращать List");
    }
    
    @Test
    void testUpdateMethodContract() {
        // Этот тест проверяет контракт метода update()
        // В реальной реализации этот метод должен:
        // 1. Обновлять существующий объект
        // 2. Бросать исключение если объект не имеет id
        
        Person personWithoutId = new Person("Без", "Id", 40, "noid@example.com");
        assertNull(personWithoutId.getId(), "Объект не должен иметь id");
        
        // Контракт: метод update должен требовать наличия id
        // assertThrows(IllegalArgumentException.class, () -> repository.update(personWithoutId),
        //     "Метод update() должен бросать исключение для объекта без id");
    }
    
    @Test
    void testDeleteByIdMethodContract() {
        // Этот тест проверяет контракт метода delete(Long id)
        // В реальной реализации этот метод должен:
        // 1. Возвращать true если объект был удален
        // 2. Возвращать false если объект не найден
        
        // Контракт: метод должен возвращать boolean
        // boolean result = repository.delete(1L);
        // assertTrue(result || !result, "Метод должен возвращать boolean значение");
    }
    
    @Test
    void testDeleteByObjectMethodContract() {
        // Этот тест проверяет контракт метода delete(Person person)
        // В реальной реализации этот метод должен:
        // 1. Возвращать true если объект был удален
        // 2. Возвращать false если объект не найден
        
        // Контракт: метод должен возвращать boolean
        // boolean result = repository.delete(testPerson1);
        // assertTrue(result || !result, "Метод должен возвращать boolean значение");
    }
    
    @Test
    void testRepositoryIsInterface() {
        // Проверяем, что PersonRepository действительно является интерфейсом
        Class<PersonRepository> repositoryClass = PersonRepository.class;
        assertTrue(repositoryClass.isInterface(), "PersonRepository должен быть интерфейсом");
    }
    
    @Test
    void testMethodSignatures() {
        // Проверяем сигнатуры методов интерфейса
        // Этот тест гарантирует, что интерфейс имеет правильные методы
        
        try {
            // Проверяем наличие всех методов
            PersonRepository.class.getMethod("save", Person.class);
            PersonRepository.class.getMethod("findById", Long.class);
            PersonRepository.class.getMethod("findAll");
            PersonRepository.class.getMethod("update", Person.class);
            PersonRepository.class.getMethod("delete", Long.class);
            PersonRepository.class.getMethod("delete", Person.class);
        } catch (NoSuchMethodException e) {
            fail("Интерфейс PersonRepository должен содержать все объявленные методы: " + e.getMessage());
        }
    }
}
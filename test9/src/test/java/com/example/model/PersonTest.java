package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности класса Person.
 */
class PersonTest {
    
    /**
     * Тест конструктора по умолчанию.
     * Проверяет, что создается пустой объект Person.
     */
    @Test
    void testDefaultConstructor() {
        Person person = new Person();
        
        assertNull(person.getId());
        assertNull(person.getFirstName());
        assertNull(person.getLastName());
        assertNull(person.getAge());
        assertNull(person.getEmail());
    }
    
    /**
     * Тест конструктора с параметрами (с id).
     * Проверяет, что создается объект Person с указанными значениями.
     */
    @Test
    void testParameterizedConstructorWithId() {
        Long id = 1L;
        String firstName = "Иван";
        String lastName = "Иванов";
        Integer age = 30;
        String email = "ivan@example.com";
        
        Person person = new Person(id, firstName, lastName, age, email);
        
        assertEquals(id, person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(age, person.getAge());
        assertEquals(email, person.getEmail());
    }
    
    /**
     * Тест конструктора с параметрами (без id).
     * Проверяет, что создается объект Person с указанными значениями, кроме id.
     */
    @Test
    void testParameterizedConstructorWithoutId() {
        String firstName = "Мария";
        String lastName = "Петрова";
        Integer age = 25;
        String email = "maria@example.com";
        
        Person person = new Person(firstName, lastName, age, email);
        
        assertNull(person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(age, person.getAge());
        assertEquals(email, person.getEmail());
    }
    
    /**
     * Тест геттеров и сеттеров.
     * Проверяет корректность работы методов get и set для всех полей.
     */
    @Test
    void testGettersAndSetters() {
        Person person = new Person();
        
        Long id = 2L;
        String firstName = "Петр";
        String lastName = "Сидоров";
        Integer age = 35;
        String email = "petr@example.com";
        
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        person.setEmail(email);
        
        assertEquals(id, person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(age, person.getAge());
        assertEquals(email, person.getEmail());
    }
    
    /**
     * Тест метода equals.
     * Проверяет корректность сравнения двух объектов Person.
     */
    @Test
    void testEquals() {
        Person person1 = new Person(1L, "Алексей", "Смирнов", 40, "alexey@example.com");
        Person person2 = new Person(1L, "Алексей", "Смирнов", 40, "alexey@example.com");
        Person person3 = new Person(2L, "Алексей", "Смирнов", 40, "alexey@example.com");
        Person person4 = new Person(1L, "Дмитрий", "Смирнов", 40, "alexey@example.com");
        Person person5 = new Person(1L, "Алексей", "Петров", 40, "alexey@example.com");
        Person person6 = new Person(1L, "Алексей", "Смирнов", 45, "alexey@example.com");
        Person person7 = new Person(1L, "Алексей", "Смирнов", 40, "dmitry@example.com");
        
        // Проверка равенства одинаковых объектов
        assertEquals(person1, person2);
        
        // Проверка неравенства объектов с разными полями
        assertNotEquals(person1, person3); // разный id
        assertNotEquals(person1, person4); // разное имя
        assertNotEquals(person1, person5); // разная фамилия
        assertNotEquals(person1, person6); // разный возраст
        assertNotEquals(person1, person7); // разный email
        
        // Проверка равенства с самим собой
        assertEquals(person1, person1);
        
        // Проверка неравенства с null
        assertNotEquals(null, person1);
        
        // Проверка неравенства с объектом другого класса
        assertNotEquals(person1, "строка");
    }
    
    /**
     * Тест метода hashCode.
     * Проверяет, что одинаковые объекты имеют одинаковый хэш-код.
     */
    @Test
    void testHashCode() {
        Person person1 = new Person(1L, "Елена", "Кузнецова", 28, "elena@example.com");
        Person person2 = new Person(1L, "Елена", "Кузнецова", 28, "elena@example.com");
        
        assertEquals(person1.hashCode(), person2.hashCode());
        
        // Проверка, что разные объекты (скорее всего) имеют разные хэш-коды
        Person person3 = new Person(2L, "Ольга", "Иванова", 32, "olga@example.com");
        assertNotEquals(person1.hashCode(), person3.hashCode());
    }
    
    /**
     * Тест метода toString.
     * Проверяет корректность строкового представления объекта.
     */
    @Test
    void testToString() {
        Person person = new Person(3L, "Сергей", "Васильев", 50, "sergey@example.com");
        
        String expected = "Person{id=3, firstName='Сергей', lastName='Васильев', age=50, email='sergey@example.com'}";
        assertEquals(expected, person.toString());
        
        // Проверка для объекта с null значениями
        Person emptyPerson = new Person();
        String emptyExpected = "Person{id=null, firstName='null', lastName='null', age=null, email='null'}";
        assertEquals(emptyExpected, emptyPerson.toString());
    }
    
    /**
     * Тест граничных значений для возраста.
     * Проверяет корректность работы с возрастом.
     */
    @Test
    void testAgeBoundaries() {
        Person person = new Person();
        
        // Проверка минимального возраста
        person.setAge(0);
        assertEquals(0, person.getAge());
        
        // Проверка положительного возраста
        person.setAge(18);
        assertEquals(18, person.getAge());
        
        // Проверка большого возраста
        person.setAge(120);
        assertEquals(120, person.getAge());
        
        // Проверка null возраста
        person.setAge(null);
        assertNull(person.getAge());
    }
    
    /**
     * Тест работы с пустыми строками.
     * Проверяет корректность работы с пустыми и null строками.
     */
    @Test
    void testEmptyStrings() {
        Person person = new Person();
        
        // Проверка пустых строк
        person.setFirstName("");
        person.setLastName("");
        person.setEmail("");
        
        assertEquals("", person.getFirstName());
        assertEquals("", person.getLastName());
        assertEquals("", person.getEmail());
        
        // Проверка null строк
        person.setFirstName(null);
        person.setLastName(null);
        person.setEmail(null);
        
        assertNull(person.getFirstName());
        assertNull(person.getLastName());
        assertNull(person.getEmail());
    }
}
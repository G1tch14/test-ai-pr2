package com.example.service;

import com.example.model.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/**
 * Сервис для управления объектами Person.
 * Предоставляет CRUD операции с использованием in-memory хранилища.
 * Включает валидацию данных.
 */
public class PersonService {
    
    /** In-memory хранилище для объектов Person */
    private final Map<Long, Person> personStorage = new HashMap<>();
    
    /** Генератор уникальных идентификаторов */
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /** Регулярное выражение для валидации email */
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    /**
     * Создает нового человека.
     * 
     * @param person объект Person для создания (без id)
     * @return созданный объект Person с присвоенным id
     * @throws IllegalArgumentException если данные невалидны
     */
    public Person createPerson(Person person) {
        validatePerson(person);
        
        // Проверяем уникальность email
        if (isEmailExists(person.getEmail())) {
            throw new IllegalArgumentException("Email уже существует: " + person.getEmail());
        }
        
        // Генерируем новый id
        Long newId = idGenerator.getAndIncrement();
        Person newPerson = new Person(
            newId,
            person.getFirstName(),
            person.getLastName(),
            person.getAge(),
            person.getEmail()
        );
        
        // Сохраняем в хранилище
        personStorage.put(newId, newPerson);
        return newPerson;
    }
    
    /**
     * Получает человека по идентификатору.
     * 
     * @param id идентификатор человека
     * @return объект Person или null, если не найден
     */
    public Person getPersonById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return personStorage.get(id);
    }
    
    /**
     * Получает всех людей.
     * 
     * @return список всех объектов Person
     */
    public List<Person> getAllPersons() {
        return new ArrayList<>(personStorage.values());
    }
    
    /**
     * Обновляет данные человека.
     * 
     * @param id идентификатор человека для обновления
     * @param updatedPerson обновленные данные
     * @return обновленный объект Person
     * @throws IllegalArgumentException если данные невалидны или человек не найден
     */
    public Person updatePerson(Long id, Person updatedPerson) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        
        // Проверяем существование человека
        Person existingPerson = personStorage.get(id);
        if (existingPerson == null) {
            throw new IllegalArgumentException("Человек с ID " + id + " не найден");
        }
        
        // Валидируем обновленные данные
        validatePerson(updatedPerson);
        
        // Проверяем уникальность email (если email изменился)
        if (!existingPerson.getEmail().equals(updatedPerson.getEmail()) && 
            isEmailExists(updatedPerson.getEmail())) {
            throw new IllegalArgumentException("Email уже существует: " + updatedPerson.getEmail());
        }
        
        // Создаем обновленный объект
        Person updated = new Person(
            id,
            updatedPerson.getFirstName(),
            updatedPerson.getLastName(),
            updatedPerson.getAge(),
            updatedPerson.getEmail()
        );
        
        // Обновляем в хранилище
        personStorage.put(id, updated);
        return updated;
    }
    
    /**
     * Удаляет человека по идентификатору.
     * 
     * @param id идентификатор человека для удаления
     * @return true если человек был удален, false если не найден
     */
    public boolean deletePerson(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return personStorage.remove(id) != null;
    }
    
    /**
     * Валидирует объект Person.
     * 
     * @param person объект Person для валидации
     * @throws IllegalArgumentException если данные невалидны
     */
    private void validatePerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person не может быть null");
        }
        
        // Валидация имени
        if (person.getFirstName() == null || person.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (person.getFirstName().length() > 50) {
            throw new IllegalArgumentException("Имя слишком длинное (максимум 50 символов)");
        }
        
        // Валидация фамилии
        if (person.getLastName() == null || person.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }
        if (person.getLastName().length() > 50) {
            throw new IllegalArgumentException("Фамилия слишком длинная (максимум 50 символов)");
        }
        
        // Валидация возраста
        if (person.getAge() == null) {
            throw new IllegalArgumentException("Возраст не может быть null");
        }
        if (person.getAge() < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        if (person.getAge() > 150) {
            throw new IllegalArgumentException("Возраст не может быть больше 150 лет");
        }
        
        // Валидация email
        if (person.getEmail() == null || person.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        if (!isValidEmail(person.getEmail())) {
            throw new IllegalArgumentException("Некорректный формат email: " + person.getEmail());
        }
    }
    
    /**
     * Проверяет валидность email с помощью регулярного выражения.
     * 
     * @param email email для проверки
     * @return true если email валиден, false в противном случае
     */
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Проверяет существование email в хранилище.
     * 
     * @param email email для проверки
     * @return true если email уже существует, false в противном случае
     */
    private boolean isEmailExists(String email) {
        return personStorage.values().stream()
            .anyMatch(person -> person.getEmail().equals(email));
    }
    
    /**
     * Очищает хранилище (для тестирования).
     */
    public void clearStorage() {
        personStorage.clear();
        idGenerator.set(1);
    }
    
    /**
     * Возвращает количество людей в хранилище.
     * 
     * @return количество людей
     */
    public int getPersonCount() {
        return personStorage.size();
    }
}
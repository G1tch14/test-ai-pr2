package com.example.model;

import java.util.Objects;

/**
 * Класс Person представляет сущность человека.
 * Содержит основные данные о человеке: идентификатор, имя, фамилию, возраст и email.
 */
public class Person {
    
    /** Уникальный идентификатор человека */
    private Long id;
    
    /** Имя человека */
    private String firstName;
    
    /** Фамилия человека */
    private String lastName;
    
    /** Возраст человека */
    private Integer age;
    
    /** Email адрес человека */
    private String email;
    
    /**
     * Конструктор по умолчанию.
     * Создает пустой объект Person.
     */
    public Person() {
        // Пустой конструктор
    }
    
    /**
     * Конструктор с параметрами.
     * Создает объект Person с указанными значениями полей.
     * 
     * @param id уникальный идентификатор
     * @param firstName имя
     * @param lastName фамилия
     * @param age возраст
     * @param email email адрес
     */
    public Person(Long id, String firstName, String lastName, Integer age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
    
    /**
     * Конструктор с параметрами (без id).
     * Создает объект Person с указанными значениями полей, кроме id.
     * 
     * @param firstName имя
     * @param lastName фамилия
     * @param age возраст
     * @param email email адрес
     */
    public Person(String firstName, String lastName, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
    
    /**
     * Возвращает уникальный идентификатор человека.
     * 
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Устанавливает уникальный идентификатор человека.
     * 
     * @param id идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Возвращает имя человека.
     * 
     * @return имя
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Устанавливает имя человека.
     * 
     * @param firstName имя
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Возвращает фамилию человека.
     * 
     * @return фамилия
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Устанавливает фамилию человека.
     * 
     * @param lastName фамилия
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Возвращает возраст человека.
     * 
     * @return возраст
     */
    public Integer getAge() {
        return age;
    }
    
    /**
     * Устанавливает возраст человека.
     * 
     * @param age возраст
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    
    /**
     * Возвращает email адрес человека.
     * 
     * @return email адрес
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Устанавливает email адрес человека.
     * 
     * @param email email адрес
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Сравнивает текущий объект Person с другим объектом.
     * Два объекта Person считаются равными, если у них одинаковые id, firstName, lastName, age и email.
     * 
     * @param o объект для сравнения
     * @return true если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Person person = (Person) o;
        
        if (!Objects.equals(id, person.id)) return false;
        if (!Objects.equals(firstName, person.firstName)) return false;
        if (!Objects.equals(lastName, person.lastName)) return false;
        if (!Objects.equals(age, person.age)) return false;
        return Objects.equals(email, person.email);
    }
    
    /**
     * Возвращает хэш-код объекта Person.
     * Хэш-код вычисляется на основе полей id, firstName, lastName, age и email.
     * 
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
    
    /**
     * Возвращает строковое представление объекта Person.
     * Формат: "Person{id=..., firstName='...', lastName='...', age=..., email='...'}"
     * 
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
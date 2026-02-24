package com.example.model;

import java.util.Objects;

/**
 * Класс, представляющий вакансию (работу).
 * Содержит информацию о должности, компании, зарплате и описании.
 */
public class Job {
    
    /** Уникальный идентификатор вакансии */
    private Long id;
    
    /** Название должности */
    private String title;
    
    /** Название компании */
    private String company;
    
    /** Зарплата (может быть null) */
    private Double salary;
    
    /** Описание вакансии */
    private String description;
    
    /**
     * Конструктор по умолчанию.
     * Создает пустой объект вакансии.
     */
    public Job() {
        // Пустой конструктор для фреймворков и библиотек
    }
    
    /**
     * Конструктор с параметрами.
     * Создает объект вакансии с указанными значениями.
     * 
     * @param id уникальный идентификатор
     * @param title название должности
     * @param company название компании
     * @param salary зарплата
     * @param description описание вакансии
     */
    public Job(Long id, String title, String company, Double salary, String description) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.description = description;
    }
    
    /**
     * Конструктор без идентификатора.
     * Создает объект вакансии без указания идентификатора.
     * 
     * @param title название должности
     * @param company название компании
     * @param salary зарплата
     * @param description описание вакансии
     */
    public Job(String title, String company, Double salary, String description) {
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.description = description;
    }
    
    // Геттеры и сеттеры
    
    /**
     * Возвращает уникальный идентификатор вакансии.
     * 
     * @return идентификатор вакансии
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Устанавливает уникальный идентификатор вакансии.
     * 
     * @param id идентификатор вакансии
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Возвращает название должности.
     * 
     * @return название должности
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Устанавливает название должности.
     * 
     * @param title название должности
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Возвращает название компании.
     * 
     * @return название компании
     */
    public String getCompany() {
        return company;
    }
    
    /**
     * Устанавливает название компании.
     * 
     * @param company название компании
     */
    public void setCompany(String company) {
        this.company = company;
    }
    
    /**
     * Возвращает зарплату.
     * 
     * @return зарплата (может быть null)
     */
    public Double getSalary() {
        return salary;
    }
    
    /**
     * Устанавливает зарплату.
     * 
     * @param salary зарплата (может быть null)
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    /**
     * Возвращает описание вакансии.
     * 
     * @return описание вакансии
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Устанавливает описание вакансии.
     * 
     * @param description описание вакансии
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Методы equals и hashCode
    
    /**
     * Сравнивает данный объект с другим объектом на равенство.
     * Две вакансии считаются равными, если у них одинаковый идентификатор.
     * 
     * @param o объект для сравнения
     * @return true если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }
    
    /**
     * Возвращает хэш-код объекта.
     * Хэш-код основан на идентификаторе вакансии.
     * 
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    /**
     * Возвращает строковое представление объекта вакансии.
     * 
     * @return строковое представление вакансии
     */
    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", description='" + description + '\'' +
                '}';
    }
}
package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности класса Job.
 */
class JobTest {
    
    @Test
    void testDefaultConstructor() {
        // Проверка создания объекта с конструктором по умолчанию
        Job job = new Job();
        
        assertNull(job.getId());
        assertNull(job.getTitle());
        assertNull(job.getCompany());
        assertNull(job.getSalary());
        assertNull(job.getDescription());
    }
    
    @Test
    void testParameterizedConstructorWithId() {
        // Проверка создания объекта с конструктором с параметрами (с ID)
        Long id = 1L;
        String title = "Java Developer";
        String company = "Tech Corp";
        Double salary = 100000.0;
        String description = "Разработка Java приложений";
        
        Job job = new Job(id, title, company, salary, description);
        
        assertEquals(id, job.getId());
        assertEquals(title, job.getTitle());
        assertEquals(company, job.getCompany());
        assertEquals(salary, job.getSalary());
        assertEquals(description, job.getDescription());
    }
    
    @Test
    void testParameterizedConstructorWithoutId() {
        // Проверка создания объекта с конструктором без ID
        String title = "Frontend Developer";
        String company = "Web Solutions";
        Double salary = 80000.0;
        String description = "Разработка пользовательских интерфейсов";
        
        Job job = new Job(title, company, salary, description);
        
        assertNull(job.getId());
        assertEquals(title, job.getTitle());
        assertEquals(company, job.getCompany());
        assertEquals(salary, job.getSalary());
        assertEquals(description, job.getDescription());
    }
    
    @Test
    void testGettersAndSetters() {
        // Проверка геттеров и сеттеров
        Job job = new Job();
        
        Long id = 2L;
        String title = "DevOps Engineer";
        String company = "Cloud Systems";
        Double salary = 120000.0;
        String description = "Настройка CI/CD и инфраструктуры";
        
        job.setId(id);
        job.setTitle(title);
        job.setCompany(company);
        job.setSalary(salary);
        job.setDescription(description);
        
        assertEquals(id, job.getId());
        assertEquals(title, job.getTitle());
        assertEquals(company, job.getCompany());
        assertEquals(salary, job.getSalary());
        assertEquals(description, job.getDescription());
    }
    
    @Test
    void testEqualsAndHashCode() {
        // Проверка методов equals и hashCode
        Job job1 = new Job(1L, "Java Developer", "Tech Corp", 100000.0, "Описание 1");
        Job job2 = new Job(1L, "Java Developer", "Tech Corp", 100000.0, "Описание 1");
        Job job3 = new Job(2L, "Frontend Developer", "Web Solutions", 80000.0, "Описание 2");
        
        // Проверка равенства объектов с одинаковым ID
        assertEquals(job1, job2);
        assertEquals(job1.hashCode(), job2.hashCode());
        
        // Проверка неравенства объектов с разными ID
        assertNotEquals(job1, job3);
        assertNotEquals(job1.hashCode(), job3.hashCode());
        
        // Проверка неравенства с null
        assertNotEquals(null, job1);
        
        // Проверка неравенства с объектом другого класса
        assertNotEquals("строка", job1);
        
        // Проверка рефлексивности
        assertEquals(job1, job1);
    }
    
    @Test
    void testToString() {
        // Проверка метода toString
        Job job = new Job(3L, "QA Engineer", "Test Systems", 70000.0, "Тестирование ПО");
        
        String expected = "Job{id=3, title='QA Engineer', company='Test Systems', salary=70000.0, description='Тестирование ПО'}";
        assertEquals(expected, job.toString());
    }
    
    @Test
    void testNullSalary() {
        // Проверка работы с null значением зарплаты
        Job job = new Job(4L, "Intern", "Startup", null, "Стажировка");
        
        assertNull(job.getSalary());
        assertTrue(job.toString().contains("salary=null"));
    }
    
    @Test
    void testEqualsWithNullId() {
        // Проверка equals с null ID
        Job job1 = new Job(null, "Developer", "Company", 50000.0, "Описание");
        Job job2 = new Job(null, "Developer", "Company", 50000.0, "Описание");
        
        // Два объекта с null ID не равны друг другу
        assertNotEquals(job1, job2);
    }
}
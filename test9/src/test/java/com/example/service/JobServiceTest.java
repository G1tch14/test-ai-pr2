package com.example.service;

import com.example.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса JobService.
 */
class JobServiceTest {
    
    private JobService jobService;
    
    @BeforeEach
    void setUp() {
        jobService = new JobService();
    }
    
    @Test
    @DisplayName("Создание валидной вакансии")
    void createJob_ValidJob_ReturnsCreatedJob() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        
        // Act
        Job created = jobService.createJob(job);
        
        // Assert
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Java Developer", created.getTitle());
        assertEquals("Tech Corp", created.getCompany());
        assertEquals(50000.0, created.getSalary());
        assertEquals("Разработка на Java", created.getDescription());
        assertEquals(1, jobService.getJobCount());
    }
    
    @Test
    @DisplayName("Создание вакансии с пустым заголовком - должно выбросить исключение")
    void createJob_EmptyTitle_ThrowsException() {
        // Arrange
        Job job = new Job("", "Tech Corp", 50000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertEquals("Заголовок вакансии не может быть пустым", exception.getMessage());
    }
    
    @Test
    @DisplayName("Создание вакансии с слишком коротким заголовком - должно выбросить исключение")
    void createJob_TooShortTitle_ThrowsException() {
        // Arrange
        Job job = new Job("Ja", "Tech Corp", 50000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertTrue(exception.getMessage().contains("Заголовок вакансии слишком короткий"));
    }
    
    @Test
    @DisplayName("Создание вакансии с слишком длинным заголовком - должно выбросить исключение")
    void createJob_TooLongTitle_ThrowsException() {
        // Arrange
        String longTitle = "A".repeat(101);
        Job job = new Job(longTitle, "Tech Corp", 50000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertTrue(exception.getMessage().contains("Заголовок вакансии слишком длинный"));
    }
    
    @Test
    @DisplayName("Создание вакансии с пустым названием компании - должно выбросить исключение")
    void createJob_EmptyCompany_ThrowsException() {
        // Arrange
        Job job = new Job("Java Developer", "", 50000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertEquals("Название компании не может быть пустым", exception.getMessage());
    }
    
    @Test
    @DisplayName("Создание вакансии с слишком коротким названием компании - должно выбросить исключение")
    void createJob_TooShortCompany_ThrowsException() {
        // Arrange
        Job job = new Job("Java Developer", "T", 50000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertTrue(exception.getMessage().contains("Название компании слишком короткое"));
    }
    
    @Test
    @DisplayName("Создание вакансии с отрицательной зарплатой - должно выбросить исключение")
    void createJob_NegativeSalary_ThrowsException() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", -100.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertTrue(exception.getMessage().contains("Зарплата не может быть отрицательной"));
    }
    
    @Test
    @DisplayName("Создание вакансии со слишком высокой зарплатой - должно выбросить исключение")
    void createJob_TooHighSalary_ThrowsException() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 2_000_000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertTrue(exception.getMessage().contains("Зарплата слишком высокая"));
    }
    
    @Test
    @DisplayName("Создание вакансии с зарплатой, имеющей более 2 знаков после запятой - должно выбросить исключение")
    void createJob_SalaryWithMoreThanTwoDecimals_ThrowsException() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.123, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertEquals("Зарплата должна иметь не более 2 знаков после запятой", exception.getMessage());
    }
    
    @Test
    @DisplayName("Создание вакансии с зарплатой, имеющей 2 знака после запятой - должно быть успешно")
    void createJob_SalaryWithTwoDecimals_ReturnsCreatedJob() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.12, "Разработка на Java");
        
        // Act
        Job created = jobService.createJob(job);
        
        // Assert
        assertNotNull(created);
        assertEquals(50000.12, created.getSalary());
    }
    
    @Test
    @DisplayName("Создание вакансии с null зарплатой - должно быть успешно")
    void createJob_NullSalary_ReturnsCreatedJob() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", null, "Разработка на Java");
        
        // Act
        Job created = jobService.createJob(job);
        
        // Assert
        assertNotNull(created);
        assertNull(created.getSalary());
    }
    
    @Test
    @DisplayName("Создание вакансии с слишком длинным описанием - должно выбросить исключение")
    void createJob_TooLongDescription_ThrowsException() {
        // Arrange
        String longDescription = "A".repeat(1001);
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, longDescription);
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.createJob(job)
        );
        assertTrue(exception.getMessage().contains("Описание вакансии слишком длинное"));
    }
    
    @Test
    @DisplayName("Создание вакансии с null описанием - должно быть успешно")
    void createJob_NullDescription_ReturnsCreatedJob() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, null);
        
        // Act
        Job created = jobService.createJob(job);
        
        // Assert
        assertNotNull(created);
        assertNull(created.getDescription());
    }
    
    @Test
    @DisplayName("Получение вакансии по существующему ID")
    void getJobById_ExistingId_ReturnsJob() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job created = jobService.createJob(job);
        Long id = created.getId();
        
        // Act
        Job found = jobService.getJobById(id);
        
        // Assert
        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Java Developer", found.getTitle());
    }
    
    @Test
    @DisplayName("Получение вакансии по несуществующему ID")
    void getJobById_NonExistingId_ReturnsNull() {
        // Act
        Job found = jobService.getJobById(999L);
        
        // Assert
        assertNull(found);
    }
    
    @Test
    @DisplayName("Получение вакансии по null ID - должно выбросить исключение")
    void getJobById_NullId_ThrowsException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.getJobById(null)
        );
        assertEquals("ID не может быть null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Получение всех вакансий из пустого хранилища")
    void getAllJobs_EmptyStorage_ReturnsEmptyList() {
        // Act
        List<Job> jobs = jobService.getAllJobs();
        
        // Assert
        assertNotNull(jobs);
        assertTrue(jobs.isEmpty());
    }
    
    @Test
    @DisplayName("Получение всех вакансий из непустого хранилища")
    void getAllJobs_NonEmptyStorage_ReturnsAllJobs() {
        // Arrange
        Job job1 = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job job2 = new Job("Frontend Developer", "Web Inc", 45000.0, "Разработка интерфейсов");
        
        jobService.createJob(job1);
        jobService.createJob(job2);
        
        // Act
        List<Job> jobs = jobService.getAllJobs();
        
        // Assert
        assertNotNull(jobs);
        assertEquals(2, jobs.size());
    }
    
    @Test
    @DisplayName("Обновление существующей вакансии")
    void updateJob_ExistingJob_ReturnsUpdatedJob() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job created = jobService.createJob(job);
        Long id = created.getId();
        
        Job updatedData = new Job("Senior Java Developer", "Tech Corp", 70000.0, "Разработка на Java, архитектура");
        
        // Act
        Job updated = jobService.updateJob(id, updatedData);
        
        // Assert
        assertNotNull(updated);
        assertEquals(id, updated.getId());
        assertEquals("Senior Java Developer", updated.getTitle());
        assertEquals(70000.0, updated.getSalary());
        assertEquals("Разработка на Java, архитектура", updated.getDescription());
        
        // Проверяем, что данные действительно обновились
        Job retrieved = jobService.getJobById(id);
        assertEquals("Senior Java Developer", retrieved.getTitle());
        assertEquals(70000.0, retrieved.getSalary());
    }
    
    @Test
    @DisplayName("Обновление несуществующей вакансии - должно выбросить исключение")
    void updateJob_NonExistingJob_ThrowsException() {
        // Arrange
        Job updatedData = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.updateJob(999L, updatedData)
        );
        assertEquals("Вакансия с ID 999 не найдена", exception.getMessage());
    }
    
    @Test
    @DisplayName("Обновление вакансии с невалидными данными - должно выбросить исключение")
    void updateJob_InvalidData_ThrowsException() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job created = jobService.createJob(job);
        Long id = created.getId();
        
        Job updatedData = new Job("", "Tech Corp", 50000.0, "Разработка на Java");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.updateJob(id, updatedData)
        );
        assertEquals("Заголовок вакансии не может быть пустым", exception.getMessage());
    }
    
    @Test
    @DisplayName("Удаление существующей вакансии")
    void deleteJob_ExistingJob_ReturnsTrue() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job created = jobService.createJob(job);
        Long id = created.getId();
        
        // Act
        boolean result = jobService.deleteJob(id);
        
        // Assert
        assertTrue(result);
        assertNull(jobService.getJobById(id));
        assertEquals(0, jobService.getJobCount());
    }
    
    @Test
    @DisplayName("Удаление несуществующей вакансии")
    void deleteJob_NonExistingJob_ReturnsFalse() {
        // Act
        boolean result = jobService.deleteJob(999L);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    @DisplayName("Удаление вакансии по null ID - должно выбросить исключение")
    void deleteJob_NullId_ThrowsException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.deleteJob(null)
        );
        assertEquals("ID не может быть null", exception.getMessage());
    }
    
    @Test
    @DisplayName("Очистка хранилища")
    void clearStorage_RemovesAllJobs() {
        // Arrange
        Job job1 = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job job2 = new Job("Frontend Developer", "Web Inc", 45000.0, "Разработка интерфейсов");
        
        jobService.createJob(job1);
        jobService.createJob(job2);
        
        // Act
        jobService.clearStorage();
        
        // Assert
        assertEquals(0, jobService.getJobCount());
        assertTrue(jobService.getAllJobs().isEmpty());
    }
    
    @Test
    @DisplayName("Поиск вакансий по названию компании")
    void findJobsByCompany_ExistingCompany_ReturnsJobs() {
        // Arrange
        Job job1 = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job job2 = new Job("Frontend Developer", "Web Inc", 45000.0, "Разработка интерфейсов");
        Job job3 = new Job("Backend Developer", "Tech Corp", 55000.0, "Разработка бэкенда");
        
        jobService.createJob(job1);
        jobService.createJob(job2);
        jobService.createJob(job3);
        
        // Act
        List<Job> techCorpJobs = jobService.findJobsByCompany("Tech Corp");
        List<Job> webIncJobs = jobService.findJobsByCompany("Web Inc");
        List<Job> nonExistingJobs = jobService.findJobsByCompany("Non Existing");
        
        // Assert
        assertEquals(2, techCorpJobs.size());
        assertEquals(1, webIncJobs.size());
        assertTrue(nonExistingJobs.isEmpty());
    }
    
    @Test
    @DisplayName("Поиск вакансий по названию компании (регистронезависимый)")
    void findJobsByCompany_CaseInsensitive_ReturnsJobs() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        jobService.createJob(job);
        
        // Act
        List<Job> lowerCase = jobService.findJobsByCompany("tech corp");
        List<Job> upperCase = jobService.findJobsByCompany("TECH CORP");
        List<Job> mixedCase = jobService.findJobsByCompany("TeCh CoRp");
        
        // Assert
        assertEquals(1, lowerCase.size());
        assertEquals(1, upperCase.size());
        assertEquals(1, mixedCase.size());
    }
    
    @Test
    @DisplayName("Поиск вакансий по названию компании с пустым запросом - должно выбросить исключение")
    void findJobsByCompany_EmptyCompany_ThrowsException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> jobService.findJobsByCompany("")
        );
        assertEquals("Название компании не может быть пустым для поиска", exception.getMessage());
    }
    
    @Test
    @DisplayName("Поиск вакансий по диапазону зарплаты")
    void findJobsBySalaryRange_ValidRange_ReturnsJobs() {
        // Arrange
        Job job1 = new Job("Junior Developer", "Tech Corp", 30000.0, "Разработка");
        Job job2 = new Job("Middle Developer", "Tech Corp", 50000.0, "Разработка");
        Job job3 = new Job("Senior Developer", "Tech Corp", 70000.0, "Разработка");
        Job job4 = new Job("Intern", "Tech Corp", null, "Стажировка");
        
        jobService.createJob(job1);
        jobService.createJob(job2);
        jobService.createJob(job3);
        jobService.createJob(job4);
        
        // Act
        List<Job> range30kTo60k = jobService.findJobsBySalaryRange(30000.0, 60000.0);
        List<Job> range40kTo80k = jobService.findJobsBySalaryRange(40000.0, 80000.0);
        List<Job> min50k = jobService.findJobsBySalaryRange(50000.0, null);
        List<Job> max40k = jobService.findJobsBySalaryRange(null, 40000.0);
        
        // Assert
        assertEquals(2, range30kTo60k.size()); // Junior и Middle
        assertEquals(2, range40kTo80k.size()); // Middle и Senior
        assertEquals(2, min50k.size()); // Middle и Senior
        assertEquals(1, max40k.size()); // Junior
    }
    
    @Test
    @DisplayName("Проверка существования вакансии")
    void jobExists_ReturnsCorrectBoolean() {
        // Arrange
        Job job = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job created = jobService.createJob(job);
        Long id = created.getId();
        
        // Act & Assert
        assertTrue(jobService.jobExists(id));
        assertFalse(jobService.jobExists(999L));
        assertFalse(jobService.jobExists(null));
    }
    
    @Test
    @DisplayName("Создание нескольких вакансий с уникальными ID")
    void createMultipleJobs_HaveUniqueIds() {
        // Arrange
        Job job1 = new Job("Java Developer", "Tech Corp", 50000.0, "Разработка на Java");
        Job job2 = new Job("Frontend Developer", "Web Inc", 45000.0, "Разработка интерфейсов");
        Job job3 = new Job("Backend Developer", "API Corp", 55000.0, "Разработка бэкенда");
        
        // Act
        Job created1 = jobService.createJob(job1);
        Job created2 = jobService.createJob(job2);
        Job created3 = jobService.createJob(job3);
        
        // Assert
        assertNotNull(created1.getId());
        assertNotNull(created2.getId());
        assertNotNull(created3.getId());
        
        assertNotEquals(created1.getId(), created2.getId());
        assertNotEquals(created1.getId(), created3.getId());
        assertNotEquals(created2.getId(), created3.getId());
        
        assertEquals(3, jobService.getJobCount());
    }
}
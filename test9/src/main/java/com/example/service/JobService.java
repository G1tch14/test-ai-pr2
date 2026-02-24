package com.example.service;

import com.example.model.Job;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Сервис для управления объектами Job (вакансиями).
 * Предоставляет CRUD операции с использованием in-memory хранилища.
 * Включает валидацию данных: проверку зарплаты, длины заголовка и других полей.
 */
public class JobService {
    
    /** In-memory хранилище для объектов Job */
    private final Map<Long, Job> jobStorage = new HashMap<>();
    
    /** Генератор уникальных идентификаторов */
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /** Минимальная допустимая зарплата */
    private static final double MIN_SALARY = 0.0;
    
    /** Максимальная допустимая зарплата */
    private static final double MAX_SALARY = 1_000_000.0;
    
    /** Минимальная длина заголовка вакансии */
    private static final int MIN_TITLE_LENGTH = 3;
    
    /** Максимальная длина заголовка вакансии */
    private static final int MAX_TITLE_LENGTH = 100;
    
    /** Минимальная длина названия компании */
    private static final int MIN_COMPANY_LENGTH = 2;
    
    /** Максимальная длина названия компании */
    private static final int MAX_COMPANY_LENGTH = 100;
    
    /** Максимальная длина описания вакансии */
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    
    /**
     * Создает новую вакансию.
     * 
     * @param job объект Job для создания (без id)
     * @return созданный объект Job с присвоенным id
     * @throws IllegalArgumentException если данные невалидны
     */
    public Job createJob(Job job) {
        validateJob(job);
        
        // Генерируем новый id
        Long newId = idGenerator.getAndIncrement();
        Job newJob = new Job(
            newId,
            job.getTitle(),
            job.getCompany(),
            job.getSalary(),
            job.getDescription()
        );
        
        // Сохраняем в хранилище
        jobStorage.put(newId, newJob);
        return newJob;
    }
    
    /**
     * Получает вакансию по идентификатору.
     * 
     * @param id идентификатор вакансии
     * @return объект Job или null, если не найден
     */
    public Job getJobById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return jobStorage.get(id);
    }
    
    /**
     * Получает все вакансии.
     * 
     * @return список всех объектов Job
     */
    public List<Job> getAllJobs() {
        return new ArrayList<>(jobStorage.values());
    }
    
    /**
     * Обновляет данные вакансии.
     * 
     * @param id идентификатор вакансии для обновления
     * @param updatedJob обновленные данные
     * @return обновленный объект Job
     * @throws IllegalArgumentException если данные невалидны или вакансия не найдена
     */
    public Job updateJob(Long id, Job updatedJob) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        
        // Проверяем существование вакансии
        Job existingJob = jobStorage.get(id);
        if (existingJob == null) {
            throw new IllegalArgumentException("Вакансия с ID " + id + " не найдена");
        }
        
        // Валидируем обновленные данные
        validateJob(updatedJob);
        
        // Создаем обновленный объект
        Job updated = new Job(
            id,
            updatedJob.getTitle(),
            updatedJob.getCompany(),
            updatedJob.getSalary(),
            updatedJob.getDescription()
        );
        
        // Обновляем в хранилище
        jobStorage.put(id, updated);
        return updated;
    }
    
    /**
     * Удаляет вакансию по идентификатору.
     * 
     * @param id идентификатор вакансии для удаления
     * @return true если вакансия была удалена, false если не найдена
     */
    public boolean deleteJob(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return jobStorage.remove(id) != null;
    }
    
    /**
     * Валидирует объект Job.
     * 
     * @param job объект Job для валидации
     * @throws IllegalArgumentException если данные невалидны
     */
    private void validateJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job не может быть null");
        }
        
        // Валидация заголовка
        validateTitle(job.getTitle());
        
        // Валидация названия компании
        validateCompany(job.getCompany());
        
        // Валидация зарплаты
        validateSalary(job.getSalary());
        
        // Валидация описания
        validateDescription(job.getDescription());
    }
    
    /**
     * Валидирует заголовок вакансии.
     * 
     * @param title заголовок для валидации
     * @throws IllegalArgumentException если заголовок невалиден
     */
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Заголовок вакансии не может быть пустым");
        }
        
        String trimmedTitle = title.trim();
        if (trimmedTitle.length() < MIN_TITLE_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Заголовок вакансии слишком короткий (минимум %d символов)", MIN_TITLE_LENGTH)
            );
        }
        
        if (trimmedTitle.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Заголовок вакансии слишком длинный (максимум %d символов)", MAX_TITLE_LENGTH)
            );
        }
    }
    
    /**
     * Валидирует название компании.
     * 
     * @param company название компании для валидации
     * @throws IllegalArgumentException если название компании невалидно
     */
    private void validateCompany(String company) {
        if (company == null || company.trim().isEmpty()) {
            throw new IllegalArgumentException("Название компании не может быть пустым");
        }
        
        String trimmedCompany = company.trim();
        if (trimmedCompany.length() < MIN_COMPANY_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Название компании слишком короткое (минимум %d символов)", MIN_COMPANY_LENGTH)
            );
        }
        
        if (trimmedCompany.length() > MAX_COMPANY_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Название компании слишком длинное (максимум %d символов)", MAX_COMPANY_LENGTH)
            );
        }
    }
    
    /**
     * Валидирует зарплату.
     * 
     * @param salary зарплата для валидации (может быть null)
     * @throws IllegalArgumentException если зарплата невалидна
     */
    private void validateSalary(Double salary) {
        // Зарплата может быть null (не указана)
        if (salary == null) {
            return;
        }
        
        if (salary < MIN_SALARY) {
            throw new IllegalArgumentException(
                String.format("Зарплата не может быть отрицательной (минимальное значение: %.2f)", MIN_SALARY)
            );
        }
        
        if (salary > MAX_SALARY) {
            throw new IllegalArgumentException(
                String.format("Зарплата слишком высокая (максимальное значение: %.2f)", MAX_SALARY)
            );
        }
        
        // Проверяем, что зарплата имеет не более 2 знаков после запятой
        double roundedSalary = Math.round(salary * 100.0) / 100.0;
        if (Math.abs(salary - roundedSalary) > 0.001) {
            throw new IllegalArgumentException("Зарплата должна иметь не более 2 знаков после запятой");
        }
    }
    
    /**
     * Валидирует описание вакансии.
     * 
     * @param description описание для валидации (может быть null)
     * @throws IllegalArgumentException если описание невалидно
     */
    private void validateDescription(String description) {
        // Описание может быть null или пустым
        if (description == null || description.trim().isEmpty()) {
            return;
        }
        
        if (description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Описание вакансии слишком длинное (максимум %d символов)", MAX_DESCRIPTION_LENGTH)
            );
        }
    }
    
    /**
     * Поиск вакансий по названию компании.
     * 
     * @param company название компании для поиска
     * @return список вакансий указанной компании
     */
    public List<Job> findJobsByCompany(String company) {
        if (company == null || company.trim().isEmpty()) {
            throw new IllegalArgumentException("Название компании не может быть пустым для поиска");
        }
        
        String searchTerm = company.trim().toLowerCase();
        List<Job> result = new ArrayList<>();
        
        for (Job job : jobStorage.values()) {
            if (job.getCompany().toLowerCase().contains(searchTerm)) {
                result.add(job);
            }
        }
        
        return result;
    }
    
    /**
     * Поиск вакансий с зарплатой в указанном диапазоне.
     * 
     * @param minSalary минимальная зарплата (может быть null)
     * @param maxSalary максимальная зарплата (может быть null)
     * @return список вакансий с зарплатой в указанном диапазоне
     */
    public List<Job> findJobsBySalaryRange(Double minSalary, Double maxSalary) {
        List<Job> result = new ArrayList<>();
        
        for (Job job : jobStorage.values()) {
            Double salary = job.getSalary();
            
            // Если зарплата не указана, пропускаем вакансию
            if (salary == null) {
                continue;
            }
            
            boolean matchesMin = (minSalary == null) || (salary >= minSalary);
            boolean matchesMax = (maxSalary == null) || (salary <= maxSalary);
            
            if (matchesMin && matchesMax) {
                result.add(job);
            }
        }
        
        return result;
    }
    
    /**
     * Очищает хранилище (для тестирования).
     */
    public void clearStorage() {
        jobStorage.clear();
        idGenerator.set(1);
    }
    
    /**
     * Возвращает количество вакансий в хранилище.
     * 
     * @return количество вакансий
     */
    public int getJobCount() {
        return jobStorage.size();
    }
    
    /**
     * Проверяет, существует ли вакансия с указанным ID.
     * 
     * @param id идентификатор вакансии
     * @return true если вакансия существует, false в противном случае
     */
    public boolean jobExists(Long id) {
        if (id == null) {
            return false;
        }
        return jobStorage.containsKey(id);
    }
}
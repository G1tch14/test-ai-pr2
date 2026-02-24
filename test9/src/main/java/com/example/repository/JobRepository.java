package com.example.repository;

import com.example.model.Job;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория для работы с сущностью Job.
 * Предоставляет абстракцию для доступа к данным и определяет CRUD операции.
 */
public interface JobRepository {
    
    /**
     * Сохраняет объект Job.
     * Если у объекта нет идентификатора (id == null), создает новую запись.
     * Если идентификатор существует, обновляет существующую запись.
     * 
     * @param job объект Job для сохранения
     * @return сохраненный объект Job с присвоенным идентификатором
     */
    Job save(Job job);
    
    /**
     * Находит объект Job по его идентификатору.
     * 
     * @param id идентификатор объекта Job
     * @return Optional с найденным объектом Job, если он существует,
     *         или пустой Optional, если объект не найден
     */
    Optional<Job> findById(Long id);
    
    /**
     * Возвращает список всех объектов Job.
     * 
     * @return список всех объектов Job
     */
    List<Job> findAll();
    
    /**
     * Обновляет существующий объект Job.
     * Объект должен иметь существующий идентификатор (id != null).
     * 
     * @param job объект Job с обновленными данными
     * @return обновленный объект Job
     * @throws IllegalArgumentException если объект не имеет идентификатора
     */
    Job update(Job job);
    
    /**
     * Удаляет объект Job по его идентификатору.
     * 
     * @param id идентификатор объекта Job для удаления
     * @return true если объект был успешно удален, false если объект не найден
     */
    boolean delete(Long id);
    
    /**
     * Удаляет указанный объект Job.
     * 
     * @param job объект Job для удаления
     * @return true если объект был успешно удален, false если объект не найден
     */
    boolean delete(Job job);
}
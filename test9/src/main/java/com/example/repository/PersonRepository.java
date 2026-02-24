package com.example.repository;

import com.example.model.Person;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория для работы с сущностью Person.
 * Предоставляет абстракцию для доступа к данным и определяет CRUD операции.
 */
public interface PersonRepository {
    
    /**
     * Сохраняет объект Person.
     * Если у объекта нет идентификатора (id == null), создает новую запись.
     * Если идентификатор существует, обновляет существующую запись.
     * 
     * @param person объект Person для сохранения
     * @return сохраненный объект Person с присвоенным идентификатором
     */
    Person save(Person person);
    
    /**
     * Находит объект Person по его идентификатору.
     * 
     * @param id идентификатор объекта Person
     * @return Optional с найденным объектом Person, если он существует,
     *         или пустой Optional, если объект не найден
     */
    Optional<Person> findById(Long id);
    
    /**
     * Возвращает список всех объектов Person.
     * 
     * @return список всех объектов Person
     */
    List<Person> findAll();
    
    /**
     * Обновляет существующий объект Person.
     * Объект должен иметь существующий идентификатор (id != null).
     * 
     * @param person объект Person с обновленными данными
     * @return обновленный объект Person
     * @throws IllegalArgumentException если объект не имеет идентификатора
     */
    Person update(Person person);
    
    /**
     * Удаляет объект Person по его идентификатору.
     * 
     * @param id идентификатор объекта Person для удаления
     * @return true если объект был успешно удален, false если объект не найден
     */
    boolean delete(Long id);
    
    /**
     * Удаляет указанный объект Person.
     * 
     * @param person объект Person для удаления
     * @return true если объект был успешно удален, false если объект не найден
     */
    boolean delete(Person person);
}
package com.example.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Job entity.
 */
class JobTest {

    @Test
    void testDefaultConstructor() {
        Job job = new Job();
        assertNull(job.getId());
        assertNull(job.getTitle());
        assertNull(job.getCompany());
        assertNull(job.getSalary());
        assertNull(job.getDescription());
    }

    @Test
    void testParameterizedConstructor() {
        String title = "Software Engineer";
        String company = "Tech Corp";
        BigDecimal salary = new BigDecimal("85000.00");
        String description = "Develop and maintain software applications";

        Job job = new Job(title, company, salary, description);

        assertNull(job.getId());
        assertEquals(title, job.getTitle());
        assertEquals(company, job.getCompany());
        assertEquals(salary, job.getSalary());
        assertEquals(description, job.getDescription());
    }

    @Test
    void testSettersAndGetters() {
        Job job = new Job();

        Long id = 1L;
        String title = "Data Analyst";
        String company = "Data Inc";
        BigDecimal salary = new BigDecimal("75000.50");
        String description = "Analyze data and create reports";

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
        Job job1 = new Job("Job A", "Company A", new BigDecimal("50000.00"), "Description A");
        job1.setId(1L);

        Job job2 = new Job("Job B", "Company B", new BigDecimal("60000.00"), "Description B");
        job2.setId(1L);

        Job job3 = new Job("Job A", "Company A", new BigDecimal("50000.00"), "Description A");
        job3.setId(2L);

        // Same ID should be equal
        assertEquals(job1, job2);
        assertEquals(job1.hashCode(), job2.hashCode());

        // Different ID should not be equal
        assertNotEquals(job1, job3);
        assertNotEquals(job1.hashCode(), job3.hashCode());

        // Test with null
        assertNotEquals(null, job1);

        // Test with different class
        assertNotEquals("string", job1);

        // Test with same object
        assertEquals(job1, job1);
    }

    @Test
    void testToString() {
        Job job = new Job("Software Developer", "Dev Corp", new BigDecimal("95000.75"), "Write code");
        job.setId(1L);

        String toString = job.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("title='Software Developer'"));
        assertTrue(toString.contains("company='Dev Corp'"));
        assertTrue(toString.contains("salary=95000.75"));
        assertTrue(toString.contains("description='Write code'"));
    }

    @Test
    void testSalaryPrecision() {
        BigDecimal salary = new BigDecimal("123456.78");
        Job job = new Job("Test Job", "Test Company", salary, "Test Description");
        
        assertEquals(salary, job.getSalary());
        
        // Test with more decimal places
        BigDecimal detailedSalary = new BigDecimal("123456.789");
        job.setSalary(detailedSalary);
        assertEquals(detailedSalary, job.getSalary());
    }
}
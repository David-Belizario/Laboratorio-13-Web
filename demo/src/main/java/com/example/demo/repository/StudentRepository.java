package com.example.demo.repository;


import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find students by name
    List<Student> findByName(String name);

    // Find students by age range
    List<Student> findByAgeBetween(int minAge, int maxAge);

    // Find students whose name starts with a specific prefix
    @Query("SELECT s FROM Student s WHERE s.name LIKE ?1%")
    List<Student> findByPrefix(String prefix);
}




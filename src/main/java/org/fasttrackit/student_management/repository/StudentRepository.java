package org.fasttrackit.student_management.repository;

import org.fasttrackit.student_management.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student,Long>{

    //queries derived from method names
    Page<Student> findByFirstNameContaining(String partialFirstName, Pageable pageable);

    Page <Student> findByLastNameContaining(String partialLastName, Pageable pageable);
}

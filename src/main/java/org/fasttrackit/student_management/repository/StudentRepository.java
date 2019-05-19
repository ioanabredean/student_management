package org.fasttrackit.student_management.repository;

import org.fasttrackit.student_management.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student,Long>{
}

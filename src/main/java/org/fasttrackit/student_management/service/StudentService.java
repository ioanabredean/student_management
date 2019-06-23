package org.fasttrackit.student_management.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.student_management.domain.Student;
import org.fasttrackit.student_management.exception.ResourceNotFoundException;
import org.fasttrackit.student_management.repository.StudentRepository;
import org.fasttrackit.student_management.transfer.CreateStudentRequest;
import org.fasttrackit.student_management.transfer.GetStudentRequest;
import org.fasttrackit.student_management.transfer.UpdateStudentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    //IoC (inversion of control) and dependency injections

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    public Student createStudent (CreateStudentRequest request) {
        LOGGER.info("Creating student {}",  request);

        Student student=objectMapper.convertValue(request,Student.class);

      /*  Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setCourseEnrolment(request.getCourseEnrolment());
        student.setCourseGroup(request.getCourseGroup());

       */
    return studentRepository.save(student);
    }


    public Student getStudent (long id ) throws ResourceNotFoundException {

        LOGGER.info("Retrieving student {}", id);
        return studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student "+id +"does not exist."));
    }

    public Student updateStudent (long id,UpdateStudentRequest request) throws ResourceNotFoundException {

        LOGGER.info("Updating student {} with {}", id, request);
        Student student = getStudent(id);
        BeanUtils.copyProperties(request, student);
        return studentRepository.save(student);
    }


    public void deleteStudent(long id){
        LOGGER.info("Deleting Student {}", id);
        studentRepository.deleteById(id);
        LOGGER.info("Deleted Student {}", id);
    }

    public Page<Student> getStudent (GetStudentRequest request, Pageable pageable){
        LOGGER.info("Retrieving students {}", request);

        if (request.getPartialName()!=null){
            return studentRepository.findByNameContaining(request.getPartialName(),pageable);
        }
        else if (request.getPartialSurname()!=null){
            return studentRepository.findBySurnameContaining(request.getPartialSurname(),pageable);
        }
        return studentRepository.findAll(pageable);
    }

}

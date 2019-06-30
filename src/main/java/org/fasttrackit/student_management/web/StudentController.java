package org.fasttrackit.student_management.web;

import org.fasttrackit.student_management.domain.Student;
import org.fasttrackit.student_management.exception.ResourceNotFoundException;
import org.fasttrackit.student_management.service.StudentService;
import org.fasttrackit.student_management.transfer.CreateStudentRequest;
import org.fasttrackit.student_management.transfer.GetStudentRequest;
import org.fasttrackit.student_management.transfer.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //endpoint

    @GetMapping
    public ResponseEntity <Page<Student>> getStudent (GetStudentRequest request , Pageable pageable){
        Page <Student> response = studentService.getStudent(request,pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent (@RequestBody @Valid CreateStudentRequest request){
        Student response = studentService.createStudent(request);
        return  new ResponseEntity<>(response ,HttpStatus.CREATED);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity deleteStudent (@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody @Valid UpdateStudentRequest request)
            throws ResourceNotFoundException {
        studentService.updateStudent(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

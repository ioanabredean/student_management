package org.fasttrackit.student_management;

import org.fasttrackit.student_management.domain.Student;
import org.fasttrackit.student_management.exception.ResourceNotFoundException;
import org.fasttrackit.student_management.service.StudentService;
import org.fasttrackit.student_management.transfer.CreateStudentRequest;
import org.fasttrackit.student_management.transfer.UpdateStudentRequest;
import org.hamcrest.Matchers;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceIntegrationTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void testCreateStudent_whenValidRequest_thenReturnCreatedStudent(){

        createStudent();
    }

    private Student createStudent() {
        CreateStudentRequest request = new CreateStudentRequest();
        request.setName("Ion");
        request.setSurname("Pop");
        request.setCourseEnrolment("Java");
        request.setCourseGroup("grupa 11");

        Student createdStudent = studentService.createStudent(request);

        assertThat(createdStudent,notNullValue());
        assertThat(createdStudent.getId(),greaterThan(0L));
        assertThat(createdStudent.getName(), is(request.getName()));
        assertThat(createdStudent.getSurname(),is(request.getSurname()));
        assertThat(createdStudent.getCourseEnrolment(),is(request.getCourseEnrolment()));
        assertThat(createdStudent.getCourseGroup(),is(request.getCourseGroup()));

        return createdStudent;
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateStudent_whenMissingMandatoryFields_thenThrowException (){
        CreateStudentRequest request = new CreateStudentRequest();
        studentService.createStudent(request);

    }

    @Test
    public void testFindStudentById_WhenValidRequest_thenReturnStudent () throws ResourceNotFoundException {
        Student createdStudent = createStudent();
        Student student=studentService.getStudent(createdStudent.getId());

        assertThat(student,notNullValue());
        assertThat(createdStudent.getId(),is(student.getId()) );
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindStudentById_WhenInvalidId_thenThrowException() throws ResourceNotFoundException {
        studentService.getStudent(9999L);
    }

    @Test
    public void testUpdateStudent_whenValidRequest_thenReturn_updatedStudent() throws ResourceNotFoundException {
        Student createdStudent = createStudent();
        UpdateStudentRequest request =new UpdateStudentRequest();
        request.setName(createdStudent.getName()+"updated");
        request.setSurname(createdStudent.getSurname()+"updated");
        request.setCourseGroup(createdStudent.getCourseGroup()+"updated");
        request.setCourseEnrolment(createdStudent.getCourseEnrolment()+"updated");

        Student updatedStudent=studentService.updateStudent(createdStudent.getId(),request);

        assertThat(updatedStudent, notNullValue());
        assertThat(updatedStudent.getName(), is(request.getName()));
        assertThat(updatedStudent.getSurname(),is(request.getSurname()));
        assertThat(updatedStudent.getCourseGroup(),is(request.getCourseGroup()));
        assertThat(updatedStudent.getCourseEnrolment(),is(request.getCourseEnrolment()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateStudent_WhenInvalidId_thenThrowException() throws ResourceNotFoundException {
        UpdateStudentRequest request =new UpdateStudentRequest();
        studentService.updateStudent(9999L, request);
    }

    @Test
    public void testDeleteStudentById_whenValidRequest_returnNull(){
        Student createdStudent = createStudent();
        studentService.deleteStudent(createdStudent.getId());

        assertThat( createdStudent.getId(), nullValue());
    }
}

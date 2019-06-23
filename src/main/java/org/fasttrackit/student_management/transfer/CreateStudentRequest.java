package org.fasttrackit.student_management.transfer;

import javax.validation.constraints.NotNull;

public class CreateStudentRequest {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String courseEnrolment;
    @NotNull
    private String courseGroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCourseEnrolment() {
        return courseEnrolment;
    }

    public void setCourseEnrolment(String courseEnrolment) {
        this.courseEnrolment = courseEnrolment;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    @Override
    public String   toString() {
        return "CreateStudentRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", courseEnrolment='" + courseEnrolment + '\'' +
                ", courseGroup='" + courseGroup + '\'' +
                '}';
    }
}

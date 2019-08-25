package org.fasttrackit.student_management.transfer;

import javax.validation.constraints.NotNull;

public class CreateStudentRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String courseEnrolment;
    @NotNull
    private String courseGroup;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String toString() {
        return "CreateStudentRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseEnrolment='" + courseEnrolment + '\'' +
                ", courseGroup='" + courseGroup + '\'' +
                '}';
    }
}

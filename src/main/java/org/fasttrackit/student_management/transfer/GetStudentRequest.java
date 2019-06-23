package org.fasttrackit.student_management.transfer;

public class GetStudentRequest {

    private String partialName;
    private String partialSurname;

    @Override
    public String toString() {
        return "GetStudentRequest{" +
                "partialName='" + partialName + '\'' +
                ", partialSurname='" + partialSurname + '\'' +
                '}';
    }

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public String getPartialSurname() {
        return partialSurname;
    }

    public void setPartialSurname(String partialSurname) {
        this.partialSurname = partialSurname;
    }

}

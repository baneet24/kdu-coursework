package org.example.question1;
public class MissingGradeException extends RuntimeException{

    private final int studentId;

    public MissingGradeException( int studentId) {

        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

}

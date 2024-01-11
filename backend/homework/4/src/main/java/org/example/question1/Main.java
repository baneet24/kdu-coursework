package org.example.question1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main (String[] args) {

        try {
            int[] firstStudentIdList = {1001, 1002};
            char[][] studentsGradesOne = {{'A', 'A', 'A', 'B'}};
            StudentUtil.calculateGPA(firstStudentIdList, studentsGradesOne);
        }
        catch (IllegalArgumentException | MissingGradeException exception) {
            logger.error("{}","IllegalArgumentException " + exception.getMessage());
        }

        try {
            int[] secondStudentIdList = {1002};
            char[][] studentsGradesTwo = {{'A', 'B', ' '}};
            StudentUtil.calculateGPA(secondStudentIdList, studentsGradesTwo);
        }

        catch (MissingGradeException exception) {
            String formattedMessage = String.format("studentID with missing grades: %d", exception.getStudentId());
            throw new InvalidDataException(formattedMessage, exception);
        }
    }
}
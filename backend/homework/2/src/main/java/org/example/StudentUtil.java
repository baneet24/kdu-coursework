package org.example;

import java.util.ArrayList;


public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][]  studentsGrades) {
        int listSize = studentIdList.length;
        int gradePoints, subjectCount, gradePoint;
        char grade;
        double[] pointsArray = new double[listSize];

        for(int i = 0; i<listSize; i++){
        gradePoints = 0;
        subjectCount = studentsGrades[i].length;

        for (int j = 0; j < subjectCount; j++) {
            grade = studentsGrades[i][j];
            gradePoint = PointsFromGrade(grade);
            gradePoints += gradePoint;
        }

        double gpa = (double) gradePoints / subjectCount;
        pointsArray[i] = gpa;
    }

        return pointsArray;

    }

    static int PointsFromGrade(char grade){
        return switch (grade) {
            case 'A' -> 4;
            case 'B' -> 3;
            case 'C' -> 2;
            default -> 0;
        };
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades){
       if(lower > higher || lower < 0 || higher < 0) return null;
       int size= studentIdList.length;
       double[] pointsArr = calculateGPA(studentIdList, studentsGrades);

        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < pointsArr.length; i++) {

            if (pointsArr[i] >= lower && pointsArr[i] <= higher) {
                result.add(studentIdList[i]);
            }
        }

        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }


       return resultArr;
    }
}

package com.example.lin.reportcard;

/**
 * Created by Lin on 08/09/2017.
 * <p>
 * This is a representation of a report card for a student.
 */

public class ReportCard {
    private String first_name;
    private String last_name;
    private int student_id;
    private int english_grade;
    private int math_grade;
    private int biology_grade;
    private int history_grade;
    private int chemistry_grade;

    public ReportCard(String firstName, String lastName, int id) {
        first_name = firstName;
        last_name = lastName;
        student_id = id;
        english_grade = math_grade = biology_grade = history_grade = chemistry_grade = 0;
    }

    /**
     * Obtain the student's first name from the record.
     *
     * @return student's first name as a string.
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * Input the student's first name.
     *
     * @param firstName first name of the student as a string.
     */
    public void setFirstName(String firstName) {
        first_name = firstName;
    }

    /**
     * Obtain the student's last name from the record.
     *
     * @return student's last name as a string.
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * Input the student's last name.
     *
     * @param lastName last name of the student as a string.
     */
    public void setLastName(String lastName) {
        last_name = lastName;
    }

    /**
     * Obtain the student's id number from the record.
     *
     * @return student's id number as an int.
     */
    public int getStudentID() {
        return student_id;
    }

    /**
     * Input the student's id number
     *
     * @param id id number of a student as an int
     */
    public void setStudentID(int id) {
        student_id = id;
    }

    /**
     * Obtain the student's English grade for current semester.
     *
     * @return the english grade of the student as an int.
     */
    public int getEnglishGrade() {
        return english_grade;
    }

    /**
     * Input the student's grade for English.
     *
     * @param grade English grade of a student as an int
     */
    public void setEnglishGrade(int grade) {
        english_grade = grade;
    }

    /**
     * Obtain the student's Math grade for current semester.
     *
     * @return the Math grade of the student as an int.
     */
    public int getMathGrade() {
        return math_grade;
    }

    /**
     * Input the student's grade for Math.
     *
     * @param grade Math grade of a student as an int
     */
    public void setMathGrade(int grade) {
        math_grade = grade;
    }

    /**
     * Obtain the student's Biology grade for current semester.
     *
     * @return the Biology grade of the student as an int.
     */
    public int getBiologyGrade() {
        return biology_grade;
    }

    /**
     * Input the student's grade for Biology.
     *
     * @param grade Biology grade of a student as an int
     */
    public void setBiologyGrade(int grade) {
        biology_grade = grade;
    }

    /**
     * Obtain the student's History grade for current semester.
     *
     * @return the History grade of the student as an int.
     */
    public int getHistoryGrade() {
        return history_grade;
    }

    /**
     * Input the student's grade for History.
     *
     * @param grade History grade of a student as an int
     */
    public void setHistoryGrade(int grade) {
        history_grade = grade;
    }

    /**
     * Obtain the student's Chemistry grade for current semester.
     *
     * @return the Chemistry grade of the student as an int.
     */
    public int getChemistryGrade() {
        return chemistry_grade;
    }

    /**
     * Input the student's grade for Chemistry.
     *
     * @param grade Chemistry grade of a student as an int
     */
    public void setChemistryGrade(int grade) {
        chemistry_grade = grade;
    }

    /**
     * returns a string that shows the report card in following format:
     * Name: (first name) (last name)
     * ID: (student ID)
     * Grades: (Respective grade values)
     *
     * @return String
     */
    @Override
    public String toString() {
        return "==============================\n"
                + "Name: " + first_name + " " + last_name + "\n"
                + "ID: " + student_id + "\n"
                + "Biology Grade: " + biology_grade + "\n"
                + "Chemistry Grade: " + chemistry_grade + "\n"
                + "English Grade: " + english_grade + "\n"
                + "History Grade: " + history_grade + "\n"
                + "Mathematics Grade: " + math_grade + "\n"
                + "==============================\n";
    }
}

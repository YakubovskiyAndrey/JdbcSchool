package foxminded.ui;

import java.util.List;
import foxminded.domain.Group;
import foxminded.domain.Student;

public class ConsoleView implements View {

    @Override
    public void showStudentWithCourse(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getFirstName());
        }

    }

    @Override
    public void showGroupWithStudent(List<Group> groups) {
        for (Group group : groups) {
            System.out.println(group.getName());
        }

    }

    @Override
    public void showIdNewStudent(int studentId) {
        if (studentId > 0) {
            System.out.println("student successfully created, ID = " + studentId);
        } else {
            System.out.println("failed to create student");
        }

    }

    @Override
    public void showStudentDeletionResult(Boolean result) {
        if (Boolean.TRUE.equals(result)) {
            System.out.println("student successfully deleted");
        } else {
            System.out.println("failed to delete student");
        }

    }

    @Override
    public void showStudentAddedToCourse(Boolean result) {
        if (Boolean.TRUE.equals(result)) {
            System.out.println("student successfully added to course");
        } else {
            System.out.println("failed to added student");
        }

    }

    @Override
    public void showStudentRemovedFromCourse(Boolean result) {
        if (Boolean.TRUE.equals(result)) {
            System.out.println("student successfully removed from course");
        } else {
            System.out.println("failed to removed student");
        }

    }

    @Override
    public void printEnterStudentId() {
        System.out.println("enter student ID");
    }

    @Override
    public void printMenu() {
        System.out.println("you can do following actions:");
        System.out.println("a. Find all groups with less or equals student count");
        System.out.println("b. Find all students related to course with given name");
        System.out.println("c. Add new student");
        System.out.println("d. Delete student by STUDENT_ID");
        System.out.println("e. Add a student to the course");
        System.out.println("f. Remove the student from one of his or her courses");
        System.out.println("x. Exit");
        System.out.println("Please type your option");
    }

    @Override
    public void printSelectCourse() {
        System.out.println("select a course from the list (enter number)");
    }

    @Override
    public void printCourseNameMustBeSpecified() {
        System.out.println("course name must be specified");
    }

    @Override
    public void printEnterGroupId() {
        System.out.println("enter group ID");
    }

    @Override
    public void printFirstName() {
        System.out.println("enter first name");
    }

    @Override
    public void printLastName() {
        System.out.println("enter last name");
    }

    @Override
    public void printNumberOfStudents() {
        System.out.println("enter number of students");
    }

    @Override
    public void printGoodbye() {
        System.out.println("Goodbye =)");
    }

    @Override
    public void printEnterCourseName() {
        System.out.println("enter course name");
    }

}

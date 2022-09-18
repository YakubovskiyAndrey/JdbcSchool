package foxminded.ui;

import java.util.List;
import foxminded.domain.Group;
import foxminded.domain.Student;

public interface View {

    public void showStudentWithCourse(List<Student> students);

    public void showGroupWithStudent(List<Group> groups);

    public void showIdNewStudent(int studentId);

    public void showStudentDeletionResult(Boolean result);

    public void showStudentAddedToCourse(Boolean result);

    public void showStudentRemovedFromCourse(Boolean result);

    public void printEnterStudentId();

    public void printMenu();

    public void printSelectCourse();

    public void printCourseNameMustBeSpecified();

    public void printEnterGroupId();

    public void printFirstName();

    public void printLastName();

    public void printNumberOfStudents();

    public void printGoodbye();

    public void printEnterCourseName();

}

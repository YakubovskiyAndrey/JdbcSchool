package foxminded.service;

import java.sql.SQLException;
import java.util.List;
import foxminded.dao.StudentDao;
import foxminded.dao.StudentModel;
import foxminded.domain.Student;
import foxminded.ui.ConsoleView;
import foxminded.ui.View;

public class StudentsService {

    StudentModel modelLayer = new StudentDao();
    View view = new ConsoleView();

    public void getStudentsWithCourse(String courseName) throws SQLException {

        List<Student> students = modelLayer.getStudentsWithCourse(courseName);
        view.showStudentWithCourse(students);

    }

    public void createNewStudent(int groupId, String firstName, String lastName, String course) throws SQLException {

        int studentId = modelLayer.addNewStudent(groupId, firstName, lastName, course);
        view.showIdNewStudent(studentId);

    }

    public void createStudentInDatabase(int studentId, int groupId, String firstName, String lastName)
            throws SQLException {

        modelLayer.create(studentId, groupId, firstName, lastName);

    }

    public void deleteStudentById(int studentId) throws SQLException {

        boolean result = modelLayer.deleteStudentById(studentId);
        view.showStudentDeletionResult(result);

    }

    public void addStudentToCourse(int studentId, String course) throws SQLException {

        boolean result = modelLayer.addStudentToCourseByName(studentId, course);
        view.showStudentAddedToCourse(result);

    }

    public void addStudentToCourseById(int studentId, int courseId) throws SQLException {

        modelLayer.addStudentToCourse(studentId, courseId);

    }

    public void removeStudentFromCourse(int studentId, String course) throws SQLException {

        boolean result = modelLayer.removeStudentFromCourse(studentId, course);
        view.showStudentRemovedFromCourse(result);

    }

}

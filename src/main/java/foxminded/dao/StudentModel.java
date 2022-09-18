package foxminded.dao;

import java.sql.SQLException;
import java.util.List;

import foxminded.domain.Student;

public interface StudentModel {

    public List<Student> getStudentsWithCourse(String courseName) throws SQLException;

    public int addNewStudent(int groupId, String firstName, String lastName, String course) throws SQLException;

    public int getLastStudentId() throws SQLException;

    public void addStudentToCourse(int studentId, int courseId) throws SQLException;

    public void removeStudentFromCourses(int studentId) throws SQLException;

    public void create(int studentId, int groupId, String firstName, String lastName)
            throws SQLException;

    public boolean deleteStudentById(int studentId) throws SQLException;

    public boolean addStudentToCourseByName(int studentId, String course) throws SQLException;

    public boolean removeStudentFromCourse(int studentId, String course) throws SQLException;

}

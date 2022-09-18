package foxminded.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import foxminded.domain.Student;
import foxminded.service.CoursesService;
import foxminded.service.GroupsService;

public class StudentDao extends AbstractDao<Student> implements StudentModel {

    @Override
    protected Student mapRow(ResultSet resultSet) throws SQLException {
        return new Student(resultSet.getInt("STUDENT_ID"), 0, resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"), null);
    }

    @Override
    public List<Student> getStudentsWithCourse(String courseName) throws SQLException {

        String sql = String
                .format("SELECT STUDENTS.STUDENT_ID, STUDENTS.FIRST_NAME, STUDENTS.LAST_NAME\r\n"
                        + "FROM school.STUDENTS_COURSES\r\n"
                        + "INNER JOIN school.STUDENTS ON (STUDENTS_COURSES.STUDENT_ID = STUDENTS.STUDENT_ID)\r\n"
                        + "INNER JOIN school.COURSES ON (STUDENTS_COURSES.COURSE_ID = COURSES.COURSE_ID)\r\n"
                        + "where COURSES.course_name = '%s'", courseName);
        return loadEntity(sql);

    }

    @Override
    public int addNewStudent(int groupId, String firstName, String lastName, String course)
            throws SQLException {

        int studentId = getLastStudentId();

        CoursesService coursesService = new CoursesService();

        int courseId = coursesService.findIdByName(course);
        if (courseId == -1) {
            throw new IndexOutOfBoundsException("course not found");
        }

        create(studentId, groupId, firstName, lastName);

        return studentId;

    }

    @Override
    public void create(int studentId, int groupId, String firstName, String lastName)
            throws SQLException {

        String sql = String.format(
                "INSERT INTO school.students (student_id, group_id, first_name, last_name) VALUES (%d, %d, '%s', '%s')",
                studentId, groupId, firstName, lastName);
        updateEntity(sql);
    }

    @Override
    public int getLastStudentId() throws SQLException {

        String sql = "SELECT student_id FROM school.students order by student_id desc limit 1";
        return getEntityId(sql, "student_id") + 1;
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) throws SQLException {

        String sql = String.format(
                "INSERT INTO school.students_courses (student_id, course_id) VALUES ('%s', '%s')",
                studentId, courseId);
        updateEntity(sql);

    }

    @Override
    public boolean deleteStudentById(int studentId) throws SQLException {

        GroupsService groupsService = new GroupsService();
        int groupId = groupsService.getGroupIdByStudentId(studentId);
        if (groupId == -1) {
            throw new IndexOutOfBoundsException("group not found");
        }

        groupsService.removeStudentFromGroup(groupId);

        removeStudentFromCourses(studentId);

        String sql = String.format("DELETE FROM school.students WHERE student_id = %d", studentId);

        try {
            updateEntity(sql);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public void removeStudentFromCourses(int studentId) throws SQLException {

        String sql = String.format("DELETE FROM school.students_courses WHERE student_id = %d",
                studentId);
        updateEntity(sql);
    }

    @Override
    public boolean addStudentToCourseByName(int studentId, String course) throws SQLException {

        CoursesService coursesService = new CoursesService();
        int courseId = coursesService.findIdByName(course);
        if (courseId == -1) {
            throw new IndexOutOfBoundsException("course not found");
        }

        String sql = String.format(
                "INSERT INTO school.students_courses (student_id, course_id) VALUES (%d, %d)",
                studentId, courseId);

        try {
            updateEntity(sql);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean removeStudentFromCourse(int studentId, String course) throws SQLException {

        CoursesService coursesService = new CoursesService();
        int courseId = coursesService.findIdByName(course);
        if (courseId == -1) {
            throw new IndexOutOfBoundsException("course not found");
        }
        String sql = String.format(
                "DELETE FROM school.students_courses WHERE student_id = %d and course_id = %d",
                studentId, courseId);

        try {
            updateEntity(sql);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

}

package foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxminded.domain.Student;



class StudentDaoTest {

    StudentDao studentDao;

    @BeforeEach
    void setUp() {
        studentDao = new StudentDao();
    }

    @Test
    void testShouldReturnListOfStudentsInGivenCourse() throws SQLException {
        String courseName = "Biology";
        studentDao.addStudentToCourseByName(99, courseName);
        assertTrue(!studentDao.getStudentsWithCourse(courseName).isEmpty());
        studentDao.removeStudentFromCourse(99, courseName);
    }

    @Test
    void testAddDeleteNewStudent() throws SQLException {
        int studentId = studentDao.addNewStudent(5, "Brian", "Johnson", "English");
        assertTrue(studentId >= 0);
        assertTrue(studentDao.deleteStudentById(studentId));
    }

    @Test
    void testRemoveStudentFromCourses() throws SQLException {
        String courseName = "Astronomy";
        int studentId = studentDao.addNewStudent(5, "Elon", "Musk", courseName);
        List<Student> students = studentDao.getStudentsWithCourse(courseName);
        boolean studentOnCourse = false;
        for (Student student : students) {
            if (student.getFirstName().equals("Elon") && student.getLastName().equals("Musk")) {
                studentOnCourse = true;
                break;
            }
        }
        studentDao.removeStudentFromCourses(studentId);
        assertFalse(studentOnCourse);
        assertTrue(studentDao.deleteStudentById(studentId));
    }

    @Test
    void testLastStudentIdShouldBeMoreOrEqualTo200() throws SQLException {
        assertTrue(studentDao.getLastStudentId() >= 200);
    }


    @Test
    void testDeleteStudentWhenIdUndefinded() {
        assertThrows(IndexOutOfBoundsException.class, () -> studentDao.deleteStudentById(544));
    }

    @Test
    void testAddNewStudentWhenCourseUndefinded() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> studentDao.addNewStudent(5, "Brian", "Johnson", "Music"));
    }

    @Test
    void testAddStudentWhenCourseUndefinded() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> studentDao.addStudentToCourseByName(99, "Music"));
    }

    @Test
    void testRemoveStudentWhenCourseUndefinded() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> studentDao.removeStudentFromCourse(99, "Music"));
    }

    @Test
    void testStudentToCourseById() throws SQLException {
        studentDao.addStudentToCourse(99, 9);
        List<Student> students = studentDao.getStudentsWithCourse("Astronomy");

        boolean addedStudent = false;
        for (Student student : students) {
            if (student.getId() == 99) {
                addedStudent = true;
                break;
            }
        }

        assertTrue(addedStudent);
    }

}

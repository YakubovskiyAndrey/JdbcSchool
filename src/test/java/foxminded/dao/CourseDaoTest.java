package foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxminded.domain.Course;

class CourseDaoTest {

    CourseDao courseDao;

    @BeforeEach
    void setUp() {
        courseDao = new CourseDao();
    }

    @Test
    void testShouldReturnCourseId() throws SQLException {
        assertSame(1, courseDao.findIdByName("Math"));
    }

    @Test
    void testCreateNewCourse() throws SQLException {
        String courseName = "Algebra";
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(11, courseName, courseName));
        courseDao.create(courses);
        assertSame(11, courseDao.findIdByName(courseName));
    }

}

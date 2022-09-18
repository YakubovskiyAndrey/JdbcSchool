package foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GenerateTestDaoTest {

    GenerateTestDao generateTestDao;
    StudentDao studentDao;
    GroupDao groupDao;
    CourseDao courseDao;


    @BeforeEach
    void setUp() throws IOException, SQLException {
        generateTestDao = new GenerateTestDao();
        studentDao = new StudentDao();
        groupDao = new GroupDao();
        courseDao = new CourseDao();
        generateTestDao.generatedTables("createTables.sql");
    }

    @Test
    void testGenerateListOfStudents() throws SQLException {
        assertEquals(201, studentDao.getLastStudentId());
    }

    @Test
    void testGenerateListOfGroups() throws SQLException {
        assertFalse(groupDao.getGroupsWithStudent(30).isEmpty());
    }

    @Test
    void testGenerateListOfCourses() throws SQLException {
        assertEquals(1, courseDao.findIdByName("Math"));
    }

    @Test
    void testInvalidScriptSql() {
        assertThrows(SQLException.class,
                () -> generateTestDao.generatedTables("invalidScript.sql"));
    }

}

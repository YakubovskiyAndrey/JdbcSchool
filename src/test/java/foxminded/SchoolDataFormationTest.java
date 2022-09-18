package foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxminded.domain.Course;
import foxminded.domain.Group;
import foxminded.domain.Student;


class SchoolDataFormationTest {

    SchoolDataFormation schoolDataFormation;

    @BeforeEach
    void setUp() {
        schoolDataFormation = new SchoolDataFormation();
    }

    @Test
    void testGroupsMustBeCreatedInSpecifiedNumber() {
        assertEquals(10, schoolDataFormation.formGroups(10).size());
        assertEquals(5, schoolDataFormation.formGroups(5).size());
        assertTrue(schoolDataFormation.formGroups(0).isEmpty());
    }

    @Test
    void testTenСoursesMustBeCreated() {
        assertEquals(10, schoolDataFormation.formCourses().size());
    }

    @Test
    void testNineCourseIsAstronomy() {
        assertSame("Astronomy", schoolDataFormation.formCourses().get(8).getName());
    }

    @Test
    void testTwoHundredStudentsMustBeCreated() {
        List<Group> groups = schoolDataFormation.formGroups(10);
        List<Course> courses = schoolDataFormation.formCourses();
        assertEquals(200, schoolDataFormation.formStudents(groups, courses).size());
    }

    @Test
    void testGroupShouldBeFrom10To30Students() {
        List<Group> groups = schoolDataFormation.formGroups(10);
        List<Course> courses = schoolDataFormation.formCourses();
        schoolDataFormation.formStudents(groups, courses);
        for (Group group : groups) {
            if (group.getCountStudents() > 0) {
                assertTrue(group.getCountStudents() >= 10);
                assertTrue(group.getCountStudents() <= 30);
            }
        }

    }

    @Test
    void testStudentsMustBeAssignedCourses() {
        List<Group> groups = schoolDataFormation.formGroups(10);
        List<Course> courses = schoolDataFormation.formCourses();
        List<Student> students = schoolDataFormation.formStudents(groups, courses);
        assertFalse(students.get(0).getCourses().isEmpty());
    }


}

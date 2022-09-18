package foxminded.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxminded.domain.Group;
import foxminded.domain.Student;


class ConsoleViewTest {

    ConsoleView consoleView;
    ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        consoleView = new ConsoleView();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    void testShowStudentWithCourse() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, 0, "Elon", "Musk", null));
        consoleView.showStudentWithCourse(students);
        assertEquals("Elon", output.toString().trim());
    }

    @Test
    void testShowGroupWithStudent() {
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(0, "np-91", 0));
        consoleView.showGroupWithStudent(groups);
        assertEquals("np-91", output.toString().trim());
    }

    @Test
    void testNewStudentIdOutputResult() {
        consoleView.showIdNewStudent(1);
        assertEquals("student successfully created, ID = 1", output.toString().trim());
    }

    @Test
    void testShowNegativeStudentId() {
        consoleView.showIdNewStudent(-1);
        assertEquals("failed to create student", output.toString().trim());
    }

    @Test
    void testShowPositiveRemovalResult() {
        consoleView.showStudentDeletionResult(true);
        assertEquals("student successfully deleted", output.toString().trim());
    }

    @Test
    void testShowNegativeRemovalResult() {
        consoleView.showStudentDeletionResult(false);
        assertEquals("failed to delete student", output.toString().trim());
    }

    @Test
    void testShowPositiveResultOfAddingToCourse() {
        consoleView.showStudentAddedToCourse(true);
        assertEquals("student successfully added to course", output.toString().trim());
    }

    @Test
    void testShowNegativeResultOfAddingToCourse() {
        consoleView.showStudentAddedToCourse(false);
        assertEquals("failed to added student", output.toString().trim());
    }

    @Test
    void testShowPositiveResultOfRemovalFromCourse() {
        consoleView.showStudentRemovedFromCourse(true);
        assertEquals("student successfully removed from course", output.toString().trim());
    }

    @Test
    void testShowNegativeResultOfRemovalFromCourse() {
        consoleView.showStudentRemovedFromCourse(false);
        assertEquals("failed to removed student", output.toString().trim());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

}

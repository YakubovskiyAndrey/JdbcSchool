package foxminded.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserInterfaceTest {

    ByteArrayOutputStream output;
    ConsoleIO view;
    UserInterface userUi;


    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        view = mock(ConsoleIO.class);
        userUi = new UserInterface();
    }

    @Test
    void testInputX() throws SQLException {
        String textOutput = "you can do following actions:\r\n"
                + "a. Find all groups with less or equals student count\r\n"
                + "b. Find all students related to course with given name\r\n"
                + "c. Add new student\r\n" + "d. Delete student by STUDENT_ID\r\n"
                + "e. Add a student to the course\r\n"
                + "f. Remove the student from one of his or her courses\r\n" + "x. Exit\r\n"
                + "Please type your option\r\n" + "Goodbye =)";

        when(view.userOptionChar()).thenReturn('x');
        userUi.showMenu(view);
        assertEquals(textOutput, output.toString().trim());
    }

    @Test
    void testInputA() throws SQLException {
        when(view.nextInt()).thenReturn(30);
        userUi.findAllGroups(view);
        assertTrue(output.toString().trim().length() > 0);
    }

    @Test
    void testInputB() throws SQLException {
        when(view.nextLine()).thenReturn("Math");
        userUi.findStudentWithCourse(view);
        assertTrue(output.toString().trim().length() > 0);
    }

    @Test
    void testInputC() throws SQLException {
        when(view.nextInt()).thenReturn(7);
        when(view.nextLine()).thenReturn("Math");
        userUi.addNewStudent(view);
        assertEquals(
                "enter group ID\r\n" + "enter first name\r\n" + "enter last name\r\n"
                        + "enter course name\r\n" + "student successfully created, ID = 201",
                output.toString().trim());
    }

    @Test
    void testInputD() throws SQLException {
        when(view.nextInt()).thenReturn(201);
        userUi.removeStudent(view);
        assertEquals("enter student ID\r\n" + "student successfully deleted",
                output.toString().trim());
    }

    @Test
    void testInputE() throws SQLException {
        when(view.nextInt()).thenReturn(7);
        userUi.addStudentToCourse(view);
        assertEquals("enter student ID\r\n" + "select a course from the list (enter number)\r\n"
                + "1. Math\r\n" + "2. Biology\r\n" + "3. History\r\n" + "4. Geography\r\n"
                + "5. Chemistry\r\n" + "6. Physics\r\n" + "7. Informatics\r\n" + "8. Geometry\r\n"
                + "9. Astronomy\r\n" + "10. English\r\n" + "student successfully added to course",
                output.toString().trim());
    }

    @Test
    void testInputF() throws SQLException {
        when(view.nextInt()).thenReturn(7);
        when(view.nextLine()).thenReturn("Informatics");
        userUi.removeStudentFromCourse(view);
        assertEquals("enter student ID\r\n" + "enter course name\r\n"
                + "student successfully removed from course", output.toString().trim());
    }

    @AfterEach
    void tearDown() {
        System.setOut(null);
    }

}

package foxminded.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class UserActionTest {

    UserAction action;

    @Test
    void testFindAllGroups() {
        action = UserAction.fromOption('a');
        assertEquals(action, UserAction.FIND_ALL_GROUPS);
    }

    @Test
    void testFindStudentWithCourse() {
        action = UserAction.fromOption('b');
        assertEquals(action, UserAction.FIND_STUDENT_WITH_COURSE);
    }

    @Test
    void testAddNewStudent() {
        action = UserAction.fromOption('c');
        assertEquals(action, UserAction.ADD_NEW_STUDENT);
    }

    @Test
    void testRemoveStudent() {
        action = UserAction.fromOption('d');
        assertEquals(action, UserAction.REMOVE_STUDENT);
    }

    @Test
    void testAddStudentToCourse() {
        action = UserAction.fromOption('e');
        assertEquals(action, UserAction.ADD_STUDENT_TO_COURSE);
    }

    @Test
    void testRemoveStudentFromCourse() {
        action = UserAction.fromOption('f');
        assertEquals(action, UserAction.REMOVE_STUDENT_FROM_COURSE);
    }

    @Test
    void testExit() {
        action = UserAction.fromOption('x');
        assertEquals(action, UserAction.EXIT);
    }

    @Test
    void testNullChar() {
        action = UserAction.fromOption(' ');
        assertEquals(action, UserAction.EXIT);
    }

}

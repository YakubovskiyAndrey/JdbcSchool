package foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxminded.domain.Group;

class GroupDaoTest {

    GroupDao groupDao;

    @BeforeEach
    void setUp() {
        groupDao = new GroupDao();
    }

    @Test
    void testGroupListMustBeCompleted() throws SQLException {
        List<Group> groups = groupDao.getGroupsWithStudent(30);
        assertTrue(!groups.isEmpty());
    }

    @Test
    void testGroupMustNotBeNull() throws SQLException {
        assertNotNull(groupDao.getGroupById(7));
    }

    @Test
    void testMustFindGroupIdByStudentId() throws SQLException {
        assertTrue(groupDao.getGroupIdByStudentId(99) > 0);
    }

    @Test
    void testCountOfStudentsInGroupShouldIncrease() throws SQLException {
        int countOfStudentsBefore = groupDao.getGroupById(7).getCountStudents();
        groupDao.addStudentToGroup(7);
        int countOfStudentsAfter = groupDao.getGroupById(7).getCountStudents();
        assertTrue(countOfStudentsBefore < countOfStudentsAfter);
    }

    @Test
    void testCountOfStudentsInGroupShouldDecrease() throws SQLException {
        int countOfStudentsBefore = groupDao.getGroupById(7).getCountStudents();
        groupDao.removeStudentFromGroup(7);
        int countOfStudentsAfter = groupDao.getGroupById(7).getCountStudents();
        assertTrue(countOfStudentsBefore > countOfStudentsAfter);
    }

}

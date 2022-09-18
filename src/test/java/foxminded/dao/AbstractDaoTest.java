package foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import foxminded.domain.Group;

class AbstractDaoTest {

    AbstractDao<Group> abstractDao;


    @BeforeEach
    void setUp() {
        abstractDao = new AbstractDao<Group>() {
            @Override
            protected Group mapRow(ResultSet resultSet) throws SQLException {
                return new Group(resultSet.getInt("group_id"), resultSet.getString("group_name"),
                        resultSet.getInt("count_students"));
            }
        };
    }

    @Test
    void testGroupListMustBeCompleted() throws SQLException {
        String sql = String.format(
                "SELECT group_id, count_students, group_name FROM school.groups where school.groups.count_students <= %d",
                30);
        List<Group> groups = abstractDao.loadEntity(sql);
        assertTrue(!groups.isEmpty());
    }

    @Test
    void testUpdateWhenInvalidFormatSql() {
        assertThrows(SQLException.class, () -> abstractDao.updateEntity("s"));
    }

    @Test
    void testLoadEntityWhenInvalidFormatSql() {
        assertThrows(SQLException.class, () -> abstractDao.loadEntity("s"));
    }

    @Test
    void testGetEntityIdWhenInvalidFormatSql() throws SQLException {
        assertEquals(-1, abstractDao.getEntityId("s", "s"));
    }


    @Test
    void testGroupMustBeFound() throws SQLException {
        String sql = "SELECT group_id FROM school.groups order by group_id desc limit 1";
        int groupId = abstractDao.getEntityId(sql, "group_id");
        assertTrue(groupId >= 0);
    }

    @Test
    void testUpdateTableOfGroups() throws SQLException {
        String sqlUpdate = String.format(
                "INSERT INTO school.groups (group_id, count_students, group_name) VALUES (%d, %d, '%s')",
                11, 0, "lq-93");
        abstractDao.updateEntity(sqlUpdate);
        String sqlQuery = "SELECT group_id FROM school.groups where group_id = 11";
        int groupId = abstractDao.getEntityId(sqlQuery, "group_id");
        assertTrue(groupId >= 0);
        String sqlDelete = "DELETE FROM school.groups WHERE group_id = 11";
        abstractDao.updateEntity(sqlDelete);
        int groupId2 = abstractDao.getEntityId(sqlQuery, "group_id");
        assertTrue(groupId2 < 0);
    }

}

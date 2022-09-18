package foxminded.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import foxminded.domain.Group;

public class GroupDao extends AbstractDao<Group> implements GroupModel {

    @Override
    protected Group mapRow(ResultSet resultSet) throws SQLException {
        return new Group(resultSet.getInt("group_id"), resultSet.getString("group_name"),
                resultSet.getInt("count_students"));
    }

    @Override
    public List<Group> getGroupsWithStudent(int countOfStudents) throws SQLException {

        String sql = String.format(
                "SELECT group_id, count_students, group_name FROM school.groups where school.groups.count_students <= %d",
                countOfStudents);
        return loadEntity(sql);

    }

    @Override
    public Group getGroupById(int groupId) throws SQLException {

        String sql = String.format("SELECT * FROM school.groups where group_id = %d", groupId);
        List<Group> listGroup = loadEntity(sql);
        return listGroup.get(0);
    }

    @Override
    public void addStudentToGroup(int groupId) throws SQLException {

        String sql = String.format(
                "UPDATE school.groups SET count_students = count_students + 1 WHERE group_id = %d",
                groupId);
        updateEntity(sql);

    }

    @Override
    public void create(List<Group> groups) throws SQLException {

        for (Group group : groups) {
            String sql = String.format(
                    "INSERT INTO school.groups (group_id, count_students, group_name) VALUES (%d, %d, '%s')",
                    group.getId(), group.getCountStudents(), group.getName());
            updateEntity(sql);
        }
    }

    @Override
    public int getGroupIdByStudentId(int studentId) throws SQLException {

        String sql = String.format("SELECT group_id FROM school.students where student_id = %d",
                studentId);
        return getEntityId(sql, "group_id");
    }

    @Override
    public void removeStudentFromGroup(int groupId) throws SQLException {

        String sql = String.format(
                "UPDATE school.groups SET count_students = count_students - 1 WHERE group_id = %d",
                groupId);
        updateEntity(sql);
    }

}

package foxminded.dao;

import java.sql.SQLException;
import java.util.List;
import foxminded.domain.Group;

public interface GroupModel {

    public List<Group> getGroupsWithStudent(int countOfStudents) throws SQLException;

    public Group getGroupById(int groupId) throws SQLException;

    public void addStudentToGroup(int groupId) throws SQLException;

    public void removeStudentFromGroup(int groupId) throws SQLException;

    public void create(List<Group> groups) throws SQLException;

    public int getGroupIdByStudentId(int studentId) throws SQLException;

}

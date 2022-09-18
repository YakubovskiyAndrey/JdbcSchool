package foxminded.service;

import java.sql.SQLException;
import java.util.List;
import foxminded.dao.GroupDao;
import foxminded.dao.GroupModel;
import foxminded.domain.Group;
import foxminded.ui.ConsoleView;
import foxminded.ui.View;

public class GroupsService {

    GroupModel modelLayer = new GroupDao();
    View view = new ConsoleView();

    public void getGroupsWithStudent(int countOfStudents) throws SQLException {
        List<Group> groups = modelLayer.getGroupsWithStudent(countOfStudents);
        view.showGroupWithStudent(groups);

    }

    public void createGroupInDatabase(List<Group> groups) throws SQLException {
        modelLayer.create(groups);

    }

    public Group getGroupByID(int groupId) throws SQLException {
        return modelLayer.getGroupById(groupId);
    }

    public int getGroupIdByStudentId(int studentId) throws SQLException {
        return modelLayer.getGroupIdByStudentId(studentId);
    }

    public void addStudentToGroup(int groupId) throws SQLException {
        modelLayer.addStudentToGroup(groupId);
    }

    public void removeStudentFromGroup(int groupId) throws SQLException {
        modelLayer.removeStudentFromGroup(groupId);
    }

}

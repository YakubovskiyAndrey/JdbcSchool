package foxminded.dao;

import java.sql.SQLException;
import java.util.List;

import foxminded.domain.Course;

public interface CourseModel {

    public int findIdByName(String name) throws SQLException;

    public void create(List<Course> courses) throws SQLException;

}

package foxminded.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import foxminded.domain.Course;

public class CourseDao extends AbstractDao<Course> implements CourseModel {

    @Override
    public int findIdByName(String name) throws SQLException {

        String sql = String.format("SELECT course_id FROM school.courses where course_name = '%s'",
                name);
        return getEntityId(sql, "course_id");
    }

    @Override
    public void create(List<Course> courses) throws SQLException {

        for (Course course : courses) {
            String sql = String.format(
                    "INSERT INTO school.courses (course_id, course_name, course_description) VALUES (%d, '%s', '%s')",
                    course.getId(), course.getName(), course.getDescription());

            updateEntity(sql);
        }
    }

    @Override
    protected Course mapRow(ResultSet resultSet) throws SQLException {
        return new Course(0, null, null);
    }
}

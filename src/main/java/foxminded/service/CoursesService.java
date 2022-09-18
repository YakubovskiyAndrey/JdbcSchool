package foxminded.service;

import java.sql.SQLException;
import java.util.List;
import foxminded.dao.CourseDao;
import foxminded.dao.CourseModel;
import foxminded.domain.Course;

public class CoursesService {

    CourseModel modelLayer = new CourseDao();

    public int findIdByName(String name) throws SQLException {

        return modelLayer.findIdByName(name);

    }

    public void create(List<Course> courses) throws SQLException {

        modelLayer.create(courses);

    }
}

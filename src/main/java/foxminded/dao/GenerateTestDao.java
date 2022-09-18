package foxminded.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import foxminded.FileReader;
import foxminded.SchoolDataFormation;
import foxminded.domain.Course;
import foxminded.domain.Group;
import foxminded.domain.Student;
import foxminded.service.CoursesService;
import foxminded.service.GroupsService;
import foxminded.service.StudentsService;

public class GenerateTestDao {

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    public void generatedTables(String pathToSql) throws IOException, SQLException {

        FileReader reader = new FileReader();
        PreparedStatement pstmt = null;
        List<String> sqlList = reader.readFile(getAbsolutPathToSQLScript(pathToSql));

        for (String sqlLine : sqlList) {
            try (Connection connection = connectionManager.getConnection()) {
                pstmt = connection.prepareStatement(sqlLine, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException();
            } finally {
                connectionManager.closeStatement(pstmt);
            }
        }

        SchoolDataFormation schoolDataFormation = new SchoolDataFormation();
        List<Group> groups = schoolDataFormation.formGroups(10);
        List<Course> courses = schoolDataFormation.formCourses();
        List<Student> students = schoolDataFormation.formStudents(groups, courses);

        GroupsService groupsService = new GroupsService();
        groupsService.createGroupInDatabase(groups);
        CoursesService coursesService = new CoursesService();
        coursesService.create(courses);
        StudentsService studentsService = new StudentsService();

        for (Student student : students) {
            studentsService.createStudentInDatabase(student.getId(), student.getGroupId(),
                    student.getFirstName(), student.getLastName());
            for (Course course : student.getCourses()) {
                studentsService.addStudentToCourseById(student.getId(), course.getId());
            }
        }

    }

    private String getAbsolutPathToSQLScript(String pathToSql) {
        String absolutPath = this.getClass().getClassLoader().getResource(pathToSql).getPath();
        char[] chArray = absolutPath.toCharArray();
        chArray[0] = ' ';
        return new String(chArray).trim();
    }

}

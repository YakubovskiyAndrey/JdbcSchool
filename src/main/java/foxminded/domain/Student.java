package foxminded.domain;

import java.util.List;

public class Student {

    private int id;
    private int groupId;
    private String firstName;
    private String lastName;
    private List<Course> courses;

    public Student(int id, int groupId, String firstName, String lastName, List<Course> courses) {
        super();
        this.id = id;
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}

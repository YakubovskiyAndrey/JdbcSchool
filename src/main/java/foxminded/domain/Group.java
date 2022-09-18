package foxminded.domain;

public class Group {

    private int id;
    private int countStudents;
    private String name;

    public Group(int id, String name, int countStudents) {
        super();
        this.id = id;
        this.name = name;
        this.countStudents = countStudents;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountStudents() {
        return countStudents;
    }

    public void setCountStudents(int countStudents) {
        this.countStudents = countStudents;
    }

}

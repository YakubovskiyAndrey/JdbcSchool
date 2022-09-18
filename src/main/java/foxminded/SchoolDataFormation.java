package foxminded;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import foxminded.domain.Course;
import foxminded.domain.Group;
import foxminded.domain.Student;

public class SchoolDataFormation {

    private static final int COUNT_OF_STUDENTS = 200;
    private static final int MAX_STUDENTS_IN_GROUP = 30;
    private static final int MIN_STUDENTS_IN_GROUP = 10;
    private Random random = new Random();

    public List<Group> formGroups(int quantity) {

        List<Group> groups = new ArrayList<>();

        for (int index = 1; index <= quantity; index++) {

            String generatedStringLetter = random.ints(97, 122 + 1).limit(2)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

            String generatedStringDigits = random.ints(48, 57 + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(2)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

            groups.add(new Group(index, generatedStringLetter + "-" + generatedStringDigits, 0));
        }
        return groups;
    }

    public List<Course> formCourses() {

        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1, "Math", "Math"));
        courses.add(new Course(2, "Biology", "Biology"));
        courses.add(new Course(3, "History", "History"));
        courses.add(new Course(4, "Geography", "Geography"));
        courses.add(new Course(5, "Chemistry", "Chemistry"));
        courses.add(new Course(6, "Physics", "Physics"));
        courses.add(new Course(7, "Informatics", "Informatics"));
        courses.add(new Course(8, "Geometry", "Geometry"));
        courses.add(new Course(9, "Astronomy", "Astronomy"));
        courses.add(new Course(10, "English", "English"));

        return courses;
    }

    public List<Student> formStudents(List<Group> groups, List<Course> courses) {

        HashMap<Integer, Integer> usedGroups = new HashMap<>();

        List<Student> students = new ArrayList<>();

        List<String> firstNames = new ArrayList<>(Arrays.asList("Michael", "Richard", "Paul", "Thomas", "Lora",
                "Regina", "Hilary", "Melina", "Sarah", "Rosalyn", "Ernest", "Jeremy", "Domenic", "Jack", "Janice",
                "Lizbeth", "Amelia", "Merry", "Peter", "Terence"));
        List<String> lastNames = new ArrayList<>(Arrays.asList("Pierce", "Eaton", "Watkins", "Hill", "Price", "Newman",
                "Knight", "Long", "Glenn", "Boyd", "Carson", "Henderson", "Moody", "Sparks", "West", "Harper",
                "Beasley", "Curtis", "Clarke", "Simmons"));
        String firstName;
        String lastName;
        Group group = null;
        for (int i = 1; i <= COUNT_OF_STUDENTS; i++) {
            firstName = firstNames.get(random.nextInt(firstNames.size()));
            lastName = lastNames.get(random.nextInt(lastNames.size()));
            while (group == null
                    || usedGroups.get(group.getId()) != null && (usedGroups.get(group.getId()) >= MIN_STUDENTS_IN_GROUP
                            || usedGroups.get(group.getId()) == MAX_STUDENTS_IN_GROUP)) {

                group = groups.get(random.nextInt(groups.size()));
                if (usedGroups.containsKey(group.getId()) && usedGroups.get(group.getId()) >= MIN_STUDENTS_IN_GROUP
                        && usedGroups.get(group.getId()) < MAX_STUDENTS_IN_GROUP) {
                    break;
                }
            }
            usedGroups.put(group.getId(),
                    usedGroups.get(group.getId()) == null ? 1 : usedGroups.get(group.getId()) + 1);
            students.add(new Student(i, group.getId(), firstName, lastName, buildCourses(courses)));
            group.setCountStudents(group.getCountStudents() + 1);
        }

        return students;
    }

    private List<Course> buildCourses(List<Course> courses) {
        List<Course> returnList = new ArrayList<>(courses);
        int amount = random.nextInt(4);
        Collections.shuffle(returnList);
        returnList.subList(amount == 0 ? 1 : amount, courses.size()).clear();
        return returnList;
    }

}

package foxminded.ui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import foxminded.service.GroupsService;
import foxminded.service.StudentsService;

public class UserInterface {

    private StudentsService studentsService = new StudentsService();
    private GroupsService groupsService = new GroupsService();
    private View view = new ConsoleView();

    public void showMenu(ConsoleIO consoleIo) throws SQLException {

        view.printMenu();
        char userOption = consoleIo.userOptionChar();
        UserAction action = UserAction.fromOption(userOption);
        boolean closeMenu = false;
        switch (action) {
            case FIND_ALL_GROUPS:
                findAllGroups(consoleIo);
                break;
            case FIND_STUDENT_WITH_COURSE:
                findStudentWithCourse(consoleIo);
                break;
            case ADD_NEW_STUDENT:
                addNewStudent(consoleIo);
                break;
            case REMOVE_STUDENT:
                removeStudent(consoleIo);
                break;
            case ADD_STUDENT_TO_COURSE:
                addStudentToCourse(consoleIo);
                break;
            case REMOVE_STUDENT_FROM_COURSE:
                removeStudentFromCourse(consoleIo);
                break;
            case EXIT:
                sayGoodbye();
                closeMenu = true;
                break;
            default:
                break;
        }
        if (!closeMenu) {
            showMenu(new ConsoleIO());
        }
        consoleIo.scannerClose();
    }

    public void removeStudentFromCourse(ConsoleIO consoleIo) throws SQLException {
        view.printEnterStudentId();
        int studentId = consoleIo.nextInt();
        view.printEnterCourseName();
        String courseName = consoleIo.nextLine();
        studentsService.removeStudentFromCourse(studentId, courseName);
    }

    public void addStudentToCourse(ConsoleIO consoleIo) throws SQLException {
        view.printEnterStudentId();
        int studentId = consoleIo.nextInt();

        HashMap<Integer, String> courses = new HashMap<>();
        courses.put(1, "Math");
        courses.put(2, "Biology");
        courses.put(3, "History");
        courses.put(4, "Geography");
        courses.put(5, "Chemistry");
        courses.put(6, "Physics");
        courses.put(7, "Informatics");
        courses.put(8, "Geometry");
        courses.put(9, "Astronomy");
        courses.put(10, "English");

        view.printSelectCourse();
        for (Map.Entry<Integer, String> entry : courses.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        int userOption = consoleIo.nextInt();
        String courseName = courses.get(userOption);

        if (!courseName.isEmpty()) {
            studentsService.addStudentToCourse(studentId, courseName);
        } else {
            view.printCourseNameMustBeSpecified();
        }
    }

    public void removeStudent(ConsoleIO consoleIo) throws SQLException {
        view.printEnterStudentId();
        int studentId = consoleIo.nextInt();
        studentsService.deleteStudentById(studentId);
    }

    public void addNewStudent(ConsoleIO consoleIo) throws SQLException {
        view.printEnterGroupId();
        int groupID = consoleIo.nextInt();

        view.printFirstName();
        String firstName = consoleIo.nextLine();

        while (firstName.isEmpty()) {
            firstName = consoleIo.nextLine();
        }
        view.printLastName();
        String lastName = consoleIo.nextLine();
        view.printEnterCourseName();
        String courseName = consoleIo.nextLine();
        studentsService.createNewStudent(groupID, firstName.trim(), lastName.trim(),
                courseName.trim());
    }

    public void findStudentWithCourse(ConsoleIO consoleIo) throws SQLException {
        view.printEnterCourseName();
        String courseName = consoleIo.nextLine();
        studentsService.getStudentsWithCourse(courseName.trim());
    }

    public void findAllGroups(ConsoleIO consoleIo) throws SQLException {
        view.printNumberOfStudents();
        int userNumb = consoleIo.nextInt();
        groupsService.getGroupsWithStudent(userNumb);
    }

    public void sayGoodbye() {
        view.printGoodbye();
    }
}

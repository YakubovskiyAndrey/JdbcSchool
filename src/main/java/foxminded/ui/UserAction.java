package foxminded.ui;

public enum UserAction {

    FIND_ALL_GROUPS, FIND_STUDENT_WITH_COURSE, ADD_NEW_STUDENT, REMOVE_STUDENT, ADD_STUDENT_TO_COURSE,
    REMOVE_STUDENT_FROM_COURSE, EXIT;

    public static UserAction fromOption(char userOption) {
        switch (userOption) {
        case 'a':
            return FIND_ALL_GROUPS;
        case 'b':
            return FIND_STUDENT_WITH_COURSE;
        case 'c':
            return ADD_NEW_STUDENT;
        case 'd':
            return REMOVE_STUDENT;
        case 'e':
            return ADD_STUDENT_TO_COURSE;
        case 'f':
            return REMOVE_STUDENT_FROM_COURSE;
        case 'x':
            return EXIT;
        default:
            break;
        }

        return EXIT;

    }

}

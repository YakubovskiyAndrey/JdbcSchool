DROP TABLE IF EXISTS school.students_courses;
DROP TABLE IF EXISTS school.groups;
DROP TABLE IF EXISTS school.students;
DROP TABLE IF EXISTS school.courses;

--;--

CREATE TABLE school.groups
(
    group_id integer NOT NULL,
    count_students integer NOT NULL,
    group_name character(255) NOT NULL,
    CONSTRAINT "GROUP_key" PRIMARY KEY (group_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS school.groups
    OWNER to postgres;

--;--
    
    CREATE TABLE school.students
(
    student_id integer NOT NULL,
    group_id integer NOT NULL,
    first_name character(255) NOT NULL,
    last_name character(255) NOT NULL,
    CONSTRAINT "STUDENT_key" PRIMARY KEY (student_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS school.students
    OWNER to postgres;
    
--;-- 
    
    CREATE TABLE school.courses
(
    course_id integer NOT NULL,
    course_name character(255) NOT NULL,
    course_description character(255) NOT NULL,
    CONSTRAINT "COURSE_key" PRIMARY KEY (course_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS school.courses
    OWNER to postgres;
    
--;--
    
    CREATE TABLE IF NOT EXISTS school.students_courses
(
    student_id integer,
    course_id integer,
    CONSTRAINT students_courses_course_id_fkey FOREIGN KEY (course_id)
        REFERENCES school.courses (course_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT students_courses_student_id_fkey FOREIGN KEY (student_id)
        REFERENCES school.students (student_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS school.students_courses
    OWNER to postgres;
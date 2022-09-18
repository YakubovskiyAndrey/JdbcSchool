package foxminded;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class FileReaderTest {

    private FileReader reader;

    @BeforeEach
    void setUp() {
        reader = new FileReader();
    }

    private String getAbsolutPathToSQLScript(String path) {
        String absolutPath = this.getClass().getClassLoader().getResource(path).getPath();
        char[] chArray = absolutPath.toCharArray();
        chArray[0] = ' ';
        return new String(chArray).trim();
    }

    @Test
    void testReturnNonEmptyLine() throws IOException {
        List<String> list = reader.readFile(getAbsolutPathToSQLScript("createTables.sql"));
        assertEquals(
                "DROP TABLE IF EXISTS school.students_courses;DROP TABLE IF EXISTS school.groups;DROP TABLE IF EXISTS school.students;DROP TABLE IF EXISTS school.courses;",
                list.get(0));
    }

    @Test
    void testFileNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            reader.readFile("");
        });
    }

    @Test
    void testFileNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            reader.readFile(null);
        });
    }

    @Test
    void testFileIsNotExsist() {
        assertThrows(NoSuchFileException.class, () -> {
            reader.readFile("file");
        });

    }

    @Test
    void testFileIsEmpty() {
        String path = getAbsolutPathToSQLScript("emptyFile.sql");
        assertThrows(IllegalArgumentException.class, () -> reader.readFile(path));
    }

}

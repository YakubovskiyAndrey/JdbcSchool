package foxminded;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReader {

    public List<String> readFile(String fileName) throws IOException {

        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Empty filename!");
        }

        StringBuilder temp = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {

            temp = reader.lines().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
            if (temp.length() == 0) {
                throw new IllegalArgumentException("Empty file!");
            }

        } catch (NullPointerException e) {
            throw new NoSuchFileException(fileName);
        }

        List<String> sqlList = new ArrayList<>();
        String[] sqls = temp.toString().split("--;--");
        Collections.addAll(sqlList, sqls);

        return sqlList;

    }

}

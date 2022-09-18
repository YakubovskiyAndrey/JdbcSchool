package foxminded;

import java.io.IOException;
import java.sql.SQLException;
import foxminded.dao.GenerateTestDao;
import foxminded.ui.ConsoleIO;
import foxminded.ui.UserInterface;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        String pathToSql = "createTables.sql";
        GenerateTestDao generateTestDAO = new GenerateTestDao();
        generateTestDAO.generatedTables(pathToSql);
        UserInterface userInterface = new UserInterface();
        userInterface.showMenu(new ConsoleIO());

    }

}

package foxminded.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class ConnectionManager {

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String PASSWORD = "1111";
    private static ConnectionManager instance;
    private ComboPooledDataSource dataSource;

    private ConnectionManager() throws PropertyVetoException {

        dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(DRIVER_CLASS_NAME);
        dataSource.setJdbcUrl(URL);
        dataSource.setUser(DATABASE_USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public final synchronized Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final synchronized void closeStatement(PreparedStatement preparaStatement)
            throws SQLException {
        preparaStatement.close();
    }

}

package foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {

    protected ConnectionManager connectionManager = ConnectionManager.getInstance();

    protected abstract T mapRow(ResultSet resultSet) throws SQLException;

    public void updateEntity(String sqlInsert) throws SQLException {

        PreparedStatement preparaStatement = null;
        try (Connection connection = connectionManager.getConnection()) {

            preparaStatement = connection.prepareStatement(sqlInsert);
            preparaStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        } finally {
            connectionManager.closeStatement(preparaStatement);
        }

    }

    public List<T> loadEntity(String sql) throws SQLException {

        List<T> list = new ArrayList<>();
        PreparedStatement preparaStatement = null;

        try (Connection connection = connectionManager.getConnection()) {
            preparaStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparaStatement.executeQuery();
            while (resultSet.next()) {

                list.add(mapRow(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        } finally {
            connectionManager.closeStatement(preparaStatement);

        }

        return list;
    }

    public int getEntityId(String sql, String tableField) throws SQLException {

        PreparedStatement preparaStatement = null;
        try (Connection connection = connectionManager.getConnection()) {

            preparaStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparaStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(tableField);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            connectionManager.closeStatement(preparaStatement);
        }
        return -1;
    }

}

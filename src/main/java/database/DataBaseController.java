package database;

import lombok.NonNull;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.util.Optional;

public class DataBaseController {
    private static DataBaseController controller;
    private String DATABASE = System.getProperty("user.dir") + File.separator + "db" + File.separator + "empresa.sqlite";
    @NonNull
    private Connection connection;
    @NonNull
    private PreparedStatement preparedStatement;

    public DataBaseController() {
    }

    public static DataBaseController getInstance() {
        if (controller == null) {
            controller = new DataBaseController();
        }
        return controller;
    }

    public void open() throws SQLException {
        String url = "jdbc:sqlite:" + DATABASE;
        connection = DriverManager.getConnection(url);
    }

    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }

    private ResultSet executeQuery(@NonNull String querySQL, Object... params) throws SQLException {
        preparedStatement = connection.prepareStatement(querySQL);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

    public Optional<ResultSet> select(@NonNull String querySQL, Object... params) throws SQLException {
        return Optional.of(executeQuery(querySQL, params));
    }

    public Optional<ResultSet> select(@NonNull String querySQL, int limit, int offset, Object... params)
            throws SQLException {
        String query = querySQL + " LIMIT " + limit + " OFFSET " + offset;
        return Optional.of(executeQuery(query, params));
    }

    public Optional<ResultSet> insert(@NonNull String insertSQL, Object... params) throws SQLException {
        preparedStatement = connection.prepareStatement(insertSQL, preparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        preparedStatement.executeUpdate();
        return Optional.of(preparedStatement.getGeneratedKeys());
    }

    public int update(@NonNull String updateSQL, Object... params) throws SQLException {
        return updateQuery(updateSQL, params);
    }

    public int delete(@NonNull String deleteSQL, Object... params) throws SQLException {
        return updateQuery(deleteSQL, params);
    }

    private int updateQuery(@NonNull String genericSQL, Object... params) throws SQLException {
        preparedStatement = connection.prepareStatement(genericSQL);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeUpdate();
    }

    public void initData(@NonNull String sqlFile) throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(connection);
        sr.setEscapeProcessing(false); // Para SQLite
        Reader reader = new BufferedReader(new FileReader(sqlFile));
        sr.runScript(reader);
    }

}

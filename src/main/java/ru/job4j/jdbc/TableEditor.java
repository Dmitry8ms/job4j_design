package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void exeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s(%s, %s);",
                tableName,
                "id serial primary key",
                "name varchar(255)");
        exeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        exeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table if exists %s add column if not exists %s %s;",
                tableName,
                columnName,
                type
        );
        exeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table if exists %s drop column if exists %s;",
                tableName,
                columnName
        );
        exeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table if exists %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        exeStatement(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        String rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        String header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, tableName, null);
        if (rs.next()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet selection = statement.executeQuery(String.format(
                        "select * from %s limit 1", tableName
                ));
                ResultSetMetaData metaData = selection.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    buffer.add(String.format("%-15s|%-15s%n",
                            metaData.getColumnName(i), metaData.getColumnTypeName(i))
                    );
                }
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = LoadProps.getLoadedPropsFrom("app.properties");

        try (TableEditor te = new TableEditor(properties)) {
            String table = "demo_table";
            te.createTable(table);
            System.out.println(te.getTableScheme(table));
            te.addColumn(table, "interests", "text");
            System.out.println(te.getTableScheme(table));
            te.renameColumn(table, "interests", "hobby");
            System.out.println(te.getTableScheme(table));
            te.dropColumn(table, "hobby");
            System.out.println(te.getTableScheme(table));
            te.dropTable(table);
            System.out.println(te.getTableScheme(table));
        }

    }

}

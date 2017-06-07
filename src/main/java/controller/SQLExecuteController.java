package controller;

import Util.TxtReader;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLExecuteController {

    public void executeQuery(String[] args, Connection connection)
        throws IllegalArgumentException, SQLException {

        try {
            if (args.length == 0) {
                System.out.println("Connected to existing database!");

            } else if (args[0].equals("--migrate-db")) {
                executeUpdate(connection, "src/main/resources/migrate.txt");

            } else if (args[0].equals("--init-db")) {
                executeUpdate(connection, "src/main/resources/init.txt");

            } else if (args[0].equals("--init-test-db")) {
                executeUpdate(connection, "src/main/resources/test_db.txt");

            } else {
                throw new IllegalArgumentException("Invalid starting argument");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void executeUpdate(Connection conn, String filePath) throws SQLException, FileNotFoundException {
        Statement stmt;
        TxtReader reader = new TxtReader();
        String[] querries = reader.reader(filePath);

        for (String query : querries) {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }
    }
}
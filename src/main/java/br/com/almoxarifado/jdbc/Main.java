package br.com.almoxarifado.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try(Connection c = databaseConnection.connect()){
            boolean closed = c.isClosed();
            System.out.println("Connection is closed?:" + closed);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }
}

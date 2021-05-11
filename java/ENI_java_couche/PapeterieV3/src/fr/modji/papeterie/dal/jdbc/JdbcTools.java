package fr.modji.papeterie.dal.jdbc;

import fr.modji.papeterie.dal.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    private Connection connection;

    public Connection getConnection(){
        if (connection == null){
            // Chargement du driver
            try {
                Class.forName(Settings.getProperties("urlDriver"));
            }catch (ClassNotFoundException e){
                System.err.println(e.getMessage());
            }

            // Connection à la base de données
            try {
                connection = DriverManager.getConnection(
                        Settings.getProperties("url"),
                        Settings.getProperties("login"),
                        Settings.getProperties("password"));
            }catch (SQLException e){
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null){
            connection.close();
            connection = null;
        }
    }
}

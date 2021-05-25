package fr.modji.TP5_SuivisDesRepas.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Gere la connection à la base de données Mysql sur la table suivisrepas
 */
public class ConnectionProvider {
    private static DataSource ds;

    static{
        Context ctx;
        try {
            ctx = new InitialContext();
            ConnectionProvider.ds = (DataSource) ctx.lookup("java:comp/env/jdbc/pool_cnx");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return une instance de connexion à notre bdd Mysql
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ConnectionProvider.ds.getConnection();
    }
}

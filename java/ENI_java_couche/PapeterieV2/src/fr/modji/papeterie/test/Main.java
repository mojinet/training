package fr.modji.papeterie.test;

import java.sql.*;

public class Main {
    private static final String PARAM_URL = "jdbc:mysql://127.0.0.1/papeterie";
    private static final String PARAM_LOGIN = "root";
    private static final String PARAM_PASSWORD = "";
    private static final String PARAM_PILOTE_JDBC = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {

        // charger le pilote JDBC de MySQL
        try {
            Class.forName(PARAM_PILOTE_JDBC);
        }catch (ClassNotFoundException e){
            System.err.println(e.getMessage());
        }

        try {
            // connection a la base de données
            Connection connection = DriverManager.getConnection(PARAM_URL, PARAM_LOGIN,PARAM_PASSWORD);
            // créer une statement il est possible de le parametré pour gerer le type de deplacement et le mode d'ouverture
            Statement stmt = connection.createStatement();
            // requete qui retourne un enssemble de ligne : select
            ResultSet rs = stmt.executeQuery("SELECT * FROM `dbo.articles` ");
            // boucle sur la requete
            while(rs.next()){                                          // tant qu'il y as des ligne de retour
                String nom = rs.getString("nom");           // recupere la valeur de la colonne nom (String)
                int age = rs.getInt("age");                 // recupere la valeur de la colonne age (int)
                Date date = rs.getDate("dateNaissance");    // recupere une date
                boolean check = rs.wasNull();                          // recupere si null
            }
            // requete qui retourne un int représentant le nombre de ligne impacté (delete, update, add)
            int nBRows = stmt.executeUpdate("UPDATE FROM dbo.articles");
            // fermer la connexion a ne surtout pas oublier !
            connection.close();
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage()); // retourne les erreur sql
        }



        // requete qui retourne un enssemble de ligne : select
        //ResultSet rs = stmt.executeQuery("SELECT * from dbo.article");





    }
}

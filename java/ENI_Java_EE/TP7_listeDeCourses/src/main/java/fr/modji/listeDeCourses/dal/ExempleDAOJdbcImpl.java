package fr.modji.listeDeCourses.dal;

import fr.modji.listeDeCourses.bo.Exemple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe concrete d'implémentation Jdbc qui hérite de l'interface ExempleDAO
 * Elle est instancier via la DAOFactory qui elle meme est appeler depuis le manager associé à la classe exemple
 */
public class ExempleDAOJdbcImpl implements ExempleDAO{

    // Liste des requete SQL
    final String SELECT_ALL = "SELECT * FROM exemple";
    final String INSERT = "INSERT INTO exemple(nom) VALUES(?)";
    final String DELETE = "DELETE FROM exemple WHERE id=?";

    @Override
    public List<Exemple> selectAll() {
        Connection cnx;
        List<Exemple> exemples = new ArrayList<>();

        try{
            cnx = ConnectionProvider.getConnection();
            Statement stmt = cnx.createStatement();

            //Recupere les resultats et instancie des instance d'Exemple puis les ajoute dans la List exemples
            ResultSet rs = stmt.executeQuery(this.SELECT_ALL);
            while (rs.next()){
                Exemple exemple = new Exemple(rs.getString("nom"),rs.getInt("id"));
                exemples.add(exemple);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return exemples;
    }

    @Override
    public int insert(Exemple exemple) {
        Connection cnx;
        int id = -1;

        try{
            cnx = ConnectionProvider.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(this.INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,exemple.getName());
            stmt.executeUpdate();

            // recupere l'id générée
            ResultSet rs = stmt.getGeneratedKeys();
            while(rs.next()) {
                rs.getInt(1);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return id;
    }

    @Override
    public void delete(int id) {
        Connection cnx;

        try{
            cnx = ConnectionProvider.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(this.DELETE);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

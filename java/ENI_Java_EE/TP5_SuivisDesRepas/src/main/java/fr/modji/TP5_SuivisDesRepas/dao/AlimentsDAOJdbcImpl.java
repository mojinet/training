package fr.modji.TP5_SuivisDesRepas.dao;

import fr.modji.TP5_SuivisDesRepas.bo.Aliments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentsDAOJdbcImpl implements AlimentsDAO{

    final String INSERT = "INSERT INTO aliments(aliment, id_repas) VALUES(?,?)";
    final String SELECT = "SELECT * FROM aliments WHERE id_Repas =?";

    @Override
    public void insert(Aliments aliments) throws SQLException {
        Connection cnx = null;

        try{
            cnx = ConnectionProvider.getConnection();
            cnx = ConnectionProvider.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(this.INSERT);
            stmt.setString(1,aliments.getAliment());
            stmt.setInt(2,aliments.getIdRepas());
            stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (!cnx.isClosed()){
                cnx.close();
            }
        }

    }

    @Override
    public List<Aliments> selectAliments(int id) throws SQLException {
        Connection cnx = null;
        List<Aliments> aliments = new ArrayList<>();
        try{
            cnx = ConnectionProvider.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(this.SELECT);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                aliments.add(new Aliments(id,rs.getInt("id"),rs.getString("aliment")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (!cnx.isClosed()){
                cnx.close();
            }
        }

        return aliments;
    }
}

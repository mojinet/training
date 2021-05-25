package fr.modji.TP5_SuivisDesRepas.dao;

import fr.modji.TP5_SuivisDesRepas.bo.Aliments;
import fr.modji.TP5_SuivisDesRepas.bo.Repas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepasDAOJdbcImpl implements RepasDAO{

    final String INSERT = "INSERT INTO repas(date, heure) VALUES(?,?)";
    final String SELECT_ALL = "SELECT * FROM repas";

    @Override
    public int insert(Repas repas) throws SQLException {
        Connection cnx = null;
        int idRepas = 0;
        try{
            cnx = ConnectionProvider.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(this.INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, repas.getDate());
            stmt.setString(2,repas.getHeure());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            while(rs.next()){
                idRepas = rs.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (!cnx.isClosed()){
                cnx.close();
            }
        }

        return idRepas;
    }

    @Override
    public Repas select(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Repas> selectAll() throws SQLException {
        Connection cnx = null;
        List<Repas> listRepas = new ArrayList<>();

        try{
            cnx = ConnectionProvider.getConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(this.SELECT_ALL);
            while (rs.next()){
               Repas repas = new Repas(rs.getInt("id"),rs.getString("date"),rs.getString("heure"));
               listRepas.add(repas);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (!cnx.isClosed()){
                cnx.close();
            }
        }
        return listRepas;
    }
}

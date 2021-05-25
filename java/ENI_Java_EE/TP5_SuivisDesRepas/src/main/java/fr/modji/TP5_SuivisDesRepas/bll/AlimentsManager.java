package fr.modji.TP5_SuivisDesRepas.bll;

import fr.modji.TP5_SuivisDesRepas.bo.Aliments;
import fr.modji.TP5_SuivisDesRepas.dao.AlimentsDAO;
import fr.modji.TP5_SuivisDesRepas.dao.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class AlimentsManager {

    private AlimentsDAO alimentsDAO;

    public AlimentsManager(){
        this.alimentsDAO = DAOFactory.getAlimentsDAO();
    }

    public List<Aliments> getAliments(int id) throws SQLException {
        return alimentsDAO.selectAliments(id);
    }

    public void setAliments(int idRepas, String[] aliments) throws SQLException {
        for ( String aliment : aliments ) {
            alimentsDAO.insert(new Aliments(idRepas,aliment));
        }

    }
}

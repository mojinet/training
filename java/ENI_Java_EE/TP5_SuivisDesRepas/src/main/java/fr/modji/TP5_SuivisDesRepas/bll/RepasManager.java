package fr.modji.TP5_SuivisDesRepas.bll;

import fr.modji.TP5_SuivisDesRepas.bo.Aliments;
import fr.modji.TP5_SuivisDesRepas.bo.Repas;
import fr.modji.TP5_SuivisDesRepas.dao.AlimentsDAO;
import fr.modji.TP5_SuivisDesRepas.dao.DAOFactory;
import fr.modji.TP5_SuivisDesRepas.dao.RepasDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepasManager {

    private RepasDAO repasDAO;
    private AlimentsDAO alimentsDAO;

    public RepasManager(){
        this.repasDAO = DAOFactory.getRepasDAO();
        this.alimentsDAO = DAOFactory.getAlimentsDAO();
    }

    /**
     * Retourne la liste de tout les repas
     * @return une liste de tout les repas avec les aliments lié
     * @throws SQLException
     */
    public List<Repas> getRepas() throws SQLException {
        // on recupere la liste des repas
        List<Repas> listRepas = repasDAO.selectAll();

        // pour chaque repas on ajoute la liste des aliments lié
        for (Repas repas: listRepas ) {
            repas.setAliments(alimentsDAO.selectAliments(repas.getId()));
        }

        // on retourne les repas avec aliments lié
        return listRepas;
    }

    public int insert(Repas repas) throws SQLException {
        return repasDAO.insert(repas);
    }

}

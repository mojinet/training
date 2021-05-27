package fr.modji.listeDeCourses.bll;

import fr.modji.listeDeCourses.bo.Exemple;
import fr.modji.listeDeCourses.dal.DAOFactory;
import fr.modji.listeDeCourses.dal.ExempleDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Manager de la classe Exemple, permet de récupérer la liste des Exemple ainsi que d'inserer un exemple
 * Fait la liaison entre les BO, la DAL et l'IHM
 */
public class ExempleManager {

    private ExempleDAO exempleDAO;

    public ExempleManager(){
        this.exempleDAO = DAOFactory.getExempleDAO();
    }

    public List<Exemple> selectAll() throws SQLException {
        return exempleDAO.selectAll();
    }

    public void insert (Exemple exemple) throws SQLException {
        exempleDAO.insert(exemple);
    }

    public void delete (int id) throws SQLException {
        exempleDAO.delete(id);
    }

}

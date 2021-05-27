package fr.modji.listeDeCourses.dal;

import fr.modji.listeDeCourses.bo.Exemple;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface permettant de définir les méthodes disponible dans les implémentations
 */
public interface ExempleDAO {

    /**
     * @return la liste des exemple sous forme de list d'instance
     * @throws SQLException
     */
    List<Exemple> selectAll() throws SQLException;

    /**
     * Insert un exemple dans la base de données
     * @return l'id généré en base de données
     * @param exemple une instance d'exemple
     * @throws SQLException
     */
    int insert(Exemple exemple) throws SQLException;


    /**
     * Suprime un exemple dans la base de données
     * @param id de l'element à suprimer
     * @throws SQLException
     */
    void delete(int id) throws SQLException;
}

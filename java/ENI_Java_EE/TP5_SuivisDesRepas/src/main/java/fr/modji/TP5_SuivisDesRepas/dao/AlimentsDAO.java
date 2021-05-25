package fr.modji.TP5_SuivisDesRepas.dao;

import fr.modji.TP5_SuivisDesRepas.bo.Aliments;

import java.sql.SQLException;
import java.util.List;

public interface AlimentsDAO {

    /**
     * Insere une listes d'aliments dans la base de données
     * @param aliments qui est une instances d'aliments
     */
    void insert(Aliments aliments) throws SQLException;


    /**
     * @param id donne en parametre l'id du repas afin de retourner tout les aliments lié
     * @return une liste d'aliments
     */
    List<Aliments> selectAliments(int id) throws SQLException;

}

package fr.modji.TP5_SuivisDesRepas.dao;

import fr.modji.TP5_SuivisDesRepas.bo.Repas;

import java.sql.SQLException;
import java.util.List;

public interface RepasDAO {

    /**
     * Insere un repas dans la bdd
     * @param repas instance du repas
     * @return int qui est l'id généré
     */
    int insert(Repas repas) throws SQLException;

    /**
     * @param id qui représente l'id du repas recherché
     * @return une instance de Repas
     */
    Repas select(int id) throws SQLException;

    /**
     * @return la liste de tout les repas
     */
    List<Repas> selectAll() throws SQLException;
}

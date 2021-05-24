package fr.modji.TP5_SuivisDesRepas.dao;

import fr.modji.TP5_SuivisDesRepas.bll.bo.Aliments;

public interface AlimentsDAO {

    /**
     * Insere une listes d'aliments dans la base de données
     * @param aliments qui est une instances d'aliments
     */
    void insert(Aliments aliments);


    /**
     * @param id
     * @return
     */
    String[] select(int id);
}

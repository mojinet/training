package fr.modji.TemplateEE.dal;

/**
 * DAOFactory permet de retourner de maniere static la classe concrete d'implémentation désirer de l'acces au données
 */
public class DAOFactory {

    public static ExempleDAO getExempleDAO(){
        return new ExempleDAOJdbcImpl();
    }
}

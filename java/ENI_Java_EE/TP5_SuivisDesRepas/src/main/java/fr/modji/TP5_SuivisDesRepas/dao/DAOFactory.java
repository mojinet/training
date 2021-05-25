package fr.modji.TP5_SuivisDesRepas.dao;

public class DAOFactory {
    /**
     * @return l'implémentation jdbc de RepasDAO
     */
    public static RepasDAO getRepasDAO(){
        return new RepasDAOJdbcImpl();
    }

    /**
     * @return l'implémentation jdbc de AlimentsDAO
     */
    public static AlimentsDAO getAlimentsDAO() {
        return new AlimentsDAOJdbcImpl();
    }
}

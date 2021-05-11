package fr.modji.papeterie.dal;


import fr.modji.papeterie.dal.jdbc.ArticleDAOJdbcImpl;

public class DAOFactory {
    public static ArticleDAO getArticleDAO(){
        return new ArticleDAOJdbcImpl();
    }
}

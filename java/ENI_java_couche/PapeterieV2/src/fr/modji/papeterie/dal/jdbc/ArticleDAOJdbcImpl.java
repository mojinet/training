package fr.modji.papeterie.dal.jdbc;

import fr.modji.papeterie.bo.Article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ArticleDAOJdbcImpl {
    private final String PARAM_PILOTE_JDBC = "com.mysql.jdbc.Driver";
    private final String PARAM_URL = "jdbc:mysql://localhost/papeterie?";
    private final String PARAM_LOGIN = "root";
    private final String PARAM_PASSWORD = "";

    private Connection connection;

    public ArticleDAOJdbcImpl(){

        // Chargement du driver
        try {
            Class.forName(PARAM_PILOTE_JDBC);
        }catch (ClassNotFoundException e){
            System.err.println(e.getMessage());
        }

        // Connection à la base de données
        try {
            this.connection = DriverManager.getConnection(PARAM_URL,PARAM_LOGIN,PARAM_PASSWORD);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retourne l'article selectionner
     * @param id qui représente l'id pour acceder à notre article
     * @return une instance d'Article
     */
    public Article selectById(int id){
        return null;
    }

    public List<Article> selectAll(){
        return null;
    }

    public int update(Article article){
        return 0;
    }

    public int insert(Article article){
        String sql "INSERT INTO 'dbo.articles"
        return 0;
    }

    public int delete(int id){
        return 0;
    }

}

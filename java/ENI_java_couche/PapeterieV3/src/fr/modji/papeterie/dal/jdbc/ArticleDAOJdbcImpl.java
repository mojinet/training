package fr.modji.papeterie.dal.jdbc;

import fr.modji.papeterie.bo.Article;
import fr.modji.papeterie.bo.Ramette;
import fr.modji.papeterie.bo.Stylo;
import fr.modji.papeterie.dal.ArticleDAO;
import fr.modji.papeterie.dal.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements ArticleDAO {
    // TODO implémenter la connection de JdbcTools
    private Connection connection = new JdbcTools();

    public ArticleDAOJdbcImpl(){



    /**
     * Retourne l'article selectionner
     * @param id qui représente l'id pour acceder à notre article
     * @return une instance d'Article
     */
    public Article selectById(int id){
        String sql = "" +
                "SELECT * " +
                "FROM `dbo.articles` " +
                "WHERE `idArticle` = ?";
        Article article = null;

        try{
            // on prepare le statement
            PreparedStatement stmt = connection.prepareStatement(sql);

            // on bind les valeur
            stmt.setInt(1, id);

            // on recupere le resultat
            ResultSet rs = stmt.executeQuery();

            // on parcours le resultat
            while(rs.next()){
                int idArticle = rs.getInt("idArticle");
                String marque = rs.getString("marque");
                String reference = rs.getString("reference");
                String designation = rs.getString("designation");
                float pu = rs.getFloat("prixUnitaire");
                int qte = rs.getInt("qteStock");
                String couleur = rs.getString("couleur");
                int grammage = rs.getInt("grammage");
                String type = rs.getString("type");

                // et on hydrate notre objet
                if (type.equals(Stylo.TYPE)){
                    article = new Stylo(idArticle, marque, reference,designation,pu,qte,couleur);
                } else if (type.equals(Ramette.TYPE)){
                    article = new Ramette(idArticle, marque, reference,designation,pu,qte,grammage);
                }
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
        }
        return article;
    }

    /**
     * @return la liste de tout les articles
     */
    public List<Article> selectAll(){
        String sql = "SELECT * FROM `dbo.articles`";
        List<Article> listArticles = new ArrayList<>();

        try{
            // on execute directement la requete
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int idArticle = rs.getInt("idArticle");
                String marque = rs.getString("marque");
                String reference = rs.getString("reference");
                String designation = rs.getString("designation");
                float pu = rs.getFloat("prixUnitaire");
                int qte = rs.getInt("qteStock");
                String couleur = rs.getString("couleur");
                int grammage = rs.getInt("grammage");
                String type = rs.getString("type");

                // et on hydrate notre objet
                if (type.equals(Stylo.TYPE)){
                    listArticles.add(new Stylo(idArticle, marque, reference,designation,pu,qte,couleur));
                } else if (type.equals(Ramette.TYPE)){
                    listArticles.add(new Ramette(idArticle, marque, reference,designation,pu,qte,grammage));
                }
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return listArticles;
    }

    /**
     * Met à jour un article de type Stylo ou Ramette
     * @param article qui represente l'instance de l'article a mettre à jour
     * @return le nombre de ligne update
     */
    public int update(Article article){
        String sql = "" +
                "UPDATE `dbo.articles` " +
                "SET marque =?, reference =?, designation =?, prixUnitaire =?, qteStock =?, couleur =?, grammage =?" +
                "WHERE idArticle =?";
        int nbLigne = 0;

        try{
            // requete preparer
            PreparedStatement stmt = connection.prepareStatement(sql);

            // On bind les valeurs
            stmt.setString(1,article.getMarque());
            stmt.setString(2,article.getReference());
            stmt.setString(3,article.getDesignation());
            stmt.setFloat(4,article.getPrixUnitaire());
            stmt.setInt(5,article.getQteStock());

            // suivant le type d'article
            if (article instanceof Stylo){
                stmt.setString(6,((Stylo) article).getCouleur());
                stmt.setString(7,null);
                stmt.setInt(8, article.getIdArticle());
            } else if (article instanceof Ramette){
                stmt.setString(6,null);
                stmt.setInt(7,((Ramette) article).getGrammage());
                stmt.setInt(8, article.getIdArticle());
            }
            // on execute la requete
            nbLigne = stmt.executeUpdate();

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return nbLigne;
    }


    /**
     * Insere un objet de type article dans la base de données, si sera enregistrer sois en tant que Ramette ou Stylo
     * @param article qui représente l'instance à ajouter dans la base de données
     * @return le nombre de ligne inserer
     */
    public int insert(Article article) {
        String sql = "" +
                "INSERT INTO `dbo.articles`(marque, reference, designation, prixUnitaire, qteStock, couleur, grammage, type) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        int nbLigne = 0;

        try{
            // On initialise notre Statement
            PreparedStatement stmt = connection.prepareStatement(sql);

            // On bind les valeurs
            stmt.setString(1,article.getMarque());
            stmt.setString(2,article.getReference());
            stmt.setString(3,article.getDesignation());
            stmt.setFloat(4,article.getPrixUnitaire());
            stmt.setInt(5,article.getQteStock());

            // suivant le type d'article
            if (article instanceof Stylo){
                stmt.setString(6,((Stylo) article).getCouleur());
                stmt.setString(7,null);
                stmt.setString(8, Stylo.TYPE);
            } else if (article instanceof Ramette){
                stmt.setString(6,null);
                stmt.setInt(7,((Ramette) article).getGrammage());
                stmt.setString(8, Ramette.TYPE);
            }
            // retourne le nombre de ligne impacté
            nbLigne = stmt.executeUpdate();

            // on ferme le flux
            stmt.close();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return nbLigne;
    }

    /**
     * @param id qui reprente l'id de l'article a supprimer
     * @return le nombre de ligne suprimmer
     */
    public int delete(int id){
        String sql = "" +
                "DELETE FROM `dbo.articles` " +
                "WHERE idArticle =?";
        int nbLigne = 0;
        try{
            // requete preparer
            PreparedStatement stmt = connection.prepareStatement(sql);
            // bind
            stmt.setInt(1,id);
            // execute et recupere le nombre de ligne supprimer
            nbLigne = stmt.executeUpdate();

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return nbLigne;
    }

}

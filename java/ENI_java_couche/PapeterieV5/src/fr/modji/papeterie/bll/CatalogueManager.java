package fr.modji.papeterie.bll;

import fr.modji.papeterie.bo.Article;
import fr.modji.papeterie.bo.Ramette;
import fr.modji.papeterie.bo.Stylo;
import fr.modji.papeterie.dal.ArticleDAO;
import fr.modji.papeterie.dal.DAOFactory;

import java.util.List;


public class CatalogueManager {
    private static CatalogueManager instance;
    private final ArticleDAO articleDAO;

    public CatalogueManager(){
            articleDAO = DAOFactory.getArticleDAO();
    }
    
    public static CatalogueManager getInstance(){
        if (instance == null){
            instance = new CatalogueManager();
        }
        return instance;
    }

    /**
     * Ajoute un article dans le catalogue
     * @param art qui représente l'article à ajouter au catalogue
     * @throws BLLException si un des élements est null
     */
    public void addArticle(Article art) throws BLLException {
        if (checkArticle(art)){
            articleDAO.insert(art);
        }else{
            throw new BLLException();
        }
    }

    public String getCatalogue() {
        List<Article> articleList = articleDAO.selectAll();
        StringBuilder str = new StringBuilder();
        String rc = String.format("%n");
        for (Article article : articleList ) {
            str.append(article);
            str.append(rc);
        }
        return str.toString();
    }

    public void updateArticle(Article article) throws BLLException {
        if (checkArticle(article)){
            articleDAO.update(article);
        }else{
            throw new BLLException();
        }
    }

    public void removeArticle(Article article) throws BLLException {
        if (checkArticle(article)){
            articleDAO.delete(article.getIdArticle());
        }else{
            throw new BLLException();
        }
    }

    private boolean checkArticle(Article article){
        boolean check = false;

        if (article.getDesignation() != null && article.getMarque() != null && article.getPrixUnitaire() > 0 && article.getQteStock() > 0 && article.getReference() !=null){
            if (article instanceof Stylo){
                if (((Stylo) article).getCouleur() != null){
                    check = true;
                }
            }else if(article instanceof Ramette){
                if(((Ramette) article).getGrammage() > 0){
                    check = true;
                }
            }
        }
        return check;
    }
}

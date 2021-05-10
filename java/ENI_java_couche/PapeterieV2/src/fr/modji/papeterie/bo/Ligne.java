package fr.modji.papeterie.bo;

/**
 * La classe ligne représente une ligne d'un panier
 */
public class Ligne {
    private int qte;
    private Article article;

    public Ligne(Article article, int qte){
        this.article = article;
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Ligne : qte=" + qte +
                ", article=" + article +
                '}' + System.getProperty("line.separator");
    }

    //Getter & Setter
    public int getQte() {
        return qte;
    }
    public Article getArticle() {
        return article;
    }
    public void setQte(int qte) {
        this.qte = qte;
    }
    public void setArticle(Article article) {
        this.article = article;
    }
}

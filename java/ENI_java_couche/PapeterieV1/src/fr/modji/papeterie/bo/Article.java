package fr.modji.papeterie.bo;

public abstract class Article {
    protected int idArticle;
    protected String reference;
    protected String marque;
    protected String designation;
    protected float prixUnitaire;
    protected int qteStock;

    public Article (int idArticle, String marque, String ref, String designation, float pu, int qte){
        this.idArticle = idArticle;
        this.marque = marque;
        this.reference = ref;
        this.designation = designation;
        this.prixUnitaire = pu;
        this.qteStock = qte;
    }

    public Article(String marque, String ref,String designation, float pu, int qte){
        this.marque = marque;
        this.reference = ref;
        this.designation = designation;
        this.prixUnitaire = pu;
        this.qteStock = qte;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", reference='" + reference + '\'' +
                ", marque='" + marque + '\'' +
                ", designation='" + designation + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", qteStock=" + qteStock +
                '}';
    }

    //Getter & Setter
    public int getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public float getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public int getQteStock() {
        return qteStock;
    }
    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }
}

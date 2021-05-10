package fr.modji.papeterie.bo;

public class Stylo extends Article {
    private String couleur;
    public static String TYPE = "Stylo";

    public Stylo(int idArticle, String marque, String ref, String designation, float pu, int qte, String couleur) {
        super(idArticle, marque, ref, designation, pu, qte);
        this.couleur = couleur;
    }

    public Stylo(String marque, String ref, String designation, float pu, int qte, String couleur) {
        super(marque, ref, designation, pu, qte);
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Stylo{" +
                "idArticle=" + idArticle +
                ", reference='" + reference + '\'' +
                ", marque='" + marque + '\'' +
                ", designation='" + designation + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", qteStock=" + qteStock +
                ", couleur='" + couleur + '\'' +
                '}';
    }

    // Getter & Setter
    public String getCouleur() {
        return couleur;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}

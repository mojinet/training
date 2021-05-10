package fr.modji.papeterie.bo;

public class Ramette extends Article{
    private int grammage;

    public Ramette(int idArticle, String marque, String ref, String designation, float pu, int qte, int grammage) {
        super(idArticle, marque, ref, designation, pu, qte);
        this.grammage = grammage;
    }

    public Ramette(String marque, String ref, String designation, float pu, int qte, int grammage) {
        super(marque, ref, designation, pu, qte);
        this.grammage = grammage;
    }

    @Override
    public String toString() {
        return "Ramette{" +
                "idArticle=" + idArticle +
                ", reference='" + reference + '\'' +
                ", marque='" + marque + '\'' +
                ", designation='" + designation + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", qteStock=" + qteStock +
                ", grammage=" + grammage +
                '}';
    }

    // Getter & Setter
    public int getGrammage() { return grammage; }
    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }
}

public abstract class Composant {

    protected final String NOM_PRODUIT;
    protected final String REF_PRODUIT;

    public String getNOM_PRODUIT() {
        return NOM_PRODUIT;
    }

    public String getREF_PRODUIT() {
        return REF_PRODUIT;
    }

    public Composant(String NOM_PRODUIT, String REF_PRODUIT) {
        this.NOM_PRODUIT = NOM_PRODUIT;
        this.REF_PRODUIT = REF_PRODUIT;
    }
}

public class SupportStockage extends Composant{
    private final int CAPACITE;
    private final String TYPE;

    public int getCAPACITE() {
        return CAPACITE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public SupportStockage(String nomProduit, String refProduit, int CAPACITE, String TYPE) {
        super(nomProduit, refProduit);
        this.CAPACITE = CAPACITE;
        this.TYPE = TYPE;
    }
}

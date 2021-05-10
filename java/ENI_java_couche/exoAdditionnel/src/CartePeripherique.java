public class CartePeripherique extends Composant{
    private final String TYPE;

    public String getTYPE() {
        return TYPE;
    }

    public CartePeripherique(String nomProduit, String refProduit, String TYPE) {
        super(nomProduit, refProduit);
        this.TYPE = TYPE;
    }
}

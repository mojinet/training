public class CarteMere extends Composant{
    private final int SLOT_RAM;
    private final String TYPE;

    public int getSLOT_RAM() {
        return SLOT_RAM;
    }

    public String getTYPE() {
        return TYPE;
    }

    public CarteMere(String nomProduit, String refProduit, int SLOT_RAM, String TYPE) {
        super(nomProduit, refProduit);
        this.SLOT_RAM = SLOT_RAM;
        this.TYPE = TYPE;
    }
}

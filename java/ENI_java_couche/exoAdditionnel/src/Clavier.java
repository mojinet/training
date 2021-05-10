public class Clavier extends Composant{
    private final boolean PAVE_NUMERIQUE;

    public boolean isPAVE_NUMERIQUE() {
        return PAVE_NUMERIQUE;
    }

    public Clavier(String nomProduit, String refProduit, boolean PAVE_NUMERIQUE) {
        super(nomProduit, refProduit);
        this.PAVE_NUMERIQUE = PAVE_NUMERIQUE;
    }
}

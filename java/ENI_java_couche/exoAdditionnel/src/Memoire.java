public class Memoire extends Composant{


    private final int FREQUENCE;
    private final int TAILLE_MEMOIRE;

    public int getFREQUENCE() {
        return FREQUENCE;
    }

    public int getTAILLE_MEMOIRE() {
        return TAILLE_MEMOIRE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public Memoire(String nomProduit, String refProduit, int FREQUENCE, int TAILLE_MEMOIRE, String TYPE) {
        super(nomProduit, refProduit);
        this.FREQUENCE = FREQUENCE;
        this.TAILLE_MEMOIRE = TAILLE_MEMOIRE;
        this.TYPE = TYPE;
    }

    private final String TYPE;
}

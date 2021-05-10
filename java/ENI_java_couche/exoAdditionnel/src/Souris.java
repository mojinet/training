public class Souris extends Composant{


    private final int NB_BOUTON;
    private final int DPI;

    public int getNB_BOUTON() {
        return NB_BOUTON;
    }

    public int getDPI() {
        return DPI;
    }

    public Souris(String nomProduit, String refProduit, int NB_BOUTON, int DPI) {
        super(nomProduit, refProduit);
        this.NB_BOUTON = NB_BOUTON;
        this.DPI = DPI;
    }
}

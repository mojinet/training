public class Ventilateur extends Composant{
    final  int VITESSE;
    final int VOLUME_DB;

    public int getVITESSE() {
        return VITESSE;
    }

    public int getVOLUME_DB() {
        return VOLUME_DB;
    }

    public Ventilateur(String nomProduit, String refProduit, int VITESSE, int VOLUME_DB) {
        super(nomProduit, refProduit);
        this.VITESSE = VITESSE;
        this.VOLUME_DB = VOLUME_DB;
    }
}

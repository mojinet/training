public class Ecran extends Composant{

    private final String DEFINITION;
    private final String TYPE_DALE;

    public Ecran(String nomProduit, String refProduit, String DEFINITION, String TYPE_DALE, String REPONSE_FREQUENCE) {
        super(nomProduit, refProduit);
        this.DEFINITION = DEFINITION;
        this.TYPE_DALE = TYPE_DALE;
        this.REPONSE_FREQUENCE = REPONSE_FREQUENCE;
    }

    private final String REPONSE_FREQUENCE;

    public String getDEFINITION() {
        return DEFINITION;
    }

    public String getTYPE_DALE() {
        return TYPE_DALE;
    }

    public String getREPONSE_FREQUENCE() {
        return REPONSE_FREQUENCE;
    }
}

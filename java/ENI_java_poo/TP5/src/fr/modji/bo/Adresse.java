package fr.modji.bo;

public class Adresse {

    // Atribute
    private String mentionComplementaire, complementNum, rue, ville;
    private int numRue, codePostal;

    // Constructor
    public Adresse(String mentionComplementaire, int numRue, String complementNum, String rue, int codePostal, String ville){
        this.mentionComplementaire = mentionComplementaire;
        this.numRue = numRue;
        this.complementNum = (complementNum == null) ? "" : complementNum;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }
    public Adresse(int numRue,String complementNum, String rue, int codePostal, String ville){
        this(null, numRue, complementNum, rue, codePostal,  ville);
    }

    // Getter
    public String getMentionComplementaire() { return mentionComplementaire; }
    public String getComplementNum() { return complementNum; }
    public String getRue() { return rue; }
    public String getVille() { return ville; }
    public int getNumRue() { return numRue; }
    public int getCodePostal() { return codePostal; }

    // Method
    public void afficher(){
        if (this.mentionComplementaire != null ){
            System.out.printf( "%s %n" , getMentionComplementaire() );
        }
        System.out.printf( "%d%s %s %n%d %s %n", getNumRue(), getComplementNum(), getRue(), getCodePostal(), getVille() );
    }
}


public class Maison {
    public static void main(String[]args){
        // Create maison 1
        Maison msn1 = new Maison("rue des fleurs","Nantes",44200);
        msn1.setSurface(70);
        msn1.setNbPorte(2);
        msn1.setNbFenetre(6);

        // Test
        msn1.informations();
        Maison.compter();

        // Create maison 2
        Maison msn2 = new Maison("Rue de la mer","Pornic",44230);
        msn2.setSurface(60);
        msn2.setNbPorte(2);
        msn2.setNbFenetre(6);

        // Test
        msn2.informations();
        Maison.compter();

        //update
        msn2.mettreEnVente();

        // Test
        msn2.informations();
    }

    // Attribute
    private String
        adresse,
        ville;
    private int
        codePostal,
        nbPorte,
        nbFenetre,
        surface;
    private boolean aVendre;

    private static int compteurMaison;

    // Constructor
    public Maison(String adresse, String ville, int codePostal){
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.aVendre = false;
        Maison.compteurMaison++;    // Incrément count(Maison)
    }

    // Setter
    public void setNbPorte(int nbPorte) { this.nbPorte = nbPorte; }
    public void setNbFenetre(int nbFenetre) { this.nbFenetre = nbFenetre; }
    public void setSurface(int surface) { this.surface = surface; }

    // Geter
    public String getAdresse() { return adresse; }
    public String getVille() { return ville; }
    public int getCodePostal() { return codePostal; }
    public int getNbPorte() { return nbPorte; }
    public int getNbFenetre() { return nbFenetre; }
    public int getSurface() { return surface; }
    public boolean getAVendre() { return aVendre; }

    //Method
    public void mettreEnVente(){
        this.aVendre = true;
    }
    public void informations(){
        String str = this.aVendre == false ? "n'est pas à vendre" : "est à vendre";
        System.out.printf("La maison de %dm2 située %s à %s %s.%n", this.getSurface(), this.getAdresse(), this.getVille(), str);
    }
    public static void compter(){
        System.out.println("Il y as eu " + Maison.compteurMaison + " maison(s) de créer");
    }
}

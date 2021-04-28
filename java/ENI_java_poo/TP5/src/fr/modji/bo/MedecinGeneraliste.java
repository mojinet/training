package fr.modji.bo;

public class MedecinGeneraliste extends Personne{

    // Attribute
    protected static int tarif = 25;
    protected Creneau[] creneau = new Creneau[15];
    protected int compteurCreneau = 0;

    // Constructor
    public MedecinGeneraliste(String nom, String prenom, String telephone, Adresse adresse) {
        super(nom,prenom,telephone,adresse);
    }
    public MedecinGeneraliste(String nom, String prenom, String telephone) {
        this(nom, prenom,telephone,null);
    }

    // Method
    public int getTarif() { return tarif; }
    public void setTarif(int tarif) { MedecinGeneraliste.tarif = tarif; }

    public void ajouterCreneau(Creneau creneau){
        if (this.compteurCreneau < this.creneau.length){
            this.creneau[this.compteurCreneau] = creneau;
            this.compteurCreneau++;
        }else{
            System.out.println("Impossible d'ajouter un creneau (15/15)");
        }
    }

    public void afficher(){

        // Affiche les informations du médecin
        System.out.printf( "%s %s %nTéléphone : %s %nTarif : %d € %n", getNom().toUpperCase(), getPrenom(), getNumeroDeTelephone(), this.getTarif());
        super.afficher();
        // Affiche ses creneaux horaires
        for (int i = 0; i < this.compteurCreneau; i++) {
            this.creneau[i].afficher();
            System.out.println();
        }
    }
}

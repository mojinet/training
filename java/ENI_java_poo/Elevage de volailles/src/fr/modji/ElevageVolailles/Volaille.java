package fr.modji.ElevageVolailles;

public class Volaille {
    // Atribute
    protected static double prixKg;
    protected static double poidsAbattage;
    protected double prixAbattage;
    protected int identite;
    protected double poids;

    // Constructor
    public Volaille(double poids, int numero){
        this.poids = poids;
        this.identite = numero;
    }

    // Method
    public boolean assezGrosse(){
        if (this.poids >= Volaille.poidsAbattage){
            return true;
        }else{
            return false;
        }
    }

    // return the price of volaille in function of price/Kg
    public double prix(){
        return this.poids * Volaille.prixKg;
    }


    // Redefine
    @Override
    public String toString(){
        String response = String.format("%s %d, poids : %.3fKg, prix : %.2f€", this.getName(), this.getIdentite(), this.getPoids(), this.prix());
        return response;
    }

    private String getName() {
        String str = this.getClass().toString();
        int index = str.lastIndexOf(".");
        str = str.substring(index+1);
        return str;
    }

    // Getter
    public double getPrixKg() {
        return prixKg;
    }
    public double getPrixAbattage() {
        return prixAbattage;
    }
    public double getPoids() {
        return poids;
    }
    public int getIdentite() {
        return identite;
    }
    // Setter
    public static void changerPrix(double prixKg) { Volaille.prixKg = prixKg; }
    public static void changerPoidsAbattage(double poids)  { Volaille.poidsAbattage = poids; }
    public void setPrixAbattage(double prixAbattage) {
        this.prixAbattage = prixAbattage;
    }
    public void setIdentite(int identite) {
        this.identite = identite;
    }
    public void setPoids(double poids) {
        this.poids = poids;
    }
}

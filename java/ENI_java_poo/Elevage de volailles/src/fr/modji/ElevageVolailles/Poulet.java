package fr.modji.ElevageVolailles;

public class Poulet extends Volaille{
    // Attribute
    protected static double prixKg;
    protected static double poidsAbattage;
    protected static double prixAbattage;
    // Constructor
    public Poulet(double poids, int numero){
        super(poids,numero);
    }
    //Method
    public boolean assezGrosse(){ return this.poids >= Poulet.poidsAbattage; }
    // return the price of volaille in function of price/Kg
    public double prix(){
        return this.poids * Poulet.prixKg;
    }
    // Setter
    public static void changerPrix(double prixKg) { Poulet.prixKg = prixKg; }
    public static void changerPoidsAbattage(double poids)  { Poulet.poidsAbattage = poids; }
    // Getter
    public static double getPrixKg() {
        return prixKg;
    }
}

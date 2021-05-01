package fr.modji.ElevageVolailles;

public class Canard extends Volaille{
    // Attribute
    protected static double prixKg;
    protected static double poidsAbattage;
    protected static double prixAbattage;
    // Constructor
    public Canard(double poids, int numero){
        super(poids,numero);
    }
    //Method
    public boolean assezGrosse(){ return this.poids >= Canard.poidsAbattage; }
    // return the price of volaille in function of price/Kg
    public double prix(){
        return this.poids * Canard.prixKg;
    }
    // Setter
    public static void changerPrix(double prixKg) { Canard.prixKg = prixKg; }
    public static void changerPoidsAbattage(double poids)  { Canard.poidsAbattage = poids; }
    // Getter
    public static double getPrixKg() {
        return prixKg;
    }
}

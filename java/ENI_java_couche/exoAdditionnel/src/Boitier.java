import java.util.ArrayList;

public class Boitier {


    private ArrayList<Memoire> memoire;
    private CarteMere cm;
    private ArrayList<CartePeripherique> peripherique;
    private ArrayList<Ventilateur> ventilateur;
    private Chassis chassis;
    private ArrayList<SupportStockage> supportStockages;

    public Boitier(ArrayList<Memoire> memoire, CarteMere cm, ArrayList<CartePeripherique> peripherique, ArrayList<Ventilateur> ventilateur, Chassis chassis, ArrayList<SupportStockage> supportStockages) {
        this.memoire = memoire;
        this.cm = cm;
        this.peripherique = peripherique;
        this.ventilateur = ventilateur;
        this.chassis = chassis;
        this.supportStockages = supportStockages;
    }

    public ArrayList<Memoire> getMemoire() {
        return memoire;
    }

    public void setMemoire(ArrayList<Memoire> memoire) {
        this.memoire = memoire;
    }

    public CarteMere getCm() {
        return cm;
    }

    public void setCm(CarteMere cm) {
        this.cm = cm;
    }

    public ArrayList<CartePeripherique> getPeripherique() {
        return peripherique;
    }

    public void setPeripherique(ArrayList<CartePeripherique> peripherique) {
        this.peripherique = peripherique;
    }

    public ArrayList<Ventilateur> getVentilateur() {
        return ventilateur;
    }

    public void setVentilateur(ArrayList<Ventilateur> ventilateur) {
        this.ventilateur = ventilateur;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public ArrayList<SupportStockage> getSupportStockages() {
        return supportStockages;
    }

    public void setSupportStockages(ArrayList<SupportStockage> supportStockages) {
        this.supportStockages = supportStockages;
    }

    public Boitier(ArrayList<Memoire> memoire, CarteMere cm, ArrayList<CartePeripherique> peripherique, Chassis chassis, ArrayList<SupportStockage> supportStockages) {
        this.memoire = memoire;
        this.cm = cm;
        this.peripherique = peripherique;
        this.chassis = chassis;
        this.supportStockages = supportStockages;
    }
}

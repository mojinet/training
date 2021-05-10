import java.util.ArrayList;

public class Ordinateur {

    public Ordinateur(ArrayList<Ecran> ecran, Boitier boitier, Clavier clavier) {
        this.ecran = ecran;
        this.boitier = boitier;
        this.clavier = clavier;
    }

    public Ordinateur(ArrayList<Ecran> ecran, Boitier boitier, Clavier clavier, Souris souris) {
        this.ecran = ecran;
        this.boitier = boitier;
        this.clavier = clavier;
        this.souris = souris;
    }

    public ArrayList<Ecran> getEcran() {
        return ecran;
    }

    public void setEcran(ArrayList<Ecran> ecran) {
        this.ecran = ecran;
    }

    public Boitier getBoitier() {
        return boitier;
    }

    public void setBoitier(Boitier boitier) {
        this.boitier = boitier;
    }

    public Clavier getClavier() {
        return clavier;
    }

    public void setClavier(Clavier clavier) {
        this.clavier = clavier;
    }

    public Souris getSouris() {
        return souris;
    }

    public void setSouris(Souris souris) {
        this.souris = souris;
    }

    private ArrayList<Ecran> ecran;
    private Boitier boitier;
    private Clavier clavier;
    private Souris souris;

}

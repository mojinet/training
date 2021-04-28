package fr.modji.location;

import java.time.LocalDate;

public class Velo extends Vehicule{
    private int nbVitesse;

    public Velo(String marque, String modele, LocalDate dateAchat, int nbVitesse){
        super(marque,modele,dateAchat);
        this.nbVitesse = nbVitesse;
    }

    public int getNbVitesse(){ return this.nbVitesse; }

    @Override
    public String toString() {
        return String.format("Velo %s %s (année %d) %d vitesses",
                this.getMarque(), this.getModele(), this.getDateAchat(), this.getNbVitesse());
    }
}

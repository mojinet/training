package fr.modji.location;

import java.time.LocalDate;

public abstract class Vehicule {
    protected String marque, modele;
    protected LocalDate dateAchat;

    //Constructor
    public Vehicule(String marque, String modele, LocalDate dateAchat){
        this.marque = marque;
        this.modele = modele;
        this.dateAchat = dateAchat;
    }

    // Method
    public String getTarif(Vehicule vehicule){
        String response;
        if (vehicule instanceof Velo){
            response = "4€90";
        }else if(vehicule instanceof Gyroroue){
            response = "18€90";
        }else if(vehicule instanceof Gyropode){
            response = "29€90";
        }else{
            response = "inconnue";
        }
        return response;
    }

    // abstract method
    public abstract String toString();

    // Getter & setter
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getDateAchat() {
        return dateAchat.getYear();
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }
}

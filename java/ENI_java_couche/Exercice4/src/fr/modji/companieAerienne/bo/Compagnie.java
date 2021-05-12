package fr.modji.companieAerienne.bo;

import java.util.ArrayList;
import java.util.List;

public class Compagnie {
    private String nom;
    private List<Vol> listeVol;
    private List<Aeroport> aeroList;

    public Compagnie(String nom) {
        this.nom = nom;
        this.listeVol = new ArrayList<>();
        this.aeroList = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Vol> getListeVol() {
        return listeVol;
    }

    public void addVol(Vol vol) {
        this.listeVol.add(vol);
    }

    public void addAeroport(Aeroport aero) {
        this.aeroList.add(aero);
    }

    public Aeroport getAeroport(int index){
        return aeroList.get(index);
    }

    public Vol getVol(int index){
        return listeVol.get(index);
    }

    public void stopReservation(Vol vol){
        for (Vol v: listeVol) {
            if (v.equals(vol)){
                vol.setReservationOuverte(false);
            }
        }
    }

    @Override
    public String toString() {
        return "Compagnie{" +
                "nom='" + nom + '\'' +
                ", listeVol=" + listeVol +
                ", aeroList=" + aeroList +
                '}';
    }
}

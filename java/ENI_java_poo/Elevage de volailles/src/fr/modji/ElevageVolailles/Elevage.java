package fr.modji.ElevageVolailles;

import java.util.ArrayList;

public class Elevage {

    // Atribute
    private ArrayList<Volaille> elevage = new ArrayList<>();

    // Method
    public void ajouter(Volaille volaille){
        this.elevage.add(volaille);
    }

    public void changerPoids(int numero, double poids){
        for (Volaille v: this.elevage ) {
            if (numero == v.getIdentite()){
                v.setPoids(poids);
            }
        }
    }

    // Redefine
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Volaille volaille: this.elevage) {
            str.append(volaille.toString());
            str.append(String.format("%n").toString());
        }
        return str.toString();
    }

    public double calculerPrixVolaillesAAbattre() {
        double somme = 0;
        for (Volaille volaille : this.elevage) {
            if (volaille.assezGrosse()){
                somme += volaille.prix();
            }
        }
        return somme;
    }

    public Volaille[] envoyerALAbattoir() {
        Volaille[] volaille = new Volaille[this.elevage.size()];
        ArrayList<Volaille> newArray = new ArrayList<>();
        int compteur = 0;
        for (Volaille v : this.elevage) {
            if (v.assezGrosse()) {
                volaille[compteur] = v;
                compteur++;
            }else{
                newArray.add(v);
            }
        }
        this.elevage = newArray;
        return volaille;
    }
}

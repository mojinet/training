package fr.modji.genericite;

import java.util.ArrayList;
import java.util.Collections;

public class Tirage {
    private ArrayList<Character> motSelectionner = new ArrayList<>();
    private ArrayList<Character> motMelanger = new ArrayList<>();
    private Dictionnaire dico;

    public Tirage(Dictionnaire dico){
        this.dico = dico;
        newTirage();
    }

    /**
     * Selectionne un mot au hasard dans le dictionnaire
     */
    private void newTirage() {
        int random = (int)(Math.random() * this.dico.getSize()) + 1;
        String mot = this.dico.getMot(random);

        // On ajoute un à un les Charactere dans notre collection
        for ( Character c : mot.toCharArray() ) {
            this.motSelectionner.add(c);
            this.motMelanger.add(c);
        }

        // on melange les lettres
        Collections.shuffle(motMelanger);
    }

    /**
     * Retourne le mot mélanger sous forme de String
     */
    public String getMot(boolean original){
        ArrayList<Character> mot = original ? this.motSelectionner : this.motMelanger;
        StringBuilder str = new StringBuilder();

        for (Character c : mot) {
            str.append(c);
        }
        return str.toString().toUpperCase();
    }
    public String getMot(){
        return this.getMot(false);
    }

    /**
     * Compare la réponse de l'utilisateur avec le dictionnaire
     */
    public boolean compare(String response){
        return response.equals(getMot(true));
    }

}

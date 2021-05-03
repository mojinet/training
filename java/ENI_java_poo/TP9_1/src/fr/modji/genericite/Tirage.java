package fr.modji.genericite;

import java.util.ArrayList;
import java.util.Collections;

public class Tirage {
    private String mot;
    private ArrayList<Character> motSelectionner;
    private ArrayList<Character> motMelanger;
    private Dictionnaire dico;

    public Tirage(Dictionnaire dico){
        this.dico = dico;
        newTirage();
    }

    /**
     * Selectionne un mot au hasard dans le dictionnaire
     */
    private void newTirage() {
        ArrayList<Character> motSelectionner = new ArrayList<>();
        ArrayList<Character> motMelanger = new ArrayList<>();

        int random = (int)(Math.random() * this.dico.getSize()) + 1;
        this.mot = this.dico.getMot(random);

        // On ajoute un à un les Charactere dans notre collection
        for ( Character c : mot.toCharArray() ) {
            motSelectionner.add(c);
            motMelanger.add(c);
        }
        // on melange les lettres
        Collections.shuffle(motMelanger);
    }

    /**
     * Retourne le mot mélanger sous forme de String
     */
    public void getMotMelanger(){
        StringBuilder str = new StringBuilder();
        for (Character c : this.motMelanger) {
            System.out.print(c);
        }
    }

    /**
     * Compare la réponse de l'utilisateur avec le dictionnaire
     */
    public boolean compare(String response){
        return true;
    }

}

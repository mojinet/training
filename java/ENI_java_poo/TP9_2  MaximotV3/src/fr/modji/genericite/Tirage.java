package fr.modji.genericite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

public class Tirage {

    private ArrayList<Character> motSelectionner = new ArrayList<>();
    private ArrayList<Character> motMelanger = new ArrayList<>();
    private Hashtable<Integer, ArrayList<String>> listeDeMot = new Hashtable<>();
    private Dictionnaire dico;

    public Tirage(Dictionnaire dico){
        this.dico = dico;
        newTirage();
        setListeDeMot();
    }

    /**
     * Remplis la table de hashage avec les combinaison possible de mot
     */
    private void setListeDeMot() {

        // On créer autant de tableau qu'il y as de caractere dans le mot
        for (int i = 0; i < getMot().length(); i++) {
            listeDeMot.put(i, new ArrayList<String>());
        }

        // Pour chaque mot dans le dico
        for (int i = 1; i <= dico.getSize()-1; i++) {
            // On vérifie si les lettre qu'il contient sont dans le mot selectionner
            char[] motTester = dico.getMot(i).toUpperCase().toCharArray();
            char[] mot = getMot(true).toCharArray();

            int count = 0;

            if (motTester.length <= mot.length){
                // pour chaque lettre du mot a tester
                for (int j = 0; j < motTester.length; j++) {
                    // pour chaque lettre du mot
                    for (int k = 0; k < mot.length; k++) {
                        // si une des lettre du mot a tester est dans le mot
                        if (motTester[j] == mot[k] && motTester[j] != ' ' && mot[k] != ' '){
                            // on retire la lettre du mot
                            motTester[j] = ' ';
                            mot[k] = ' ';
                            // on incrémente le compteur des qu'ont matche
                            count++;
                        }
                    }
                }

                // si le compteur est de la meme longeur que le mot tester toute les lettre match avec une de notre mot
                if (count == motTester.length){
                    motTester = dico.getMot(i).toUpperCase().toCharArray();
                    mot = getMot(true).toCharArray();

                    StringBuilder strMot = new StringBuilder();
                    StringBuilder strMotTester = new StringBuilder();

                    for (char c: motTester) { strMotTester.append(c); }
                    for (char c: mot) { strMot.append(c); }

                    // On ajoute le mot dans le tableau de la liste des mots qui donne des points
                    ArrayList<String> tab = listeDeMot.get(motTester.length);
                    if (tab != null){
                        tab.add(strMotTester.toString());
                    }
                }
            }
        }
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
    public int compare(String response){
        int score = 0;

        for (int i = 0; i < motSelectionner.size(); i++) {
            ArrayList<String> aL = listeDeMot.get(i);
            for (String motCompare: aL ) {
                if (response.equals(motCompare)){
                    score = i;
                }
            }
        }

        return score;
    }

    public void DisplayListeReponse() {
        listeDeMot.forEach(
                (k, v) -> System.out.println("Mot avec " + k + "lettres : " + v));
    }
}

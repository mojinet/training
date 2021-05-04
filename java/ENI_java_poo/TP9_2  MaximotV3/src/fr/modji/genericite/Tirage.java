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

    ArrayList<String> c1 = new ArrayList<>();
    ArrayList<String> c2 = new ArrayList<>();
    ArrayList<String> c3 = new ArrayList<>();
    ArrayList<String> c4 = new ArrayList<>();
    ArrayList<String> c5 = new ArrayList<>();
    ArrayList<String> c6 = new ArrayList<>();
    ArrayList<String> c7 = new ArrayList<>();
    ArrayList<String> c8 = new ArrayList<>();
    ArrayList<String> c9 = new ArrayList<>();
    ArrayList<String> c10 = new ArrayList<>();

    /**
     * Remplis la table de hashage avec les combinaison possible de mot
     */
    private void setListeDeMot() {

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
                        if (motTester[j] == mot[k]){
                            // on retire la lettre du mot
                            motTester[j] = ' ';
                            // on incrémente le compteur des qu'ont matche
                            count++;
                            if (count == motTester.length){
                                j = motTester.length;
                                k = mot.length;
                            }
                        }else if ((motTester[j] != mot[k]) && (j == motTester.length -1)){
                            count = 0;
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

                    switch (count) {
                        case 1 -> {
                            c1.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c1);
                        }
                        case 2 -> {
                            c2.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c2);
                        }
                        case 3 -> {
                            c3.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c3);
                        }
                        case 4 -> {
                            c4.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c4);
                        }
                        case 5 -> {
                            c5.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c5);
                        }
                        case 6 -> {
                            c6.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c6);
                        }
                        case 7 -> {
                            c7.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c7);
                        }
                        case 8 -> {
                            c8.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c8);
                        }
                        case 9 -> {
                            c9.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c9);
                        }
                        case 10 -> {
                            c10.add(dico.getMot(i).toUpperCase());
                            listeDeMot.put(count, c10);
                        }
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
    public boolean compare(String response){

        listeDeMot.forEach(
            (k,v) -> {
                System.out.println("Mot de " + k + " lettres : " + v);
            }
        );

        return response.equals(getMot(true));
    }

}

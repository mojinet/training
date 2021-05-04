package fr.modji.genericite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionnaire {
    private ArrayList<String> dico = new ArrayList<>();

    /**
     * Initialise la collection de mot du dictionnaire (présent dans l'url) dans l'attribut dico
     */
    public Dictionnaire(String url){
        try (FileInputStream file = new FileInputStream(url); Scanner s = new Scanner(file) ){
            while (s.hasNextLine()) {
                this.dico.add(s.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Lecture impossible ! ");
        }
    }

    /**
     * On vérifie que le mot passé en parametre existe dans le dictionnaire
     */
    public boolean estDansLeDico(String mot){
        boolean confirm = false;

        for (int i = 0; i < dico.size(); i++) {
            if (String.valueOf(mot).equals(dico.get(i))) {
                confirm = true;
            }
        }
        return confirm;
    }

    /**
     * Retourne la taille du tableau
     */
    public int getSize(){
        return this.dico.size();
    }

    /**
     * Retourne le mot à l'index passé en parametre
     */
    public String getMot(int index){
        return dico.get(index);
    }
}

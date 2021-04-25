package fr.modji.exercice.module1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MaximotV1 {

	public static String tirerMotAleatoirement() {
		// On créer un tableau dynamique pour stocké les mots du dico
		ArrayList<String> motDico = new ArrayList<String>();
		// On parcours le fichier dictionnaire et ajoute les mots a motDico
		try (FileInputStream file = new FileInputStream("./dictionnaire.txt"); Scanner s = new Scanner(file) ){
			while (s.hasNextLine()) {
				motDico.add(s.nextLine());
			}
		} catch (IOException e) {
			System.err.println("Lecture impossible ! ");
		}
		// On renvois un mot choisie au hasard
		return motDico.get( (int) (Math.random() * motDico.size()));
	}
	public static void main(String[] args) {
		
		// On pioche un mot aléatoirement dans un fichier texte
		String motChoisie = tirerMotAleatoirement();
	
		// On mélange les lettre du mot aléatoirement
		
		// le tirage est afficher au joueur
		
		// Le joueur saisie sa proposition
		
		// La proposition est vérifier
			// Uniquement les lettres tirées ont été utilises
			// mot présent dans le dictionnaire
		
		// Le mot tiré au sort est dévoilé au joueur

	}

}

package fr.modji.exercice.module7;

import java.util.Scanner;

public class TP_5_Palindromes {

	public static void main(String[] args) {
		String texte;
		String[] arrayOfTexte;
		String texteReverse;
		Scanner scan = new Scanner(System.in);
		
		// recupere la saisie
		System.out.println("Saisir une phrase : ");
		texte = scan.nextLine();
		
		// traitement
		System.out.println(texte.strip());
		arrayOfTexte =  texte.split(" ");
		for(String mot : arrayOfTexte) {
			System.out.println(mot);
		}
		
	}

}

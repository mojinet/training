package fr.modji.exercice.module7;

import java.util.Scanner;

public class TP_1_MelangeurDeMot {

	public static void main(String[] args) {
		// variable
		String texteOriginal, texteMelanger = "";
		String[] texteOriginalTableau;
		
		// recupere la phrase
		Scanner scan = new Scanner(System.in);
		System.out.println("Veuillez saisir un texte : ");
		texteOriginal = scan.nextLine();
		
		// On recupere les mots 1 à 1
		texteOriginalTableau = texteOriginal.split(" ");

		// On inverse quelque lettres
		for (String mot : texteOriginalTableau) {
			String motModifier = "" +  mot.charAt(0);
			
			// inverse l'ordre des lettres a partir de l'avant derniere jusqu'a la deuxieme
			for (int i =  mot.length()-2; i > 0; i--) {
				motModifier += mot.charAt(i);
			}
			
			motModifier += mot.charAt(mot.length()-1);
			texteMelanger += motModifier + " ";
		}
		
		System.out.println(texteMelanger);
		scan.close();
		
	}

}

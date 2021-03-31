package fr.modji.exercice.module7;

import java.util.Scanner;

public class TP_5_Palindromes {

	public static void main(String[] args) {
		String texte, texteSansEspace = "";
		String[] arrayOfTexte;
		String texteReverse = "";
		Scanner scan = new Scanner(System.in);
		
		// recupere la saisie
		System.out.println("Saisir une phrase : ");
		texte = scan.nextLine();
		
		// retire les espace du texte saisie
		texteSansEspace = suprimeEspace(texte);
		
		// retourne la phrase à l'envers
		texteReverse = reverse(texteSansEspace);

		// compare les deux chaine
		if (texteSansEspace.equals(texteReverse)) {
			System.out.println("C'est un palindrome !");
		}else {
			System.out.println("Ce n'est PAS un palindrome !");
		}
		
	}

	private static String reverse(String texte) {
		
		String tmp = "";
		
		for (int i = texte.length()-1 ; i >= 0 ; i--) {
			tmp += texte.charAt(i);
		}
		return tmp;
	}

	private static String suprimeEspace(String texte) {
		
		String[] arrayOfTexte = texte.split(" ");
		String texteSansEspace = "";
		
		for(String mot : arrayOfTexte) {
			for (int i = 0; i < mot.length() ; i++) {
				texteSansEspace += mot.charAt(i);
			}
		}
		
		return texteSansEspace;
	}

}

package fr.modji.exercice.module7;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TP_2_Cyptage {
	
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		// variable et constante
		final int CRYPTER = 1, DECRYPTER = 2, AFFICHER_TABLEAU = 3;
		String cle, texte;
		int choice;
		int[][] tabCorrespondance = new int[26][26];
		
		
		remplirTableau(tabCorrespondance);
		
		do {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 - Crypter");
			System.out.println("2 - Décrypter");
			System.out.println("3 - Afficher le tableau des correspondance");
			System.out.println("4 - Quitter");
			choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			case CRYPTER : 
				System.out.println(crypter(recupereCle(), recupereTexte(), tabCorrespondance));
				break;
			case DECRYPTER : 
				System.out.println(decrypter(recupereCle(), recupereTexte(), tabCorrespondance));
				break;
			case AFFICHER_TABLEAU : 
				affichertableau(tabCorrespondance);
				break;
			}
			
		} while (choice != 4) ;

		System.out.println("Fin du programme");
	}

	private static String recupereTexte() {

		System.out.println("Quel texte voulez vous crypter ?");
		return  scan.nextLine().toUpperCase();

	}

	private static String recupereCle() {
		System.out.println("Quel est votre clé de cryptage ?");
		return scan.nextLine().toUpperCase();
	}

	private static void affichertableau(int[][] tab) {
		for (int x = 0 ; x < 26; x++) {
			for (int y = 0 ; y < 26; y++) {
				System.out.print( (char) tab[x][y] + " ");
			}
			System.out.println();
		}
		
	}

	private static String decrypter(String cle, String texte, int[][] tab) {
		StringBuilder response = new StringBuilder();
		int j = 0;
		int firstValue = (int) 'A';
		
		// TODO
		//parcourir les valeur de tab[cle.chartAt(i)][?] pour qu'elle corresponde a la lettre en texte.charAt(j) 
		for (int i = 0; i < texte.length(); i++) {
			for (int k = 0; k < 26; k++) {
				if (tab[j][k] == texte.charAt(i)) {
					response.append( (char) tab
							[(int) texte.charAt(i) - firstValue]
							[(int) cle.charAt(k) - firstValue]
							 );
				}
			}
			if (j < cle.length()-1) {
				j++;
			}else {
				j = 0;
			}
		}
		
		return "votre texte crypter est : " + response.toString();
	}

	private static String crypter(String cle, String texte, int[][] tab) {
		StringBuilder response = new StringBuilder();
		int firstValue = (int) 'A';
		int j =0;
		
	for (int i = 0; i < texte.length(); i++) {
		response.append( (char) tab
				[(int) texte.charAt(i) - firstValue]
				[(int) cle.charAt(j) - firstValue]
				 );
		
		if (j < cle.length()-1) {
			j++;
		}else {
			j = 0;
		}
	}
		return "votre texte crypter est : " + response.toString();
	}

	private static void remplirTableau(int[][] tab) {
		int firstValue = (int) 'A';
		int lastValue = (int) 'Z';
		
		for (int x = 0 ; x < 26; x++) {
			for (int y = 0 ; y < 26; y++) {
				tab[x][y] = firstValue+x+y <= lastValue ? firstValue+x+y : ( firstValue+x+y ) - (lastValue - firstValue +1) ;
			}
		}

	}
}
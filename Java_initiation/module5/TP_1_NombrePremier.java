package fr.modji.exercice.module5;

import java.util.Scanner;

public class TP_1_NombrePremier {

	public static void main(String[] args) {
		int nombreBoucle = 1000;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Bienvenue au calcul des nombre premier !");
		System.out.println("Veuillez saisir la valeur maximal de votre calcul");
		nombreBoucle = scan.nextInt();
		
		// Pour chaque nombre....
		for (int i = 2; i < nombreBoucle; i++) {
			boolean estPremier = true;
			
			// on test la division de tout les nombres précedent i/2
			for (int j = 2; j <= (i/2); j++) {
				
				// si i est divisible par j, ce n'est pas un nombre premier
				if (i % j == 0) {
					estPremier = false;
				}
			}
			if (estPremier) {
				System.out.println(i + " est un nombre premier");
			}
		}

		scan.close();
	}

}

package fr.modji.exercice.module4;

import java.util.Scanner;

public class TP_2_BulletinSimple {

	public static void main(String[] args) {
		// variable & constante
		Scanner scan = new Scanner(System.in);
		int nbHeure, heureCalcul;
		float salaireCalcul = 0f;
		
		final float TAUX_HORAIRE = 10.20f;
		final float MAJORATION_180 = 1.6f;
		final float MAJORATION_169 = 1.5f;
		
		// save the user's data
		System.out.println("Votre nombre nombre d'heure ?");
		nbHeure = scan.nextInt();
		heureCalcul = nbHeure;
		
		// Calcule des heures au dela de 180
		if (nbHeure > 180) {
			heureCalcul -= 181;
			salaireCalcul = heureCalcul * (TAUX_HORAIRE * MAJORATION_180); 
			System.out.println(heureCalcul + " heure majoré à 60% = " + heureCalcul * (TAUX_HORAIRE * MAJORATION_180));
		}
		
		// Calcule des heures entre 169 et 180
		if (nbHeure >= 169) {
			if (nbHeure > 180) {
				heureCalcul = 181 - 169;
			}else {
				heureCalcul = nbHeure - 169;
			}
			salaireCalcul += heureCalcul * (TAUX_HORAIRE * MAJORATION_180); 
			System.out.println(heureCalcul + " heure majoré à 50% = " + heureCalcul * (TAUX_HORAIRE * MAJORATION_169));
		}
		
		//Calcule des heures normal
		if (nbHeure > 169) {
			heureCalcul = 169;
		}else {
			heureCalcul = nbHeure;
		}
		salaireCalcul += heureCalcul * TAUX_HORAIRE; 
		System.out.println(heureCalcul + " heure normal = " + heureCalcul * TAUX_HORAIRE);
		
		// resultat
		System.out.println("votre salaire est de " + salaireCalcul + "€");
		
	}

}

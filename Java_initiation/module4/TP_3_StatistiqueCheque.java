package fr.modji.exercice.module4;

import java.util.Scanner;

public class TP_3_StatistiqueCheque {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		float chequeEnCours = 0, cumulCheque = 0, superieurEgalA200Cumul = 0, inferieurA200Cumul =0, minValue = 99999999, maxValue = 0;
		int superieurEgalA200 = 0, inferieurA200 = 0, nbCheque = 0, minNum = 0, maxNum = 0, numCheque = 0 ;
		
		//Recupere les saisie tant que != 0 et créer des statistiques
		do {
			System.out.println("saisir le montant d'un cheque ou 0 pour sortir");
			chequeEnCours = scan.nextFloat();
			if (chequeEnCours != 0) {
				
				// nombre de cheque
				cumulCheque += chequeEnCours;
				numCheque++;
				
				// cheque >= 200
				if (chequeEnCours >= 200) {
					superieurEgalA200++;
					superieurEgalA200Cumul += chequeEnCours;
					
				// cheque < 200
				}else {
					inferieurA200++;
					inferieurA200Cumul += chequeEnCours;
				}
				
				// min
				if (chequeEnCours < minValue) {
					minValue = chequeEnCours;
					minNum = numCheque;
					
				// max
				}if(chequeEnCours > maxValue) {
					maxValue = chequeEnCours;
					maxNum = numCheque;					
				}
				
			}
		} while (chequeEnCours != 0);
		
		//Reponse
		System.out.println("Nombre de cheques Total : " + nbCheque);
		System.out.println("Montant total des cheques : " + cumulCheque + "€");
		System.out.println("Moyenne des montant : " + (cumulCheque / numCheque) + "€");
		System.out.println("Nombre de cheque inferieur à 200€ " + inferieurA200);
		System.out.println("Montant TOTAL des cheques inférieur à 200€ : " + inferieurA200Cumul);
		System.out.println("Nombre de cheques supérieur ou egal à 200€ : " + superieurEgalA200);
		System.out.println("Montant TOTAL des cheques supérieur ou egal à 200€ : " + superieurEgalA200Cumul);
		System.out.println("le plus petit cheque est le Numéro " + minNum + " pour une valeur de " + minValue);
		System.out.println("le plus grand cheque est le Numéro " + maxNum + " pour une valeur de " + maxValue);
	}

}

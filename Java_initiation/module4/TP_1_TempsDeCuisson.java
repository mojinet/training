package Java_initiation.module4;

import java.util.Scanner;

public class TP_1_TempsDeCuisson {

	public static void main(String[] args) {
		
		// variable & constante
		Scanner scan = new Scanner(System.in);
		int choixViande, choixCuisson, choixGramme;
		float reponse = 0.0f;
		final int BOEUF = 1;
		final int PORC = 2;
		final int BLEU = 1;
		final int A_POINT = 2;
		final int BIEN_CUIT = 3;
		
		final float BOEUF_BLEU = (10*60) / 500f ;
		final float BOEUF_A_POINT = (17*60) / 500f;
		final float BOEUF_BIEN_CUIT = (25*60) / 500f;
		final float PORC_BLEU = (15*60) / 400f;
		final float PORC_A_POINT = (25*60) / 400f;
		final float PORC_BIEN_CUIT = (40*60) / 400f;
		
		// Save the user's choices
		System.out.println("Choisir un type de viande : 1 - boeuf / 2 - porc");
		choixViande = scan.nextInt();
		System.out.println("Indiquer le poids en grammes");
		choixGramme = scan.nextInt();
		System.out.println("Choisir un type de cuisson : 1 - bleu / 2 - a point / 3 - bien cuit");
		choixCuisson = scan.nextInt();
		
		// Return time in seconde
		switch(choixViande){
			case BOEUF : 
				switch(choixCuisson) {
					case BLEU : 
						reponse = choixGramme * BOEUF_BLEU;
						break;
					case A_POINT : 
						reponse = choixGramme * BOEUF_A_POINT;
						break;
					case BIEN_CUIT : 
						reponse = choixGramme * BOEUF_BIEN_CUIT;
						break;
					default : System.out.println("Erreur lors de la saisie de la cuisson");
						}
					break;
					
			case PORC : 
				switch(choixCuisson) {
					case BLEU : 
						reponse = choixGramme * PORC_BLEU;
						break;
					case A_POINT : 
						reponse = choixGramme * PORC_A_POINT;
						break;
					case BIEN_CUIT : 
						reponse = choixGramme * PORC_BIEN_CUIT;
						break;
					default : System.out.println("Erreur lors de la saisie de la cuisson");			
				}
				break;
			default : System.out.println("Erreur lors de la saisie de la viande");
		}
		
		System.out.println("Votre temps de cuisson est de " + reponse + " secondes !");
	}

}

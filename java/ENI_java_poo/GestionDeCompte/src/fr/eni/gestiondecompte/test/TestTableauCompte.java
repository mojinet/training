package fr.eni.gestiondecompte.test;

import fr.eni.gestiondecompte.bo.Compte;

public class TestTableauCompte {

	public static void main(String[] args) {
		// create array
		Compte[] arrayOfCompte = new Compte[10];

		// create 10 instance
		for (int i = 0; i < 10; i++) {
			Compte cptTmp = new Compte();
			cptTmp.deposer( 200 + (100 * i) );
			arrayOfCompte[i] = cptTmp;
		}
		
		// Display information of array
		for (Compte cpt: arrayOfCompte) {
			System.out.println(cpt);
		}
	}

}

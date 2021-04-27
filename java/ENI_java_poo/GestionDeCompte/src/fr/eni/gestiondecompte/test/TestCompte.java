package fr.eni.gestiondecompte.test;

import fr.eni.gestiondecompte.bo.Compte;

public class TestCompte {

	public static void main(String[] args) {

		// Create compte
		Compte cpt1 = new Compte();
		Compte cpt2 = new Compte();

		// Opération
		cpt1.deposer(500);
		cpt2.deposer(1000);
		cpt2.retirer(10);
		cpt1.virerVers(75, cpt2);

		//Display
		System.out.println(cpt1);
		System.out.println(cpt2);
	}

}

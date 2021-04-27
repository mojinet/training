package fr.eni.gestiondecompte.bo;

public class Compte {
	private int solde = 0;

	public void deposer(int montant) {
		solde = solde + montant;
	}

	public void retirer(int montant) {
		solde = solde - montant;
	}

	public void virerVers(int montant, Compte destination) {
		this.retirer(montant);
		destination.deposer(montant);
	}

	public String toString() {
		return "solde: " + solde;
	}
}

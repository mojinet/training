package fr.modji.ElevageVolailles;

public class MainVolaille {

	public static void main(String[] args) {

		// Ajout de jeu de test manquant
		Canard.changerPrix(15);
		Canard.changerPoidsAbattage(0.260);
		Poulet.changerPrix(20);
		Poulet.changerPoidsAbattage(0);


		Elevage laFerme = new Elevage();
		//Ajout de 14 poulets de 250 grammes, identité allant de 150 à 164
		for (int i = 0; i < 15; i++) {
			laFerme.ajouter(new Poulet(0.250, 150 + i));
		}
		//Ajout de 14 canarards de 250 grammes, identité allant de 380 à 394
		for (int i = 0; i < 15; i++) {
			laFerme.ajouter(new Canard(0.250, 380 + i));
		}
		//Ajout de 9 poulets de 250 grammes, identité allant de 700 à 709
		for (int i = 0; i < 10; i++) {
			laFerme.ajouter(new Poulet(0.250, 700 + i));
		}
		//Ajout d'un canard de 750 grammes avec identité 825
		laFerme.ajouter(new Canard(0.750, 825));
		for (int i = 0; i < 8; i++) {
			//Modification du poids des volailles ayant leur identité allant de 155 à 162
			laFerme.changerPoids(155 + i, 1.3);
			//Modification du poids des volailles ayant leur identité allant de 382 à 389
			laFerme.changerPoids(382 + i, 1.55);
		}
		System.out.println("Volailles présentes dans la ferme avant abattoir");
		System.out.print(laFerme);

		System.out.printf("Valeur des volailles à abattre: %.2f€%n", laFerme.calculerPrixVolaillesAAbattre());
		System.out.println("Liste des volailles à abattre");
		Volaille[] tabVolailleAAbattre = laFerme.envoyerALAbattoir();
		for(Volaille v : tabVolailleAAbattre) {
			if(v!=null) {
				System.out.println(v);
			}
		}
		System.out.println("Volailles présentes dans la ferme après abattoir");
		System.out.print(laFerme);
		System.out.printf("Valeur des volailles à abattre: %.2f€%n", laFerme.calculerPrixVolaillesAAbattre());
		System.out.println("Modification du prix du kilo de canard et de poulet");
		Canard.changerPrix(24.5);
		Poulet.changerPrix(11.2);
		System.out.println("Volailles présentes dans la ferme après modification du prix du kilo de canard");
		System.out.print(laFerme.toString());

		System.out.println(Canard.prixKg + " " + Poulet.prixKg + " " + Volaille.prixKg);
	}

}

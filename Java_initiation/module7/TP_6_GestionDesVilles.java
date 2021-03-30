package fr.modji.exercice.module7;

public class TP_6_GestionDesVilles {
	
	public static void main(String[] args) {
		
		String[] tabVille = { "Lille", "Lens", "Amiens", "Caen", "Rennes", "Nantes", "Niort", "Bordeaux", "Bayonne" };
		
		System.out.println("********Affiche toutes les villes");
		afficheVille( tabVille);
		
		System.out.println("*******Affiche les villes qui commence par A");
		afficheVilleCommencePar('A', tabVille);
		
		System.out.println("*******Ajoute un nombre aléatoire au nom de la ville");
		ajouteNombreAleaVille( tabVille);

	}
	
	public static void afficheVille(String[]  tabVille) {
		for (String ville : tabVille) {
			System.out.println(ville);
		}
	}

	public static void afficheVilleCommencePar(char lettre, String[]  tabVille) {
		for (String ville : tabVille) {
			if (ville.charAt(0) == lettre) {
				System.out.println(ville);
			}
		}
		
	}
	
	public static void ajouteNombreAleaVille(String[]  tabVille) {
			int rand;
			for (String ville : tabVille) {
				rand = (int) (Math.random() * 10f);
				ville += rand;
				System.out.println(ville);
			}
	}

}

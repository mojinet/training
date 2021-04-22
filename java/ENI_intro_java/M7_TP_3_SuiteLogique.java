package fr.modji.exercice.module7;

public class TP_3_SuiteLogique {

	public static void main(String[] args) {
		String line = "1";
		final int PROFONDEUR = 40;
		long debut = System.currentTimeMillis();
		
		// Itération pour calculer le prochain nombre
		for (int i = 0; i < PROFONDEUR; i++) {
			System.out.println(line);
			line = calculeProchainNombre(line);
		}
		
		System.out.println(System.currentTimeMillis()-debut + " ms");

	}

	private static String calculeProchainNombre(String line) {
		int cumul = 0;
		char value;
		StringBuilder reponse = new StringBuilder("");
		
		
		for (int i = 0; i < line.length(); i++) {
			// initialise les valeurs
			value = line.charAt(i);
			cumul = 1;
			
			// tant qu'il existe une occurence en n+1 (qui ne depasse pas la longeur du tableau) avec la meme valeur on incrémente cumul
				while ( (i+1 <= line.length() -1) && (line.charAt(i) == line.charAt(i + 1) ) ) {
					cumul++;
					i++;
			}

			// on ajoute les valeurs à la réponse
			reponse.append(String.valueOf(cumul) + String.valueOf(value));
		}
		
		return reponse.toString();
	}
	
}

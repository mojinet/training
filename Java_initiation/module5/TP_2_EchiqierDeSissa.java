package fr.modji.exercice.module5;

public class TP_2_EchiqierDeSissa {

	public static void main(String[] args) {
		final int nbCase = 8*8;
		float cumulRiz = 1, rizSurCase = 1;
		
		// on commence a la 2em case
		for (int i = 2; i <= nbCase; i++) {
			cumulRiz += rizSurCase * 2;
			rizSurCase *= 2;
			System.out.println("La case " + i + " à " + rizSurCase + " grain de riz !");
		}
	}

}

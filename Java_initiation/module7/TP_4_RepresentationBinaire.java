package fr.modji.exercice.module7;

import java.util.Scanner;

public class TP_4_RepresentationBinaire {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("entrez un nombre a convertir en binaire (max 255)");
		int saisie = scan.nextInt();
		scan.close();
		
		System.out.println( convertToBinary(saisie));
	}

	private static String convertToBinary(int saisie) {
		int reste = saisie;
		int[] binaryNumber = {128,64,32,16,8,4,2,1};
		StringBuilder result = new StringBuilder("");
		
		for (int i = 0; i < binaryNumber.length; i++) {
			result = reste >= binaryNumber[i] ? result.append("1") : result.append("0");
			reste =  reste >= binaryNumber[i] ? reste - binaryNumber[i] : reste;
		}
		return result.toString();
	}
}
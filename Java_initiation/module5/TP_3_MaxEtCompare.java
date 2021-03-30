package fr.modji.exercice.module5;

public class TP_3_MaxEtCompare {

	public static void main(String[] args) {
		System.out.println(testValeur(4,5));

	}
	
	public static int testValeur (int a, int b) {
		if (a == b) {
			return 0;
		} else if(a > b) {
			return 1;
		} else {
			return -1;
		}
	}

}

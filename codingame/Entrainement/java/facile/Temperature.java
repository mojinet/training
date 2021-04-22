package fr.modji.puzzle.facile;

import java.util.Scanner;

public class Temperature {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int[] tempValue = new int[n];

        // On recupere toute les valeurs dans un tableau
        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            tempValue[i] = t;
        }

        // Si on ne fournis pas de temperature affiche 0
        if (n == 0){
            System.out.println(0);
        }else{
            System.out.println(calcPlusProche(tempValue));
        }
    }

    // Calcule la valeur la plus proche de 0 avec les valeur absolue, en cas d'egalité on choisie la valeur positive
    private static int calcPlusProche(int[] tempValue) {

        int valueNearZero = Integer.MAX_VALUE;

        for (int i : tempValue) {
            if (Math.abs(i) < Math.abs(valueNearZero)){
                valueNearZero = i;
            }else if (Math.abs(i) == Math.abs(valueNearZero)){
                valueNearZero = (i < valueNearZero ) ? valueNearZero : i ;
            }
        }

        return valueNearZero;
    }
}

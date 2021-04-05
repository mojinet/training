package fr.modji.puzzle.facile;

import java.util.Arrays;
import java.util.Scanner;

public class ChevCourse {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] horsesTable = new int[N];

        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            horsesTable[i] = pi;
        }

        // Réponse
        System.out.println(compare(horsesTable));
    }


    // Compare les cheveaux entre eux et renvois l'écart le plus petit entre 2 cheveaux
    public static int compare(int[] tab) {
        int min = Integer.MAX_VALUE;
        Arrays.sort(tab);
        for (int i = 0; i < tab.length - 1; i++) {
            if ( Math.abs(tab[i] - tab[i+1]) < min ){
                min = Math.abs(tab[i] - tab[i+1]);
            }
        }
        return min;
    }
}

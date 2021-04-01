package fr.modji.puzzle.facile;

import java.util.Scanner;

public class LaDescente {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int indexMax, hauteurMax;

        // game loop : enregistre la valeur maximal
        while (true) {
            hauteurMax = 0;
            indexMax = 0;
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt();
                if( mountainH > hauteurMax){
                    indexMax = i;
                    hauteurMax = mountainH;
                }
            }

            System.out.println(indexMax); // The index of the mountain to fire on.

        }
    }
}

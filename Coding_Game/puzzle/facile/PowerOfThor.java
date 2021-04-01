package fr.modji.puzzle.facile;

import java.util.Scanner;

public class PowerOfThor {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTx = in.nextInt(); // Thor's starting X position
        int initialTy = in.nextInt(); // Thor's starting Y position
        int actualTx = initialTx;
        int actualTy = initialTy;
        StringBuilder response = new StringBuilder();

        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.
            response = new StringBuilder();

            if (lightY > actualTy && actualTy >= 0){
                actualTy++;
                response.append("S");
            }else if (lightY < actualTy && actualTy < 17 ){
                actualTy--;
                response.append("N");
            }

            if (lightX > actualTx && actualTx >= 0){
                response.append("E");
                actualTx++;
            }else if (lightX < actualTx && actualTx < 39){
                response.append("W");
                actualTx--;
            }

            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(response.toString());
        }
    }
}

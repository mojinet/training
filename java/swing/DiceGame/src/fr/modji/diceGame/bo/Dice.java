package fr.modji.diceGame.bo;

public class Dice {
    int[] dices = new int[5];

    public int[] getDices(int max){
        for (int i = 0; i < 5; i++) {
            dices[i] = (int) (Math.random() * max);
        }
        return this.dices;
    }
}

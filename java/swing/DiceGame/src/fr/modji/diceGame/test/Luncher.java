package fr.modji.diceGame.test;

import fr.modji.diceGame.bo.Dice;
import fr.modji.diceGame.ihm.img.Windows;

public class Luncher {
    public static void main(String[] args) {
        Windows game = new Windows("Lanceur de dée");
        Dice dice = new Dice();
        dice.getDices(6);
    }
}

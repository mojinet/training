package fr.modji.main;

import fr.modji.test.*;

import java.util.Scanner;

public class QuelMedecin {
    public static void main(String[] args) {
    QuelMedecin.start();

    System.out.println("Fin du programme, bye");
    }

    // TP5
    private static void start() {
        Scanner scan = new Scanner(System.in);
        int choice;

        do{
            System.out.println("Quel test voulez vous effectuer ? ");
            System.out.println("1 - Personnes");
            System.out.println("2 - Specialités");
            System.out.println("4 - Quitter");
            System.out.print("Votre choix : ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1 :
                    TestPersonnes.test();
                    break;
                case 2 :
                    TestSpecialistes.test();
                    break;
                default : choice = 4;
            }
        } while (choice != 4);
        scan.close();
    }
}

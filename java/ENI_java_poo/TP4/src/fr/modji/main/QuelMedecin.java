package fr.modji.main;

import fr.modji.test.TestAdresse;
import fr.modji.test.TestMedecin;
import fr.modji.test.TestPatient;

import java.util.Scanner;

public class QuelMedecin {
    public static void main(String[] args) {
    QuelMedecin.start();
    System.out.println("Fin du programme, bye");
    }

    /**
     * Display information
     */
    private static void start() {
        Scanner scan = new Scanner(System.in);
        int choice;

        do{
            System.out.println("Quel test voulez vous effectuer ? ");
            System.out.println("1 - Adresse");
            System.out.println("2 - Medecin");
            System.out.println("3 - Patient");
            System.out.println("4 - Quitter");
            System.out.print("Votre choix : ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1 :
                    TestAdresse.test();
                    break;
                case 2 :
                    TestMedecin.test();
                    break;
                case 3 :
                    TestPatient.test();
                    break;
                default : choice = 4;
            }
        } while (choice != 4);
        scan.close();
    }
}

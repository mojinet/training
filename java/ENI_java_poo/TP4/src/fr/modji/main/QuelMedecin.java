package fr.modji.main;

import fr.modji.test.*;

import java.util.Scanner;

public class QuelMedecin {
    public static void main(String[] args) {
    //QuelMedecin.start();
    QuelMedecin.start2();
    System.out.println("Fin du programme, bye");
    }

    // TP4
    private static void start2() {
        Scanner scan = new Scanner(System.in);
        int choice;

        do{
            System.out.println("Quel test voulez vous effectuer ? ");
            System.out.println("1 - Association");
            System.out.println("2 - Creneau");
            System.out.println("3 - RDV");
            System.out.println("4 - Quitter");
            System.out.print("Votre choix : ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1 :
                    TestAssoAdresse.test();
                    break;
                case 2 :
                    TestCreneau.test();
                    break;
                case 3 :
                    TestRDV.test();
                    break;
                default : choice = 4;
            }
        } while (choice != 4);
        scan.close();
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

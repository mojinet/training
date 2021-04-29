package fr.modji.calculatrice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Saisie{
    private static Scanner scan = new Scanner(System.in);

    public static int setNumber(){
        boolean check;
        int number;

        do{
            try{
                System.out.println("Saisir un nombre entier : ");
                number = Saisie.scan.nextInt();
                check = false;
            }catch(InputMismatchException e){
                System.err.println("Saisie du mauvais type :( Réessayer !");
                check = true;
                number = 0;
            }finally {
                Saisie.scan.nextLine();
            }
        }while(check);
        System.out.println("Vous avez saisie : " + number);

        return number;
    }

    public static char opérateur() {

        System.out.println("Saisir un opérateur (-+*/% ou q pour quitter) : ");
        char operateur = Saisie.scan.nextLine().charAt(0);

        return operateur;
    }

    public static int getCalcule(int a, int b, char choice) throws DepassementCapaciteException {
        int res;

        switch (choice) {
            case '-' -> res = Operation.soustraire(a, b);
            case '+' -> res = Operation.ajouter(a, b);
            case '*' -> res = Operation.multiplier(a, b);
            case '/' -> res = a / b;
            case '%' -> res = a % b;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }

        return res;
    }
}

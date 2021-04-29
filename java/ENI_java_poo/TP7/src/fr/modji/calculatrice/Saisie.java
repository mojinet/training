package fr.modji.calculatrice;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Saisie{
    private static Scanner scan = new Scanner(System.in);

    /**
     * This method take a number from user's response and can return an exception if response is not corect
     * @return
     */
    public static int setNumber(){
        boolean check = true;
        int number;
        double numberTmp;

        do{
            try{
                System.out.println("Saisir un nombre entier : ");
                numberTmp = Saisie.scan.nextDouble();
                number = (int) numberTmp;
                // Check limit of INT
                if (number == numberTmp){
                    check = false;
                }else{
                    throw new DepassementCapaciteException();
                }
            // If number > max value of int
            }catch(DepassementCapaciteException e){
                System.err.println(e.getMessage());
                check = true;
                number = 0;
            /// If number is not a number (may be a String)
            }catch(InputMismatchException e){
                System.err.println("Type de données incorecte !");
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
        int res = 0;
        double resD = 0;

        try{
            if ( (choice == '/') && (a == 0 || b == 0)){
                throw new ArithmeticException();
            }else{
                switch (choice) {
                    case '-' -> resD = Operation.soustraire(a, b);
                    case '+' -> resD = Operation.ajouter(a, b);
                    case '*' -> resD = Operation.multiplier(a, b);
                    case '/' -> resD = a / b;
                    case '%' -> resD = a % b;
                    default -> throw new IllegalStateException("Opérateur inconnue: " + choice);
                }

                res = (int) resD;
                if (res != resD){
                    throw new DepassementCapaciteException();
                }
            }
        }catch (ArithmeticException e){
            System.err.println("Division par zero impossible ");
        }catch (DepassementCapaciteException e){
            System.err.println(e.getMessage());
        }

        return res;
    }
}

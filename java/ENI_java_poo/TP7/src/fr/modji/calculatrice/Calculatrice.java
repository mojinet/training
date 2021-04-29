package fr.modji.calculatrice;

public class Calculatrice {
    public static void main(String[] args) throws DepassementCapaciteException{
        char choice;
        int a,b,res;

        System.out.println("Welcome to THE calculator !");

        do{
            a = Saisie.setNumber();
            choice = Saisie.opérateur();
            if (choice != 'q'){
                b = Saisie.setNumber();
                res = Saisie.getCalcule(a,b,choice);
                System.out.println("Le resultat est : " + res);
            }
        }while(choice != 'q');

        System.out.println("this is the end, bye !");
    }

}

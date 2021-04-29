package fr.modji.calculatrice;

public class Operation {

    public static int ajouter(int a, int b) throws DepassementCapaciteException{
        if( ((long) a + b) != (a + b) ){
            throw new DepassementCapaciteException();
        }else{
            return a + b;
        }
    }
    public static int soustraire(int a, int b) throws DepassementCapaciteException{
        if( ((long) a - b) != (a - b) ){
            throw new DepassementCapaciteException();
        }else{
            return a - b;
        }
    }
    public static int multiplier(int a, int b) throws DepassementCapaciteException{
        if( ((long) a * b) != (a * b) ){
            throw new DepassementCapaciteException();
        }else{
            return a * b;
        }
    }
}

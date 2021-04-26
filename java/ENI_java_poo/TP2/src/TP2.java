import java.awt.*;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TP2 {
    public static void main(String[] args) {
        GregorianCalendar gc = new GregorianCalendar();  // Get new GregorianCalendar
        displayCalendar(gc);
    }

    private static void displayCalendar(GregorianCalendar gc) {
        // On initialise notre instance de GregorianCalendar au 1er du mois
        gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), 1);

        // On recupere les différente infos utiles de gc
        String month = gc.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG_FORMAT, Locale.FRANCE);
        String day = gc.getDisplayName(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.LONG_FORMAT, Locale.FRANCE);
        int year = gc.get(GregorianCalendar.YEAR);

        // autre variables
        int dayPosition;
        String currentNumber;

        // définie la premiere position du 1er du mois
        // connaitre la position de départ en fonction du jour
        switch (day){
            case "lundi" :
                dayPosition = 0;
                break;
            case "mardi" :
                dayPosition = 1;
                break;
            case "mercredi" :
                dayPosition = 2;
                break;
            case "jeudi" :
                dayPosition = 3;
                break;
            case "vendredi" :
                dayPosition = 4;
                break;
            case "samedi" :
                dayPosition = 5;
                break;
            case "dimanche" :
                dayPosition = 6;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + day);
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < dayPosition; i++) {
            str.append("   ");
        }

        // affichage du calendrier
        int dayInMonth = 30; //TODO le calcul
        GregorianCalendar gcTest = new GregorianCalendar();
        String dayTest = "";
        String newLine = System.getProperty("line.separator"); // recupere le caractere de séparation de ligne

        for (int i = 1; i < dayInMonth; i++) {
            str.append(String.format("%02d ", i));
            // on test si on est dimanche un retour a la ligne
            gcTest.set(year,GregorianCalendar.MONTH,i);
            System.out.println(i + " " + dayTest);
            dayTest = gcTest.getDisplayName(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.LONG_FORMAT, Locale.FRANCE);
            if( dayTest.equals("dimanche") ){
                str.append(newLine);
            }
        }

        // Affiche dans la sortie standard
        System.out.println( " * " + month + " " + year + " *");
        System.out.println("L  Ma Me J  V  S  D");
        System.out.printf(str.toString());

    }
}

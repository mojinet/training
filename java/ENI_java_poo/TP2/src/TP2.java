import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class TP2 {
    public static void main(String[] args) {
        GregorianCalendar gc = new GregorianCalendar();  // Get actual date
        displayCalendar(gc);                             // Display the calendar of the current month/year
        askNewMonth(gc);                                 // Ask a month who displaying to user

    }

    /**
     * Ask a month to user and display this with displayCalendar()
     * @param gc
     */
    private static void askNewMonth(GregorianCalendar gc) {
        System.out.print("Choose a new month ( between 1 and 12 ) : ");
        Scanner scan = new Scanner(System.in);
        int monthChoice = scan.nextInt()-1;
        scan.close();
        gc.set(GregorianCalendar.MONTH, monthChoice);
        displayCalendar(gc);
    }

    /**
     * Display calendar of the month
     * @param gc who is date in GregorianCalendar
     */
    private static void displayCalendar(GregorianCalendar gc) {
        gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), 1);                                         // init the month on the first day
        String month = gc.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG_FORMAT, Locale.FRANCE);            // Current month in french
        int year = gc.get(GregorianCalendar.YEAR);                                                                          // Current Year
        int firstDayPosition = gc.get(GregorianCalendar.DAY_OF_WEEK) == 1 ? 7 : gc.get(GregorianCalendar.DAY_OF_WEEK) - 1;  // Day of week of the first day
        int dayInMonth = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);                                               // Days max in the current month
        String newLine = System.getProperty("line.separator");                                                              // Get separator
        StringBuilder str = new StringBuilder();                                                                            // Response builder

        // Add space for all day before the first day of month
        for (int i = 0; i < firstDayPosition - 1; i++) {
            str.append("   ");
        }

        // Make a line with number of day, when day is sunday make a new line
        for (int i = 1; i <= dayInMonth; i++) {
            str.append(String.format("%02d ", i));
            gc.set(GregorianCalendar.DAY_OF_MONTH, i);
            if( gc.get(GregorianCalendar.DAY_OF_WEEK) == 1 ){
                str.append(newLine);
            }
        }

        // Displays response
        System.out.println( " * " + month + " " + year + " *");
        System.out.println("L  Ma Me J  V  S  D");
        System.out.printf(str.toString());
        System.out.println();
    }
}

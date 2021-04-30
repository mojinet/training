import java.util.Scanner;

/**
 * Reversi game
 */
public class Game {

    private Scanner scan = new Scanner(System.in);
    private int x,y;

    public static void main(String[] args) {

        System.out.println("Welcome to reversy, game for 2 player");

        board.poser(Pion.NOIR,5,6);
        board.whereIsOtherPion(Pion.NOIR, 5,6);


    }

    public void playerPlay(Pion pion){
        System.out.println("******************");
        System.out.println("turn of  " + pion.getSymbol());
        board.afficher();
        System.out.print("ligne : ");
        x = scan.nextInt();
        scan.nextLine();
        System.out.print("colonne : ");
        y = scan.nextInt();
        scan.nextLine();
        board.tester(pion,x,y);
        board.poser(pion,x,y);
    }
}
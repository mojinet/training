import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Reversi game
 */
public class Game {
    private PlateauDeReversi board = new PlateauDeReversi();
    private Scanner scan = new Scanner(System.in);
    private int x,y;

    public static void main(String[] args) {

        Game game = new Game();
        System.out.println("Welcome to reversy, game for 2 player");

        // Game loop TODO une fin
        do{
            game.playerPlay(Pion.NOIR);
            game.playerPlay(Pion.BLANC);
        }while(true);

    }

    public void playerPlay(Pion pion){
        boolean aJouer = false;

        // Si le joueur à au moins une position jouable
        if (board.peutJouer(pion)){
            System.out.println("******************");
            System.out.println("turn of  " + pion.getSymbol());
            board.afficher();

            // L'utilisateur saisie la ligne/colonne qu'il veut jouer
            do{
                try{
                    System.out.print("ligne : ");
                    x = scan.nextInt();
                    scan.nextLine();
                    System.out.print("colonne : ");
                    y = scan.nextInt();
                    scan.nextLine();
                }catch (InputMismatchException e){
                    System.out.println("/!\\  incorect ! retry ! /!\\ ");
                    scan.nextLine();
                }

                // On test la position que l'utilisateur veut jouer
                if (board.tester(pion,x,y) >= 1){
                    board.poser(pion,x,y);
                    aJouer = true;
                }else{
                    System.out.println("/!\\ You can't play here ! retry /!\\");
                }
            }while(!aJouer);
        }else{
            System.out.println("/!\\ The color " + pion.getSymbol() + " doesn't have position to play /!\\");
        }
    }
}